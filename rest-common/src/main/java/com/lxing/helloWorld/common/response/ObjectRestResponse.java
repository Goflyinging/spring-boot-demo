package com.lxing.helloWorld.common.response;

/***
 * Created on 2017/11/1 <br>
 * Description: [实体返回值封装]<br>
 * @author lxing
 * @version 1.0
 */
public class ObjectRestResponse<T> extends BaseResponse {

    private T data;
    private boolean rel;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }


    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
