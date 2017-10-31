package com.lxing.common.util.jwt;

/***
 * Created on 2017/11/1 <br>
 * Description: [jwt token用户信息]<br>
 * @author lxing
 * @version 1.0
 */
public interface IJWTInfo {
    /**
     * 获取用户名
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     * @return
     */
    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();
}
