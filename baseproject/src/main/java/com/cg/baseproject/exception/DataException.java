package com.cg.baseproject.exception;
/**
* @ClassName: DataException
* @Description: 处理自定义数据格式异常
* @author sam
* @date 2013-12-27 下午6:15:45
 */
public class DataException extends Exception {
	String errorMessage;

	public DataException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String toString() {
		return errorMessage;
	}

	@Override
    public String getMessage() {
		return errorMessage;
	}
}
