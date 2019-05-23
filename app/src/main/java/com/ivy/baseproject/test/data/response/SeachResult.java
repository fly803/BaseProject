package com.ivy.baseproject.test.data.response;

import java.util.List;

/**
 * @author sam
 * @version 1.0
 * @date 2019/5/14
 */
public class SeachResult {
    /**
     * err_code : 0
     * err_msg : success
     * data : {"items":[{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=4012550","iconurl":"http://p15.qhimg.com/t0182f6eec540ef303f.png","name":"百度超级链","packagename":"com.baidu.xchain","appid":"4012550"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3888208","iconurl":"http://p17.qhimg.com/t01dfecec8eacd20f5b.png","name":"百度云","packagename":"com.baidu.bce","appid":"3888208"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=99383","iconurl":"http://p18.qhimg.com/t01d5006ce9db617672.png","name":"百度网盘","packagename":"com.baidu.netdisk","appid":"99383"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=246468","iconurl":"http://p4.qhimg.com/t0106c62a2a8ef4c175.png","name":"百度推广","packagename":"com.baidu.fengchao.ui","appid":"246468"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3008756","iconurl":"http://p17.qhimg.com/t0109ccca2152df5bc5.png","name":"百度圈景","packagename":"com.baidu.panocam","appid":"3008756"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=873410","iconurl":"http://p16.qhimg.com/t0190b76f9962cbbcb9.png","name":"百度身边店","packagename":"com.baidu.lbc","appid":"873410"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1929745","iconurl":"http://p17.qhimg.com/t01209c6ef8212b7f37.png","name":"百度电视助手","packagename":"com.baidu.tv.helper","appid":"1929745"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3013269","iconurl":"http://p19.qhimg.com/t01d3e5edddc7949fe4.png","name":"百度 CarLife","packagename":"com.baidu.carlife","appid":"3013269"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=2342788","iconurl":"http://p3.qhimg.com/t0141e5e20ab947de23.png","name":"百度地图HD","packagename":"com.baidu.BaiduMap.pad","appid":"2342788"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1742126","iconurl":"http://p17.qhimg.com/t0116fb3aacb1452f30.png","name":"百度糯米商家","packagename":"com.nuomi.merchant","appid":"1742126"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=922778","iconurl":"http://p15.qhimg.com/t01c0e28186eaecf52b.png","name":"百度宅男当崇祯","packagename":"com.qidian.QDReader_C1705845","appid":"922778"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=2378425","iconurl":"http://p16.qhimg.com/t01b49213c2038e1c06.png","name":"百度电视云HD","packagename":"com.baidu.tv.hd","appid":"2378425"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1959698","iconurl":"http://p18.qhimg.com/t0166d054972b4864e7.png","name":"百度书城","packagename":"com.baidu.shucheng","appid":"1959698"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3165680","iconurl":"http://p19.qhimg.com/t019604f46b349996a4.png","name":"李彦宏，百度与智能时代的畅想","packagename":"com.chineseall.singlebook60558301","appid":"3165680"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=12272","iconurl":"http://p19.qhimg.com/t0150f0c6ab147d8efe.png","name":"百度视频","packagename":"com.baidu.video","appid":"12272"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1652641","iconurl":"http://p18.qhimg.com/t012db7cfabd251df6e.png","name":"百度导航HD版","packagename":"com.baidu.navi.hd","appid":"1652641"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=7655","iconurl":"http://p17.qhimg.com/t015946988a40cb8f5d.png","name":"百度地图","packagename":"com.baidu.BaiduMap","appid":"7655"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=8216","iconurl":"http://p16.qhimg.com/t0150dfca207422e379.png","name":"百度糯米","packagename":"com.nuomi","appid":"8216"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3011683","iconurl":"http://p7.qhimg.com/t01628ae16acff64593.png","name":"百度输入法一加专版","packagename":"com.baidu.input_yijia","appid":"3011683"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=838662","iconurl":"http://p4.qhimg.com/t013826ea35492e7d34.png","name":"百度商桥","packagename":"com.baidu.bridge","appid":"838662"}],"count":20}
     */

    private int err_code;
    private String err_msg;
    private DataBean data;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * items : [{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=4012550","iconurl":"http://p15.qhimg.com/t0182f6eec540ef303f.png","name":"百度超级链","packagename":"com.baidu.xchain","appid":"4012550"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3888208","iconurl":"http://p17.qhimg.com/t01dfecec8eacd20f5b.png","name":"百度云","packagename":"com.baidu.bce","appid":"3888208"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=99383","iconurl":"http://p18.qhimg.com/t01d5006ce9db617672.png","name":"百度网盘","packagename":"com.baidu.netdisk","appid":"99383"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=246468","iconurl":"http://p4.qhimg.com/t0106c62a2a8ef4c175.png","name":"百度推广","packagename":"com.baidu.fengchao.ui","appid":"246468"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3008756","iconurl":"http://p17.qhimg.com/t0109ccca2152df5bc5.png","name":"百度圈景","packagename":"com.baidu.panocam","appid":"3008756"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=873410","iconurl":"http://p16.qhimg.com/t0190b76f9962cbbcb9.png","name":"百度身边店","packagename":"com.baidu.lbc","appid":"873410"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1929745","iconurl":"http://p17.qhimg.com/t01209c6ef8212b7f37.png","name":"百度电视助手","packagename":"com.baidu.tv.helper","appid":"1929745"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3013269","iconurl":"http://p19.qhimg.com/t01d3e5edddc7949fe4.png","name":"百度 CarLife","packagename":"com.baidu.carlife","appid":"3013269"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=2342788","iconurl":"http://p3.qhimg.com/t0141e5e20ab947de23.png","name":"百度地图HD","packagename":"com.baidu.BaiduMap.pad","appid":"2342788"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1742126","iconurl":"http://p17.qhimg.com/t0116fb3aacb1452f30.png","name":"百度糯米商家","packagename":"com.nuomi.merchant","appid":"1742126"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=922778","iconurl":"http://p15.qhimg.com/t01c0e28186eaecf52b.png","name":"百度宅男当崇祯","packagename":"com.qidian.QDReader_C1705845","appid":"922778"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=2378425","iconurl":"http://p16.qhimg.com/t01b49213c2038e1c06.png","name":"百度电视云HD","packagename":"com.baidu.tv.hd","appid":"2378425"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1959698","iconurl":"http://p18.qhimg.com/t0166d054972b4864e7.png","name":"百度书城","packagename":"com.baidu.shucheng","appid":"1959698"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3165680","iconurl":"http://p19.qhimg.com/t019604f46b349996a4.png","name":"李彦宏，百度与智能时代的畅想","packagename":"com.chineseall.singlebook60558301","appid":"3165680"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=12272","iconurl":"http://p19.qhimg.com/t0150f0c6ab147d8efe.png","name":"百度视频","packagename":"com.baidu.video","appid":"12272"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=1652641","iconurl":"http://p18.qhimg.com/t012db7cfabd251df6e.png","name":"百度导航HD版","packagename":"com.baidu.navi.hd","appid":"1652641"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=7655","iconurl":"http://p17.qhimg.com/t015946988a40cb8f5d.png","name":"百度地图","packagename":"com.baidu.BaiduMap","appid":"7655"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=8216","iconurl":"http://p16.qhimg.com/t0150dfca207422e379.png","name":"百度糯米","packagename":"com.nuomi","appid":"8216"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=3011683","iconurl":"http://p7.qhimg.com/t01628ae16acff64593.png","name":"百度输入法一加专版","packagename":"com.baidu.input_yijia","appid":"3011683"},{"downloadurl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=838662","iconurl":"http://p4.qhimg.com/t013826ea35492e7d34.png","name":"百度商桥","packagename":"com.baidu.bridge","appid":"838662"}]
         * count : 20
         */

        private int count;
        private List<ItemsBean> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * downloadurl : http://api.np.mobilem.360.cn/redirect/down/?from=lm_256435&appid=4012550
             * iconurl : http://p15.qhimg.com/t0182f6eec540ef303f.png
             * name : 百度超级链
             * packagename : com.baidu.xchain
             * appid : 4012550
             */

            private String downloadurl;
            private String iconurl;
            private String name;
            private String packagename;
            private String appid;

            public String getDownloadurl() {
                return downloadurl;
            }

            public void setDownloadurl(String downloadurl) {
                this.downloadurl = downloadurl;
            }

            public String getIconurl() {
                return iconurl;
            }

            public void setIconurl(String iconurl) {
                this.iconurl = iconurl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPackagename() {
                return packagename;
            }

            public void setPackagename(String packagename) {
                this.packagename = packagename;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }
        }
    }
}
