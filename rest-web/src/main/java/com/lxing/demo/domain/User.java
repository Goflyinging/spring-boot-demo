package com.lxing.demo.domain;

import java.util.Date;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * Created on 2017/10/10 <br>
 * Description: [user entity]<br>
 * @author lxing
 * @version 1.0
 */
@ApiModel(value="user", description = "用户实体")
public class User {

    private String id;

    @ApiModelProperty(value = "用户名",name = "用户名")
    @Size(min=2,max=10)
    private String username;

    private String password;

    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
