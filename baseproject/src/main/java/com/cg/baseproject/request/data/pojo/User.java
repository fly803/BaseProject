package com.cg.baseproject.request.data.pojo;

/**
 * @author
 * @version 1.0
 * @date 3/5/2018
 */

public class User {
    private long uid;
    private String userName;
    private String token;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
