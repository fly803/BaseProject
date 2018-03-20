package com.cg.baseproject.request.exception;

/**
 * @author
 * @version 1.0
 * @date 2018/3/14
 */

public interface ApiErrorCode {
    /**
     * 客户端错误
     */
    int ERROR_CLIENT_AUTHORIZED = 1;
    /**
     * 用户授权失败
     */
    int ERROR_USER_AUTHORIZED = 2;
    /**
     * 请求参数错误
     */
    int ERROR_REQUEST_PARAM = 3;
    /**
     * 参数检验不通过
     */
    int ERROR_PARAM_CHECK = 4;
    /**
     * 自定义错误
     */
    int ERROR_OTHER = 10;
    /**
     * 无网络连接
     */
    int ERROR_NO_INTERNET = 11;
}

