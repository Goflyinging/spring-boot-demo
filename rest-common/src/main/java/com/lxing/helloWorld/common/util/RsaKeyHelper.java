package com.lxing.helloWorld.common.util;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/***
 * Created on 2017/11/1 <br>
 * Description: [RSA加密算法工具类]<br>
 * @author lxing
 * @version 1.0
 */
public class RsaKeyHelper {


  /***
   * 生成公钥私钥
   * @param publicKeyFilename 公钥文件路径
   * @param privateKeyFilename 私钥文件路径
   * @param password 加密密码
   * @throws IOException
   */
  public void generateKey(String publicKeyFilename, String privateKeyFilename, String password)
      throws IOException {

    KeyPairGenerator keyPairGenerator;
    try {
      keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("getInstance RSA error!");
    }
    SecureRandom secureRandom = new SecureRandom(password.getBytes());
    keyPairGenerator.initialize(1024, secureRandom);
    KeyPair keyPair = keyPairGenerator.genKeyPair();
    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
    FileOutputStream fos = new FileOutputStream(publicKeyFilename);
    fos.write(publicKeyBytes);
    fos.close();
    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
    fos = new FileOutputStream(privateKeyFilename);
    fos.write(privateKeyBytes);
    fos.close();
  }

  /***
   * 获取私钥
   * @param filename 私钥所在classpath路径
   * @return
   * @throws IOException
   * @throws InvalidKeySpecException
   */
  public PrivateKey getPrivateKey(String filename) throws IOException, InvalidKeySpecException {
    InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
    DataInputStream dis = new DataInputStream(resourceAsStream);
    byte[] keyBytes = new byte[resourceAsStream.available()];
    dis.readFully(keyBytes);
    dis.close();
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory kf;
    try {
      kf = KeyFactory.getInstance("RSA");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("getInstance RSA error!");
    }
    return kf.generatePrivate(spec);
  }


  /***
   * 获取公钥
   * @param filename 公钥所在classpath路径
   * @return
   * @throws IOException
   * @throws InvalidKeySpecException
   */
  public PublicKey getPublicKey(String filename) throws IOException, InvalidKeySpecException {
    InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
    DataInputStream dis = new DataInputStream(resourceAsStream);
    byte[] keyBytes = new byte[resourceAsStream.available()];
    dis.readFully(keyBytes);
    dis.close();
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory kf;
    try {
      kf = KeyFactory.getInstance("RSA");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("getInstance RSA error!");
    }
    PublicKey publicKey = kf.generatePublic(spec);
    return publicKey;
  }


  public static void main(String[] args) {
    RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
    try {
      rsaKeyHelper.generateKey("/key/public.key", "/key/private.key", "lxing");
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }
}
