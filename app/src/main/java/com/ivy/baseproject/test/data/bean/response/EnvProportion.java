package com.ivy.baseproject.test.data.bean.response;

/**
 * @author
 * @version 1.0
 * @date 2018/8/3
 */
public class EnvProportion {
    /**
     * code : 200
     * msg : OK
     * data : {"pm25":12,"pm10":23,"so2":45,"no2":27,"o3":47,"co":34,"time":"2017-08-12 11:11:11"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pm25 : 12.0
         * pm10 : 23.0
         * so2 : 45.0
         * no2 : 27.0
         * o3 : 47.0
         * co : 34.0
         * time : 2017-08-12 11:11:11
         */

        private double pm25;
        private double pm10;
        private double so2;
        private double no2;
        private double o3;
        private double co;
        private String time;

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public double getPm10() {
            return pm10;
        }

        public void setPm10(double pm10) {
            this.pm10 = pm10;
        }

        public double getSo2() {
            return so2;
        }

        public void setSo2(double so2) {
            this.so2 = so2;
        }

        public double getNo2() {
            return no2;
        }

        public void setNo2(double no2) {
            this.no2 = no2;
        }

        public double getO3() {
            return o3;
        }

        public void setO3(double o3) {
            this.o3 = o3;
        }

        public double getCo() {
            return co;
        }

        public void setCo(double co) {
            this.co = co;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "DataBean{" + "pm25=" + pm25 + ", pm10=" + pm10 + ", so2=" + so2 + ", no2=" + no2 + ", o3=" + o3 + ", co=" + co + ", time='" + time + '\'' + '}';
        }
    }
}
