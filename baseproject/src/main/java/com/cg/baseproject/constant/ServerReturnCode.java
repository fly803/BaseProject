package com.cg.baseproject.constant;

public enum ServerReturnCode {
    REQUEST_FAIL(0, "请求失败##"),REQUEST_SUCCESS(1, "请求成功@@"),
    DEVICE_NOT_ONLINE(2, "设备不在线"),  TIME_OUT(3, "超时"),PARA_EXCEPTION(-1, "请求失败异常"), DEVICE_EXCEPTION(-9, "网络异常"), UNKNOWN(999, "未知原因");

    private int code; // 返回编码
    private String reason; // 返回原因
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private ServerReturnCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    //根据错误原因返回错误代码
    public static int getCodeByReason(String reason) {
        for (ServerReturnCode c : ServerReturnCode.values()) {
            if (c.getReason().equals(reason.trim())) {
                return c.code;
            }
        }
        return ServerReturnCode.UNKNOWN.code;
    }

    //根据错误代码返回错误原因
    public static String getReasonByCode(int code) {
        for (ServerReturnCode c : ServerReturnCode.values()) {
            if (c.getCode() == code) {
                return c.reason;
            }
        }
        return ServerReturnCode.UNKNOWN.reason;
    }
}
