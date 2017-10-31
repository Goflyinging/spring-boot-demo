package com.lxing.common.response;

import java.util.List;


/***
 * Created on 2017/11/1 <br>
 * Description: [list数组返回值封装]<br>
 * @author lxing
 * @version 1.0
 */
public class ListResultResponse<T> extends BaseResponse {

  private List<T> list;

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public ListResultResponse<T> List(List<T> list) {
    this.setList(list);
    return this;
  }

  public ListResultResponse(int status, String message) {
    super(status, message);
  }

  public ListResultResponse() {
  }
}
