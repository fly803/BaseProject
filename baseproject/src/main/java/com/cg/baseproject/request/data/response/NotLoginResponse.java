package com.cg.baseproject.request.data.response;

/**
 * @author
 * @version 1.0
 * @date 2018/3/7
 */

public class NotLoginResponse {
    /**
     * msg : need_login
     * code : 103
     * request : POST /v2/book/reviews
     */

    private String msg;
    private int code;
    private String request;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
