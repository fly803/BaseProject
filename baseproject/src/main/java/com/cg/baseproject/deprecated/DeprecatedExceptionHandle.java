package com.cg.baseproject.deprecated;

import android.net.ParseException;

import com.cg.baseproject.request.exception.ApiException;
import com.cg.baseproject.request.exception.ERROR;
import com.cg.baseproject.request.exception.ServerException;
import com.google.gson.JsonParseException;
import org.json.JSONException;
import java.net.ConnectException;
import retrofit2.HttpException;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/14
 */


public class DeprecatedExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    ex.message = "Forbidden";
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.getCode());
            ex.message = resultException.getMessage();
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ApiException) {
            ex = new ResponeThrowable(e,((ApiException) e).getCode() );
            ex.message = e.getMessage();
            return ex;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误ExceptionHandle";
            return ex;
        }
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * 错误回调
     */
    protected void onError(ApiException ex) {
        
    }

    /**
     * 权限错误，需要实现重新登录操作
     */
    protected void onPermissionError(ApiException ex) {
        
    }

    /**
     * 服务器返回的错误
     */
    protected void onResultError(ApiException ex) {
        
    }
}
