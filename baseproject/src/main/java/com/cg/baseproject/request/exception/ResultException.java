package com.cg.baseproject.request.exception;

/**
 * @author
 * @version 1.0
 * @date 2018/3/14
 */

public class ResultException extends RuntimeException {

    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
