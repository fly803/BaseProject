package com.cg.baseproject.request.exception;

import com.cg.baseproject.request.data.BaseResponse;

/**
 * Created by 12262 on 2016/5/31.
 */
public class ApiException extends Exception {
  private int code;
  private String message;

  public ApiException(Throwable throwable, int code) {
    super(throwable);
    this.code = code;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  public BaseResponse baseResult;

  public ApiException(String detailMessage) {
    super(detailMessage);
    setMessage(detailMessage);
  }

  public ApiException(BaseResponse detailMessage) {
    super(detailMessage.getMsg());
//      super(detailMessage.getMessage()+detailMessage.getMsg());
    this.baseResult = detailMessage;
  }
}
