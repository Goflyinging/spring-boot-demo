package com.lxing.common.msg;

/***
 * Created on 2017/11/1 <br>
 * Description: [基类响应信息]<br>
 * @author lxing
 * @version 1.0
 */
public class BaseResponse {

  private int status = 200;
  private String message;

  public BaseResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public BaseResponse() {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


}
