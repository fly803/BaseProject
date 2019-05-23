package com.ivy.baseproject.test.data.bean.response;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2018/7/27
 */
public class AirForecast {
    /**
     * code : 200
     * msg : OK
     * data : {"dates":["7-11","7-12","7-13","7-14","7-15"],"datas":["20","30","40","60","30"]}
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
        private List<String> dates;
        private List<String> datas;

        public List<String> getDates() {
            return dates;
        }

        public void setDates(List<String> dates) {
            this.dates = dates;
        }

        public List<String> getDatas() {
            return datas;
        }

        public void setDatas(List<String> datas) {
            this.datas = datas;
        }
    }
}
