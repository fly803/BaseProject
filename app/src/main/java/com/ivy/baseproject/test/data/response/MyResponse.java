package com.ivy.baseproject.test.data.response;

/**
 * @author sam
 * @version 1.0
 * @date 2019/3/22
 */
public class MyResponse {
    /**
     * data : {"name":"秦川小将","phone":"182******08"}
     * code : 0
     * message : 请求成功
     */

    private DataBean data;
    private int code;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyResponse{" + "data=" + data + ", code=" + code + ", message='" + message + '\'' + '}';
    }

    public static class DataBean {
        /**
         * name : 秦川小将
         * phone : 182******08
         */

        private String name;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
