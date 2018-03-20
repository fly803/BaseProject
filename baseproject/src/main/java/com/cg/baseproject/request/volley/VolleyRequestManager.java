package com.cg.baseproject.request.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.cg.baseproject.BaseApplication;

/**
* @ClassName: RequestManager
* @Description: 网络请求管理类，根据需求选择是Gson请求还是String请求等
* @author sam
* @date 2016-3-30 下午2:18:17
 */
public class VolleyRequestManager {
	private static RequestQueue mRequestQueue;
	private static VolleyRequestManager mVolleyRequestManager;
	

	public static VolleyRequestManager getInstance() {
		if (mVolleyRequestManager == null) {
			mVolleyRequestManager = new VolleyRequestManager();
		}
		return mVolleyRequestManager;
	}

	/**
	 * @param context
	 * 			application context
	 */
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}

	public static RequestQueue getQueueInstance() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(BaseApplication.getContext());
		}
		return mRequestQueue;
	}


	
	/**
	 * @return
	 * 		instance of the queue
	 * @throws
	 * 		 if init has not yet been called
	 */
	public static RequestQueue getQueueInstance(Context context) {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		}
		return mRequestQueue;
	}
	

	public static RequestQueue getRequestQueue() {
		// TODO Auto-generated method stub
		return mRequestQueue;
	}
}
