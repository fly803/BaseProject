package com.ivy.baseproject.test.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.cg.baseproject.interfaces.SubscriberOnNextListener;
import com.cg.baseproject.request.data.BaseResponse;
import com.cg.baseproject.request.data.pojo.IpResult;
import com.cg.baseproject.request.data.response.BookSearchResponse;
import com.cg.baseproject.request.retrofit.subscriber.ProgressSubscriber;
import com.cg.baseproject.utils.android.ResolutionAdaptationUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ivy.baseproject.test.R;
import com.ivy.baseproject.test.adapter.MainInterfaceListAdapter;
import com.ivy.baseproject.test.api.AppConfig;
import com.ivy.baseproject.test.api.RequestBusiness;
import com.ivy.baseproject.test.bean.response.AirForecast;
import com.ivy.baseproject.test.bean.response.EnvProportion;
import com.ivy.baseproject.test.sample.SampleActivity;
import com.ivy.baseproject.test.sample.SampleFragmentActivity;
import com.ivy.baseproject.test.view.decoration.swtichgridlist.MainInterfaceItem;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 主界面，测试BaseProject的相关方法
 * @author sam
 * @version 1.0
 * @date 2016/6/8
 */
public class MainActivity extends AppCompatActivity {
    RecyclerView mRvDataIndex;
    List<MainInterfaceItem> listMainInterfaceItem = new ArrayList<>();

    @ColorInt
    private static final int[] BG_COLORS = {
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87, 
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMainInterfaceAdapter();
    }

    private void initView() {
        mRvDataIndex = (RecyclerView) findViewById(R.id.rvDataIndex);
    }

    private void initMainInterfaceAdapter() {
        MainInterfaceListAdapter dataIndexBaseQuickAdapter = new MainInterfaceListAdapter(R.layout.list_maininterface_item, initData());
        //        mRvDataIndex.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        //        mRvDataIndex.setLayoutManager(new LinearLayoutManager(this));
        mRvDataIndex.setAdapter(dataIndexBaseQuickAdapter);
        dataIndexBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                runMethod(listMainInterfaceItem.get(position).getMethod());
            }
        });
    }

    private List<MainInterfaceItem> initData() {
        MainInterfaceItem mainInterfaceItem0 = new MainInterfaceItem();
        mainInterfaceItem0.setName("Get请求");
        mainInterfaceItem0.setMethod("getRequest");
        mainInterfaceItem0.setBackgroundColor(BG_COLORS[0]);
        listMainInterfaceItem.add(mainInterfaceItem0);

        MainInterfaceItem mainInterfaceItem1 = new MainInterfaceItem();
        mainInterfaceItem1.setName("Post请求");
        mainInterfaceItem1.setMethod("postRequest");
        mainInterfaceItem1.setBackgroundColor(BG_COLORS[1]);
        listMainInterfaceItem.add(mainInterfaceItem1);

        MainInterfaceItem mainInterfaceItem2 = new MainInterfaceItem();
        mainInterfaceItem2.setName("RxGet请求");
        mainInterfaceItem2.setMethod("rxGet");
        mainInterfaceItem2.setBackgroundColor(BG_COLORS[2]);
        listMainInterfaceItem.add(mainInterfaceItem2);

        MainInterfaceItem mainInterfaceItem3 = new MainInterfaceItem();
        mainInterfaceItem3.setName("RxPost请求");
        mainInterfaceItem3.setMethod("rxPost");
        mainInterfaceItem3.setBackgroundColor(BG_COLORS[3]);
        listMainInterfaceItem.add(mainInterfaceItem3);

        MainInterfaceItem mainInterfaceItem4 = new MainInterfaceItem();
        mainInterfaceItem4.setName("网络图片");
        mainInterfaceItem4.setMethod("netImage");
        mainInterfaceItem4.setBackgroundColor(BG_COLORS[4]);
        listMainInterfaceItem.add(mainInterfaceItem4);

        MainInterfaceItem mainInterfaceItem5 = new MainInterfaceItem();
        mainInterfaceItem5.setName("分配率适配");
        mainInterfaceItem5.setMethod("resolution");
        mainInterfaceItem5.setBackgroundColor(BG_COLORS[5]);
        listMainInterfaceItem.add(mainInterfaceItem5);

        MainInterfaceItem mainInterfaceItem6 = new MainInterfaceItem();
        mainInterfaceItem6.setName("分配率适配测试");
        mainInterfaceItem6.setMethod("resolutionTest");
        mainInterfaceItem6.setBackgroundColor(BG_COLORS[6]);
        listMainInterfaceItem.add(mainInterfaceItem6);

        MainInterfaceItem mainInterfaceItem7 = new MainInterfaceItem();
        mainInterfaceItem7.setName("继承BaseActivity");
        mainInterfaceItem7.setMethod("sampleActivity");
        mainInterfaceItem7.setBackgroundColor(BG_COLORS[7]);
        listMainInterfaceItem.add(mainInterfaceItem7);

        MainInterfaceItem mainInterfaceItem8 = new MainInterfaceItem();
        mainInterfaceItem8.setName("继承BaseFragment");
        mainInterfaceItem8.setMethod("sampleFragment");
        mainInterfaceItem8.setBackgroundColor(BG_COLORS[8]);
        listMainInterfaceItem.add(mainInterfaceItem8);

        MainInterfaceItem mainInterfaceItem9 = new MainInterfaceItem();
        mainInterfaceItem9.setName("运行时权限请求");
        mainInterfaceItem9.setMethod("runtimePermission");
        mainInterfaceItem9.setBackgroundColor(BG_COLORS[9]);
        listMainInterfaceItem.add(mainInterfaceItem9);

        MainInterfaceItem mainInterfaceItem10 = new MainInterfaceItem();
        mainInterfaceItem10.setName("文件图像操作测试");
        mainInterfaceItem10.setMethod("FileUtilsTest");
        mainInterfaceItem10.setBackgroundColor(BG_COLORS[10]);
        listMainInterfaceItem.add(mainInterfaceItem10);

        MainInterfaceItem mainInterfaceItem11 = new MainInterfaceItem();
        mainInterfaceItem11.setName("UI小控件测试");
        mainInterfaceItem11.setMethod("uiWidget");
        mainInterfaceItem11.setBackgroundColor(BG_COLORS[11]);
        listMainInterfaceItem.add(mainInterfaceItem11);

        MainInterfaceItem mainInterfaceItem12 = new MainInterfaceItem();
        mainInterfaceItem12.setName("项目接口测试");
        mainInterfaceItem12.setMethod("interfaceTest");
        mainInterfaceItem12.setBackgroundColor(BG_COLORS[12]);
        listMainInterfaceItem.add(mainInterfaceItem12);

        for (int i = 13; i < 18; i++) {
            MainInterfaceItem mainInterfaceItem = new MainInterfaceItem();
            mainInterfaceItem.setName("待添加操作" + i);
            mainInterfaceItem.setMethod("");
//            mainInterfaceItem.setBackgroundColor(BG_COLORS[0]);
            listMainInterfaceItem.add(mainInterfaceItem);
        }

        return listMainInterfaceItem;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void runMethod(String methodName) {
        switch (methodName) {
            case "getRequest":
                getRequest();
                break;
            case "postRequest":
                postRequest();
                break;
            case "rxGet":
                rxGet();
                break;
            case "rxPost":
                rxGet();
                break;
            case "netImage":
                netImage();
                break;
            case "resolution":
                resolution();
                break;
            case "resolutionTest":
                resolutionTest();
                break;
            case "sampleActivity":
                sampleActivity();
                break;
            case "sampleFragment":
                sampleFragment();
                break;
            case "runtimePermission":
                runtimePermission();
                break;
            case "FileUtilsTest":
                FileUtilsTest();
                break;
            case "uiWidget":
                uiWidgetest();
                break;
            case "interfaceTest":
                interfaceTest();
                break;
            default:
                testResolution(this);
                ResolutionAdaptationUtils.showNavBar(MainActivity.this);
                Snackbar.make(mRvDataIndex, "没有方法执行", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void interfaceTest() {
/*        RequestBusiness.getInstance().toSubscribe(RequestBusiness.getInstance().getAPI().getAirForecast(), 
                new ProgressSubscriber<BaseResponse<AirForecast>>(new SubscriberOnNextListener<AirForecast>() {
            @Override
            public void onNext(AirForecast airForecast) {
                Snackbar.make(mRvDataIndex, "getAirForecast:" + airForecast.getMsg(), Snackbar.LENGTH_SHORT).show();
            }
        }, this));*/
        RequestBusiness.getInstance().toSubscribe(RequestBusiness.getInstance().getAPI().psalms(),
                new ProgressSubscriber<BaseResponse<EnvProportion>>(new SubscriberOnNextListener<EnvProportion>() {
                    @Override
                    public void onNext(EnvProportion envProportion) {
                        Log.d("cg", "MainActivity onNext: "+envProportion.getMsg()+" " +envProportion.getCode());
                        Snackbar.make(mRvDataIndex, "getEnvProportion:" + envProportion.getData(), Snackbar.LENGTH_SHORT).show();
                    }
                }, this));
    }
    
    private void uiWidgetest() {
        Intent intentResolutionTest = new Intent(MainActivity.this, UiWidgetActivity.class);
        startActivity(intentResolutionTest);
    }

    private void FileUtilsTest() {
        Intent intentResolutionTest = new Intent(MainActivity.this, FileImageActivity.class);
        startActivity(intentResolutionTest);
    }

    private void runtimePermission() {
        Intent intentResolutionTest = new Intent(MainActivity.this, PermissionActivity.class);
        startActivity(intentResolutionTest);
    }

    private void sampleFragment() {
        Intent intentResolutionTest = new Intent(MainActivity.this, SampleFragmentActivity.class);
        startActivity(intentResolutionTest);
    }

    private void sampleActivity() {
        Intent intentResolutionTest = new Intent(MainActivity.this, SampleActivity.class);
        startActivity(intentResolutionTest);
    }

    public void testResolution(Context context) {
        Log.d("cg", "testResolution: " + ResolutionAdaptationUtils.getResolutionInfo(context));
        //        Log.d("cg", "getBottomStatusHeight: "+ResolutionAdaptationUtils.getBottomStatusHeight(this));
        //        Log.d("cg", "getNavigationBarHeight: "+ResolutionAdaptationUtils.getNavigationBarHeight(this));
        Log.d("cg", "xxxvalues: " + getResources().getDimension(R.dimen.pick_pxX1));
        Log.d("cg", "dimen_values: " + getResources().getDimension(R.dimen.dimen_values));
        //        Log.d("cg", "px1: "+getResources().getDimension(R.dimen.px1));
        //        Log.d("cg", "testResolution getPPI: "+ResolutionAdaptationUtils.getPPI(3840,2160,65));
        //        Log.d("cg", "witch values dimens pick_values:"+getResources().getDimension(R.dimen.pick_values)/ ResolutionAdaptationUtils.getDipScale(this));
        //        Log.d("cg", "witch values dimens dp:"+getResources().getDimension(R.dimen.witch_values)/ResolutionAdaptationUtils.getDipScale(this));
    }

    private void resolutionTest() {
        Intent intentResolutionTest = new Intent(MainActivity.this, ResolutionTestActivity.class);
        startActivity(intentResolutionTest);
    }

    private void resolution() {
        Intent intentResolutionTest = new Intent(MainActivity.this, ResolutionAdaptionDemoActivity.class);
        startActivity(intentResolutionTest);
    }

    private void rxGet() {
        RequestBusiness.getInstance().toSubscribe(RequestBusiness.getInstance().getAPI().demoRxJava2("220.181.90.8"), 
                new ProgressSubscriber<BaseResponse<IpResult>>(new SubscriberOnNextListener<IpResult>() {
            @Override
            public void onNext(IpResult ipResult) {
                Log.d(AppConfig.TAG, "!!!onNext: " + ipResult.getCity());
                Snackbar.make(mRvDataIndex, "postRequest:" + ipResult.getCity(), Snackbar.LENGTH_SHORT).show();
            }
        }, this));

    }

    private void netImage() {
        Intent intent = new Intent(MainActivity.this, NetImageActivity.class);
        startActivity(intent);
    }

    private void postRequest() {
        RequestBusiness.getInstance().getAPI().
                postData("https://www.baidu.com", "desc", "content", "Android", true).
                enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()) {
                            //对数据的处理操作
                            Log.d("cg", "onResponse postData: " + response.body().toString());
                            Snackbar.make(mRvDataIndex, "postRequest:" + response.body().toString(), Snackbar.LENGTH_SHORT).show();
                        } else {
                            //请求出现错误例如：404 或者 500
                            Log.d("cg", "onResponse postData else: " + " code:" + response.code());
                            //                                  Log.d("cg", "onResponse postData else: " + response.headers().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.d("cg", "onFailure postData: ");
                    }

                });
    }

    private void getRequest() {
        /**
         * 同步请求，这里需要注意的是网络请求一定要在子线程中完成，不能直接在UI线程执行，不然会crash
         * BookSearchResponse response = call.execute().body();
         * https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
         */
        RequestBusiness.getInstance().getAPI().
                getSearchBooks("小王子", "", 0, 3).
                enqueue(new Callback<BookSearchResponse>() {
                    @Override
                    public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                       
                       /*
                        An HTTP response may still indicate an application-level failure such as a 404 or 500. 
                        Call Response.isSuccessful() to determine if the response indicates success.
                        HTTP response 仍然可以指示应用程序级故障，如404或500
                        调用Response.isSuccessful()，以确定是否该响应指示成功。
                         */
                        if (response.isSuccessful()) {
                            //对数据的处理操作
                            Log.d("cg", "onResponse getSearchBooks: " + response.body().getTotal());
                            Snackbar.make(mRvDataIndex, "getRequest:" + response.body().getTotal(), Snackbar.LENGTH_SHORT).show();
                        } else {
                            //请求出现错误例如：404 或者 500
                        }
                        /*try {
                            throw new NullPointerException();
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                            Log.d("cg", "Exception: " + e.toString());
                        }*/
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
    }

}
