package com.cg.baseproject.request.data;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2018/4/13
 */
public class JsonTest {
    private static final String mJson1 = "{\"data\": \"\", \"code\": 1, \"message\": \"请求失败\"}";
    private static final String mJson2 = "{\"data\": null, \"code\": 1, \"message\": \"请求失败\"}";
    private static final String mJson3 = "{\"data\": {\"name\": \"秦川小将\", \"phone\": \"182******08\"}, \"code\": 0, \"message\": \"请求成功\"}";
    private static final String mJson4 = "{\"data\": [{\"name\": \"张三\", \"phone\": \"182******08\"},{\"name\": \"李四\", \"phone\": \"182******08\"}],\"code\": 0, \"message\": \"请求成功\"}";
    private static final String mJson5 = "{\"data\":\"{\\\"id\\\":2981,\\\"title\\\":\\\"除夕活动启动页\\\",\\\"content\\\":\\\"\\\",\\\"url\\\":\\\"http://np.test.com/Activity/MonsterNian20180215\\\"}\",\"code\":0,\"message\":\"请求成功\"}";
    private static final String mJson6 = "{\"data\":\"[{\\\"id\\\":2981,\\\"title\\\":\\\"除夕活动启动页\\\",\\\"content\\\":\\\"\\\",\\\"url\\\":\\\"http://np.test.com/Activity/MonsterNian20180215\\\"},{\\\"id\\\":2849,\\\"title\\\":\\\"默认启动页\\\",\\\"content\\\":\\\"\\\",\\\"url\\\":\\\"\\\"}]\",\"code\":0,\"message\":\"请求成功\"}";

    public static void main(String[] args) {

        String responseString = mJson1;
        System.out.println("反序列化前responseString：" + responseString);
        responseString = FromJsonUtils.fromJson(responseString,BaseResponse.class).toString();
        System.out.println("反序列化后responseString：" + responseString);
        System.out.print("***********************************************************" + "\n");
        
        System.out.print("mJson1：" + mJson1);
        BaseResponse<DataBean> mData1 = FromJsonUtils.fromJson(mJson1, DataBean.class);
        System.out.print("数据类型：" + mData1.getDataType() + "\t解析后取值：" + mData1.getData().getName() + "\n");
        System.out.print("====================================" + "\n");

        BaseResponse<DataBean> mData2 = FromJsonUtils.fromJson(mJson2, DataBean.class);
        System.out.print("数据类型：" + mData2.getDataType() + "\t解析后取值：" + mData2.getData().getName() + "\n");
        System.out.print("====================================" + "\n");

        BaseResponse<DataBean> mData3 = FromJsonUtils.fromJson(mJson3, DataBean.class);
        System.out.print("数据类型：" + mData3.getDataType() + "\t解析后取值：" + mData3.getData().getName() + "\n");
        System.out.print("====================================" + "\n");

        BaseResponse<List<DataBean>> mData4 = FromJsonUtils.fromJson(mJson4, DataBean.class);
        for (DataBean mData : mData4.getData()) {
            System.out.print("数据类型：" + mData4.getDataType() + "\t解析后取值：" + mData.getName() + "\n");
        }
        System.out.print("====================================" + "\n");

        BaseResponse<ActivityBean> mData5 = FromJsonUtils.fromJson(mJson5, ActivityBean.class);
        System.out.print("数据类型：" + mData5.getDataType() + "\t解析后取值：" + mData5.getData().getTitle() + "\n");
        System.out.print("====================================" + "\n");

        BaseResponse<List<ActivityBean>> mData6 = FromJsonUtils.fromJson(mJson6, ActivityBean.class);
        for (ActivityBean mActivity : mData6.getData()) {
            System.out.print("数据类型：" + mData6.getDataType() + "\t解析后取值：" + mActivity.getTitle() + "\n");
        }
    }
}
