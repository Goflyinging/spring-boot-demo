package com.lxing.common.util.jwt;

import com.lxing.common.constant.TokenConstants;
import com.lxing.common.util.RsaKeyHelper;

import org.joda.time.DateTime;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/***
 * Created on 2017/11/1 <br>
 * Description: [jwt 工具类]<br>
 * @author lxing
 * @version 1.0
 */
public class JWTHelper {
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     */
    public static String generateToken(IJWTInfo jwtInfo, String priKeyPath, int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUniqueName())
                .claim(TokenConstants.JWT_KEY_USER_ID, jwtInfo.getId())
                .claim(TokenConstants.JWT_KEY_NAME, jwtInfo.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
                .compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     */
    public static IJWTInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        Object object = body.get(TokenConstants.JWT_KEY_USER_ID);
        String userId = "";
        if (object != null) {
            userId = object.toString();
        }
        String name = "";
        Object object1 = body.get(TokenConstants.JWT_KEY_NAME);
        if (object1 != null) {
            name = object1.toString();
        }
        return new JWTInfo(body.getSubject(), userId, name);
    }


    public static void main(String[] args) {
        IJWTInfo jwtInfo = new JWTInfo("LXING", "1111", "XING");
        try {
            String generateToken = JWTHelper.generateToken(jwtInfo, "key/private.key", 5000);
            System.out.println(generateToken);
            IJWTInfo infoFromToken = JWTHelper.getInfoFromToken(generateToken, "key/public.key");
            System.out.println("---------->"+infoFromToken.getId()+" "+infoFromToken.getName()+" "+infoFromToken.getUniqueName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
