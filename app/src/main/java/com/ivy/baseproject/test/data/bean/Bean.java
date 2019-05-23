package com.ivy.baseproject.test.data.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author
 * @version 1.0
 * @date 2018/8/3
 */
public class Bean {
    /**
     * data : {"sessionId:":"D594D7D4FB2FBA0FF6C0CC073C48CC96"}
     * message : 登录成功
     * code : 200
     */

    private DataBean data;
    private String message;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        @SerializedName("sessionId:")
        private String _$SessionId77; // FIXME check this code

        public String get_$SessionId77() {
            return _$SessionId77;
        }

        public void set_$SessionId77(String _$SessionId77) {
            this._$SessionId77 = _$SessionId77;
        }
    }
}
