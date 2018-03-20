package com.cg.baseproject.request.data.pojo;

/**
 * @author
 * @version 1.0
 * @date 3/5/2018
 */

public class WrapperRspEntity<T> {
    private int status;
    private T data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}



