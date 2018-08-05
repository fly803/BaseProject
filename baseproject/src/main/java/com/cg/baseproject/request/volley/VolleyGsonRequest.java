package com.cg.baseproject.request.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;

import java.util.Map;

/**
* @ClassName: GsonRequest
* @Description: 用于返回值是json格式的网络请求，采用gson进行解析
* @author sam
* @date 2014-6-18 下午2:04:33
 */
public class VolleyGsonRequest<T> extends Request<T> {

	private final Gson gson = new Gson();
	private Class<T> clazz;
	private Map<String, String> params;
	private Map<String, String> headers;
	private final Listener<T> listener;
	/** Charset for request. */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
        String.format("application/json; charset=%s", PROTOCOL_CHARSET);
	/**
	 * Get方式请求数据
	 * @param url 接口地址
	 * @param clazz 类名称
	 * @param listener 返回数据监听器
	 * @param errorListener 请求错误接口监听器
	 */
	public VolleyGsonRequest(String url, Class<T> clazz,
                             Listener<T> listener, ErrorListener errorListener) {
		super(Method.GET, url, errorListener);
		this.clazz = clazz;
		this.listener = listener;
	}
	
	/**
	 * Post方式请求数据
	 * @param url 接口地址
	 * @param clazz 类名称
	 * @param params Post访问形式参数
	 * @param listener 返回数据监听器
	 * @param errorListener 请求错误接口监听器
	 */
	public VolleyGsonRequest(String url, Class<T> clazz, Map<String, String> params,
                             Listener<T> listener, ErrorListener errorListener) {
		super(Method.POST, url, errorListener);
		this.clazz = clazz;
		this.params = params;
		this.listener = listener;
	}
	
	
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		/**
		 * 对所有请求的共同参数进行设置
		 */
//		params.put("Ver", "1.0");
//		params.put("Sid", "E90F842F18F04b4b8CD0");
		return params;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(gson.fromJson(json, clazz),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}
	}

	private Response<T> getFromDiskCache(String url,NetworkResponse response) {
		if(VolleyRequestManager.getQueueInstance().getCache().get(url)!=null){
			try {
				String json = new String((VolleyRequestManager.getQueueInstance().getCache().get(url).data));
				return Response.success(gson.fromJson(json, clazz),
						HttpHeaderParser.parseCacheHeaders(response));
			} catch (Error e) {
				// TODO Auto-generated catch block
				return Response.error(new ParseError(e));
			}
		}else{
//			Log.d(TAG, 没有缓存数据);
			return Response.success(gson.fromJson(url, clazz),
					HttpHeaderParser.parseCacheHeaders(response));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	protected Response<T> parseNetworkResponse(NetworkResponse response) {
//		try {
//			String json = new String(response.data,
//					HttpHeaderParser.parseCharset(response.headers));
//			if (clazz != null) {
//				return Response.success(gson.fromJson(json, clazz),
//						HttpHeaderParser.parseCacheHeaders(response));
//			} else if (typeOfT != null) {
//				return (Response<T>) Response.success(
//						gson.fromJson(json, typeOfT),
//						HttpHeaderParser.parseCacheHeaders(response));
//			}
//			throw new RuntimeException("clazz and typeOfT are null");
//		} catch (Exception e) {
//			return Response.error(new ParseError(e));
//		}
//	}
}