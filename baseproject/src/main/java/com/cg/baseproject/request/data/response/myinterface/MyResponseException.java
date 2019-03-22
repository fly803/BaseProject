package com.cg.baseproject.request.data.response.myinterface;

/**
 * @author sam
 * @version 1.0
 * @date 2019/3/22
 */
public class MyResponseException {
    /**
     * data : 
     * code : 1
     * message : 请求失败
     */

    private String data;
    private int code;
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyResponseException{" + "data='" + data + '\'' + ", code=" + code + ", message='" + message + '\'' + '}';
    }
}
