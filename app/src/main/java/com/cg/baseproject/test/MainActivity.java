package com.cg.baseproject.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cg.baseproject.base.BaseResponse;
import com.cg.baseproject.test.api.AppConfig;
import com.cg.baseproject.interfaces.SubscriberOnNextListener;
import com.cg.baseproject.request.data.pojo.IpResult;
import com.cg.baseproject.request.data.response.BookSearchResponse;
import com.cg.baseproject.request.retrofit.subscriber.ProgressSubscriber;
import com.cg.baseproject.test.activity.NetImageActivity;
import com.cg.baseproject.test.api.RequestBusiness;
import com.cg.baseproject.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_get)
    Button mBtnGet;
    @BindView(R.id.btn_post)
    Button mBtnPost;
    @BindView(R.id.btn_netimage)
    Button mBtnNetimage;
    @BindView(R.id.btn_rxget)
    Button mBtnRxget;
    @BindView(R.id.btn_rxpost)
    Button mBtnRxpost;
    @BindView(R.id.btn_switch_baseurl)
    Button mBtnSwitchBaseurl;
    private String str = null;
    private List list = null;
    private int start = 0;
    private int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //        Log.d("cg", "onCreate: AndroidSystemUtil:"+ AndroidSystemUtil.getMaxAspect(this));
        //        Log.d("cg", "onCreate: "+ NullUtils.isEmptyList(list));
        //        testResolution();
//                AppApplication.setUID("set10001");
        Log.d("cg", "onCreate: uid:" + AppApplication.getUID());
        ToastUtils.showToast(AppApplication.getContext(), "AppApplication.getContext()");
//        interfaceTest();
    }

    private void interfaceTest() {
        Log.d("cg", "interfaceTest: ");
        RequestBusiness.getInstance().getAPI().
                postData("https://gank.io/api/add2gank", "content", "rating", "android", true).
                enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()) {
                            //对数据的处理操作
                            Log.d("cg", "onResponse postData: " + response.body().toString());
                        } else {
                            //请求出现错误例如：404 或者 500
                            Log.d("cg", "onResponse postData else: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.d("cg", "onFailure postData: ");
                    }

                });
/*        RetrofitRequestManager.getInstance().getAPI().
                addReviews("1084336", "title", "content", "rating").
                enqueue(new Callback<NotLoginResponse>() {
                    @Override
                    public void onResponse(Call<NotLoginResponse> call, retrofit2.Response<NotLoginResponse> response) {
                        if(response.isSuccessful()){
                            //对数据的处理操作
                            Log.d("cg", "onResponse addReviews: "+response.body().getMsg());
                        }else{
                            //请求出现错误例如：404 或者 500
                            Log.d("cg", "onResponse addReviews else: "+response.body().getMsg());
                        }
                    }
                    @Override
                    public void onFailure(Call<NotLoginResponse> call, Throwable t) {
                        Log.d("cg", "onFailure addReviews: ");
                    }

                });*/
    }

    public void testResolution() {
        //        Log.d("cg", "getBottomStatusHeight: "+ResolutionAdaptationUtils.getBottomStatusHeight(this));
        //        Log.d("cg", "getNavigationBarHeight: "+ResolutionAdaptationUtils.getNavigationBarHeight(this));
        Log.d("cg", "xxxvalues: " + getResources().getDimension(R.dimen.xxxvalues));
        Log.d("cg", "dimen_values: " + getResources().getDimension(R.dimen.dimen_values));
        //        Log.d("cg", "px1: "+getResources().getDimension(R.dimen.px1));
        //        Log.d("cg", "testResolution getPPI: "+ResolutionAdaptationUtils.getPPI(3840,2160,65));
        //        Log.d("cg", "witch values dimens pick_values:"+getResources().getDimension(R.dimen.pick_values)/ ResolutionAdaptationUtils.getDipScale(this));
        //        Log.d("cg", "witch values dimens dp:"+getResources().getDimension(R.dimen.witch_values)/ResolutionAdaptationUtils.getDipScale(this));
    }

    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_netimage, R.id.btn_rxget, R.id.btn_rxpost, R.id.btn_switch_baseurl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                Log.d("cg", "onViewClicked: btn_get");
                /**
                 * 同步请求，这里需要注意的是网络请求一定要在子线程中完成，不能直接在UI线程执行，不然会crash
                 * BookSearchResponse response = call.execute().body();
                 * https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
                 */
                RequestBusiness.getInstance().getAPI().
                        getSearchBooks("小王子", "", 0, 3).
                        enqueue(new Callback<BookSearchResponse>() {
                    @Override
                    public void onResponse(Call<BookSearchResponse> call, retrofit2.Response<BookSearchResponse> response) {
                       
                       /*
                        An HTTP response may still indicate an application-level failure such as a 404 or 500. 
                        Call Response.isSuccessful() to determine if the response indicates success.
                        HTTP response 仍然可以指示应用程序级故障，如404或500
                        调用Response.isSuccessful()，以确定是否该响应指示成功。
                         */
                        if(response.isSuccessful()){
                            //对数据的处理操作
                            Log.d("cg", "onResponse getSearchBooks: "+response.body().getTotal());
                            mBtnGet.setText(String.valueOf(response.body().getTotal()));
                        }else{
                            //请求出现错误例如：404 或者 500
                        }
                        try {
                            throw new NullPointerException();
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                            Log.d("cg", "Exception: "+e.toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<BookSearchResponse> call, Throwable t) {
                        /**
                     * Invoked when a network exception occurred talking to the server or when an unexpected exception
                     * occurred creating the request or processing the response.
                     当连接服务器时出现网络异常 或者 在创建请求、处理响应结果 的时候突发异常 都会被调用。
                     通过自己测试发现了几种调用情况：GSON解析数据转换错误，手机断网或者网络异常。
                     */
                        Log.d("cg", "onFailure getSearchBooks: ");
                    }
                });
                break;
            case R.id.btn_post:
                RequestBusiness.getInstance().getAPI().
                        postData("https://www.baidu.com", 
                                "desc", 
                                "content", 
                                "Android", 
                                true).
                        enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                if (response.isSuccessful()) {
                                    //对数据的处理操作
                                    Log.d("cg", "onResponse postData: " + response.body().toString());
                                    mBtnPost.setText("post");
                                } else {
                                    //请求出现错误例如：404 或者 500
                                    Log.d("cg", "onResponse postData else: " + " code:"+response.code());
//                                  Log.d("cg", "onResponse postData else: " + response.headers().toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {
                                Log.d("cg", "onFailure postData: ");
                            }

                        });
                break;
            case R.id.btn_netimage:
                Intent intent = new Intent(MainActivity.this, NetImageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_rxget:
                RequestBusiness.getInstance()
                        .toSubscribe(RequestBusiness.getInstance().getAPI().demoRxJava2("220.181.90.8"),
                                new ProgressSubscriber<BaseResponse<IpResult>>(new SubscriberOnNextListener<IpResult>() {
                                    @Override
                                    public void onNext(IpResult ipResult) {
                                        Log.d(AppConfig.TAG, "!!!onNext: "+ipResult.getCity());
                                    }
                                }, this));

                break;
            case R.id.btn_rxpost:
                break;
            case R.id.btn_switch_baseurl:
                break;
            default:
                break;
        }
    }
}
