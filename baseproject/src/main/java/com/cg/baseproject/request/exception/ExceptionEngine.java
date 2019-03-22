package com.cg.baseproject.request.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import retrofit2.HttpException;


/** 
 * 统一处理网络请求异常
 * @author  sam
 * @date   2019/3/22
 * @version 1.0
 */ 
public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e){
        ApiException ex;
        if (e instanceof HttpException){             //HTTP错误
            String httpExceptionMessage = "";
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            switch(httpException.code()){
                case UNAUTHORIZED:
                    httpExceptionMessage = UNAUTHORIZED + " " + "UNAUTHORIZED";
                    break;
                case FORBIDDEN:
                    httpExceptionMessage = FORBIDDEN + " " + "FORBIDDEN"; 
                    break;
                case NOT_FOUND:
                    httpExceptionMessage = NOT_FOUND + " " + "NOT_FOUND";
                    break;
                case REQUEST_TIMEOUT:
                    httpExceptionMessage = REQUEST_TIMEOUT + " " + "REQUEST_TIMEOUT";
                    break;
                case GATEWAY_TIMEOUT:
                    httpExceptionMessage = GATEWAY_TIMEOUT + " " + "GATEWAY_TIMEOUT";
                    break;
                case INTERNAL_SERVER_ERROR:
                    httpExceptionMessage = INTERNAL_SERVER_ERROR + " " + "INTERNAL_SERVER_ERROR";
                    break;
                case BAD_GATEWAY:
                    httpExceptionMessage = BAD_GATEWAY + " " + "BAD_GATEWAY";
                    break;
                case SERVICE_UNAVAILABLE:
                    httpExceptionMessage = SERVICE_UNAVAILABLE + " " + "SERVICE_UNAVAILABLE";
                    break;
                default:
                    httpExceptionMessage = "其他";
                    break;
            }
            ex.setMessage("网络错误:"+httpExceptionMessage);  //均视为网络错误
            return ex;
        } else if (e instanceof ServerException){    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.getCode());
            ex.setMessage(resultException.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, ERROR.PARSE_ERROR);
            ex.setMessage("ParseException解析错误");            //均视为解析错误
            return ex;
        }else if(e instanceof ConnectException){
            ex = new ApiException(e, ERROR.NETWORD_ERROR);
            ex.setMessage("连接失败");  //均视为网络错误
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, ERROR.SSL_ERROR);
            ex.setMessage("证书验证失败");
            return ex;
        }else {
            ex = new ApiException(e, ERROR.UNKNOWN);
            ex.setMessage("ExceptionEngine 未知错误");          //未知错误
            return ex;
        }
    }
}
