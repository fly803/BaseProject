package com.cg.baseproject.request.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author
 * @version 1.0
 * @date 2018/8/4
 */
public class HttpStatus {
    @SerializedName("code")
    private int mCode;
    @SerializedName("message")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    /**
     * API是否请求失败 * * @return 失败返回true, 成功返回false
     */
//    public boolean isCodeInvalid() {
//        return mCode != Constants.WEB_RESP_CODE_SUCCESS;
//    }


}
