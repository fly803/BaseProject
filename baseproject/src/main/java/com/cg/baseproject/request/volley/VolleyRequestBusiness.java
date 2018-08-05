package com.cg.baseproject.request.volley;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: RequestBusiness
* @Description: 对所有接口进行统一管理，所有接口统一在这里注册
* 接口测试链接：http://huoli.sgs8.com/test.aspx
* @author sam
 */
public class VolleyRequestBusiness {
    //服务器地址
    private static Gson gson = new Gson();
    private static Map<String, String> params = new HashMap<String, String>();

    //用户建议接口
    /*public static void userSuggest(String uid, String cid, String suggestion, String contact, Response.Listener<PostResponseResultData> succesListener, ErrorListener erroListener){
        params.clear();
        params.put("uid",  uid);
        params.put("cid",  cid);
        params.put("suggestion",  suggestion);
        params.put("contact",  contact);
        RequestManager.getQueueInstance().add(new GsonRequest<PostResponseResultData>(URLContants.getSuggestURL(),
                PostResponseResultData.class,params,succesListener,erroListener));
    }*/

    //版本更新接口
    /*public static void versionUpdate(String vr, String md5, String pn, String type, Response.Listener<UpdateReturnData> succesListener, ErrorListener erroListener){
        RequestManager.getQueueInstance().add(new GsonRequest<UpdateReturnData>(URLContants.getApkUpdateURL(vr, md5, pn, type),
                UpdateReturnData.class,succesListener,erroListener));
    }*/


}
