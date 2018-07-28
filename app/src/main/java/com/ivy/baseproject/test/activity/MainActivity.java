package com.ivy.baseproject.test.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.cg.baseproject.request.data.BaseResponse;
import com.cg.baseproject.interfaces.SubscriberOnNextListener;
import com.cg.baseproject.request.data.pojo.IpResult;
import com.cg.baseproject.request.data.response.BookSearchResponse;
import com.cg.baseproject.request.retrofit.subscriber.ProgressSubscriber;
import com.cg.baseproject.utils.android.ResolutionAdaptationUtils;
import com.cg.baseproject.utils.android.SharedPreferencesUtils;
import com.cg.baseproject.utils.android.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ivy.baseproject.test.R;
import com.ivy.baseproject.test.adapter.MainInterfaceListAdapter;
import com.ivy.baseproject.test.api.AppConfig;
import com.ivy.baseproject.test.api.RequestBusiness;
import com.ivy.baseproject.test.sample.SampleActivity;
import com.ivy.baseproject.test.sample.SampleFragmentActivity;
import com.ivy.baseproject.test.view.decoration.swtichgridlist.AutoFitRecyclerView;
import com.ivy.baseproject.test.view.decoration.swtichgridlist.MainInterfaceItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author sam
 * @version 1.0
 * @date 2018/4/11
 */
public class MainActivity extends AppCompatActivity {
    RecyclerView mRvDataIndex;
    private MainInterfaceListAdapter mDataIndexBaseQuickAdapter;

    @ColorInt
    private static final int[] BG_COLORS = {0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079, 0xff36393e,0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079, 0xff36393e};
    @DrawableRes
    private static final int[] BG_COVERS = {R.mipmap.card_cover_a, R.mipmap.card_cover_b, R.mipmap.card_cover_c,
            R.mipmap.card_cover_d, R.mipmap.card_cover_e, R.mipmap.card_cover_f,R.mipmap.card_cover_a, R.mipmap.card_cover_b, R.mipmap.card_cover_c,
            R.mipmap.card_cover_d, R.mipmap.card_cover_e, R.mipmap.card_cover_f};

    private AutoFitRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<MainInterfaceItem> mMainInterfaceItems;
    private int mMode;

    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
//        initMainInterfaceAdapter();

        mMainInterfaceItems = initData();
        mMode = getMode();

        mLayoutInflater = LayoutInflater.from(this);
        mRecyclerView = (AutoFitRecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }
    
    private void initView(){
//        mRvDataIndex = (RecyclerView) findViewById(R.id.rvDataIndex);
    }

    private void initMainInterfaceAdapter(){
        mDataIndexBaseQuickAdapter = new MainInterfaceListAdapter(R.layout.list_maininterface_item, initData());
        mRvDataIndex.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        mRvDataIndex.setLayoutManager(new LinearLayoutManager(this));
        mRvDataIndex.setAdapter(mDataIndexBaseQuickAdapter);
        mDataIndexBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }
    private List<MainInterfaceItem> initData() {
        List<MainInterfaceItem> listMainInterfaceItem = new ArrayList<>();
        
        MainInterfaceItem record0 = new MainInterfaceItem();
        record0.setName("Get请求");
        record0.setMethod("getRequest");
        listMainInterfaceItem.add(record0);
        
        MainInterfaceItem record1 = new MainInterfaceItem();
        record1.setName("Post请求");
        record1.setMethod("postRequest");
        listMainInterfaceItem.add(record1);
        
        MainInterfaceItem record2 = new MainInterfaceItem();
        record2.setName("RxGet请求");
        record2.setMethod("rxGet");
        listMainInterfaceItem.add(record2);
        
        MainInterfaceItem record3 = new MainInterfaceItem();
        record3.setName("RxPost请求");
        record3.setMethod("rxPost");
        listMainInterfaceItem.add(record3);
        
        MainInterfaceItem record4 = new MainInterfaceItem();
        record4.setName("网络图片");
        record4.setMethod("netImage");
        listMainInterfaceItem.add(record4);
        
        MainInterfaceItem record5 = new MainInterfaceItem();
        record5.setName("分配率适配");
        record5.setMethod("resolution");
        listMainInterfaceItem.add(record5);
        
        MainInterfaceItem record6 = new MainInterfaceItem();
        record6.setName("分配率适配测试");
        record6.setMethod("resolutionTest");
        listMainInterfaceItem.add(record6);

        MainInterfaceItem record7 = new MainInterfaceItem();
        record7.setName("继承BaseActivity");
        record7.setMethod("sampleActivity");
        listMainInterfaceItem.add(record7);

        MainInterfaceItem record8 = new MainInterfaceItem();
        record8.setName("继承BaseFragment");
        record8.setMethod("sampleFragment");
        listMainInterfaceItem.add(record8);

        MainInterfaceItem record9 = new MainInterfaceItem();
        record9.setName("运行时权限请求");
        record9.setMethod("runtimePermission");
        listMainInterfaceItem.add(record9);

        MainInterfaceItem record10 = new MainInterfaceItem();
        record10.setName("文件图像操作测试");
        record10.setMethod("FileUtilsTest");
        listMainInterfaceItem.add(record10);

        MainInterfaceItem record11= new MainInterfaceItem();
        record11.setName("UI小控件");
        record11.setMethod("uiWidget");
        listMainInterfaceItem.add(record11);

        for (int i = 12; i < 18; i++) {
            MainInterfaceItem record = new MainInterfaceItem();
            record.setName("待添加操作" + i);
            record.setMethod("");
            listMainInterfaceItem.add(record);
        }
        
        return listMainInterfaceItem;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void runMethod(String methodName){
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
            default:
                testResolution(this);
                ResolutionAdaptationUtils.showNavBar(MainActivity.this);
                Snackbar.make(mRecyclerView, "没有方法执行", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void uiWidgetest(){
        Intent intentResolutionTest = new Intent(MainActivity.this, UiWidgetActivity.class);
        startActivity(intentResolutionTest);
    }
    
    private void FileUtilsTest(){
        Intent intentResolutionTest = new Intent(MainActivity.this, FileImageActivity.class);
        startActivity(intentResolutionTest);
    }
    
    private void runtimePermission(){
        Intent intentResolutionTest = new Intent(MainActivity.this, PermissionActivity.class);
        startActivity(intentResolutionTest);
    }
    
    private void sampleFragment(){
        Intent intentResolutionTest = new Intent(MainActivity.this, SampleFragmentActivity.class);
        startActivity(intentResolutionTest);
    }
    
    private void sampleActivity(){
        Intent intentResolutionTest = new Intent(MainActivity.this, SampleActivity.class);
        startActivity(intentResolutionTest);
    }
    
    public void testResolution(Context context) {
        Log.d("cg", "testResolution: "+ ResolutionAdaptationUtils.getResolutionInfo(context));
        //        Log.d("cg", "getBottomStatusHeight: "+ResolutionAdaptationUtils.getBottomStatusHeight(this));
        //        Log.d("cg", "getNavigationBarHeight: "+ResolutionAdaptationUtils.getNavigationBarHeight(this));
        Log.d("cg", "xxxvalues: " + getResources().getDimension(R.dimen.xxxvalues));
        Log.d("cg", "dimen_values: " + getResources().getDimension(R.dimen.dimen_values));
        //        Log.d("cg", "px1: "+getResources().getDimension(R.dimen.px1));
        //        Log.d("cg", "testResolution getPPI: "+ResolutionAdaptationUtils.getPPI(3840,2160,65));
        //        Log.d("cg", "witch values dimens pick_values:"+getResources().getDimension(R.dimen.pick_values)/ ResolutionAdaptationUtils.getDipScale(this));
        //        Log.d("cg", "witch values dimens dp:"+getResources().getDimension(R.dimen.witch_values)/ResolutionAdaptationUtils.getDipScale(this));
    }
    
    private void resolutionTest(){
        Intent intentResolutionTest = new Intent(MainActivity.this, ResolutionTestActivity.class);
        startActivity(intentResolutionTest);
    }

    private void resolution(){
        Intent intentResolutionTest = new Intent(MainActivity.this, ResolutionAdaptionDemoActivity.class);
        startActivity(intentResolutionTest);
    }
    
    private void rxGet(){
        RequestBusiness.getInstance()
                .toSubscribe(RequestBusiness.getInstance().getAPI().demoRxJava2("220.181.90.8"),
                        new ProgressSubscriber<BaseResponse<IpResult>>(new SubscriberOnNextListener<IpResult>() {
                            @Override
                            public void onNext(IpResult ipResult) {
                                Log.d(AppConfig.TAG, "!!!onNext: "+ipResult.getCity());
                                Snackbar.make(mRecyclerView, "postRequest:" + ipResult.getCity(), Snackbar.LENGTH_SHORT).show();
                            }
                        }, this));

    }
    
    private void netImage(){
        Intent intent = new Intent(MainActivity.this, NetImageActivity.class);
        startActivity(intent);
    }
    private void postRequest(){
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
                            Snackbar.make(mRecyclerView, "postRequest:" + response.body().toString(), Snackbar.LENGTH_SHORT).show();
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
    }
    private void getRequest(){
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
                        if(response.isSuccessful()){
                            //对数据的处理操作
                            Log.d("cg", "onResponse getSearchBooks: "+response.body().getTotal());
                            Snackbar.make(mRecyclerView, "getRequest:" + response.body().getTotal(), Snackbar.LENGTH_SHORT).show();
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
    }
    private int getMode() {
        return (int)SharedPreferencesUtils.getInstance().get("sp_key_switch_mode", AutoFitRecyclerView.MODE_LIST);
    }

    private void updateMode() {
        SharedPreferencesUtils.getInstance().set("sp_key_switch_mode", mMode);
    }

    private void toggleDisplayMode() {
        mMode = mRecyclerView.toggleMode();
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.scheduleLayoutAnimation();
        updateMode();
    }

    int isShow = 0;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            MainInterfaceItem mainInterfaceItem = (MainInterfaceItem) v.getTag();
            if (mainInterfaceItem == null) {
                Snackbar.make(mRecyclerView, "测试分辨率Dimens", Snackbar.LENGTH_SHORT).show();
                testResolution(MainActivity.this);
                ResolutionAdaptationUtils.hideNavBar(MainActivity.this);
                isShow = 1;
            } else {
                runMethod(mainInterfaceItem.getMethod());
            }
        }
    };

    
    private int getBgColor(int position) {
        int index = position % BG_COLORS.length;
        return BG_COLORS[index];
    }

    private int getBgCover(int position) {
        int index = position % BG_COVERS.length;
        return BG_COVERS[index];
    }

    private class MyAdapter extends RecyclerView.Adapter {

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            if (mMode == AutoFitRecyclerView.MODE_GRID) {
                final GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return getItemViewType(position) == TYPE_HEADER ? manager.getSpanCount() : 1;
                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                return TYPE_ITEM;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            if (TYPE_HEADER == viewType) {
                v = mLayoutInflater.inflate(R.layout.recycleview_header, parent, false);
                return new HeaderHolder(v);
            } else {
                switch (mMode) {
                    case AutoFitRecyclerView.MODE_LIST:
                        v = mLayoutInflater.inflate(R.layout.list_maininterface_item, parent, false);
                        return new ListHolder(v);
                    case AutoFitRecyclerView.MODE_GRID:
                        v = mLayoutInflater.inflate(R.layout.grid_item, parent, false);
                        return new GridHolder(v);
                }
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderHolder) {
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.headerView.setOnClickListener(mOnClickListener);
            } else {
                MainInterfaceItem mainInterfaceItem = mMainInterfaceItems.get(position - 1);// 减去header的位置
                @ColorInt int colorRes = getBgColor(position);
                if (holder instanceof ListHolder) {
                    ListHolder listHolder = (ListHolder) holder;
                    listHolder.nameText.setText(mainInterfaceItem.getName());
                    listHolder.colorView.setBackgroundColor(colorRes);
                    listHolder.cardView.setTag(mainInterfaceItem);
                    listHolder.cardView.setOnClickListener(mOnClickListener);
                } else if (holder instanceof GridHolder) {
                    GridHolder gridHolder = (GridHolder) holder;
                    gridHolder.nameText.setText(mainInterfaceItem.getName());
                    gridHolder.colorView.setBackgroundColor(colorRes);
                    gridHolder.coverView.setImageResource(getBgCover(position));
                    gridHolder.cardView.setTag(mainInterfaceItem);
                    gridHolder.cardView.setOnClickListener(mOnClickListener);
                }
            }
        }

        @Override
        public int getItemCount() {
            if (mMainInterfaceItems == null || mMainInterfaceItems.isEmpty()) {
                return 1;
            } else {
                return mMainInterfaceItems.size() + 1;
            }
        }

        public class HeaderHolder extends RecyclerView.ViewHolder {

            public View headerView;

            public HeaderHolder(View v) {
                super(v);
                headerView = v.findViewById(R.id.groups_header);
            }
        }

        public class ListHolder extends RecyclerView.ViewHolder {

            public ImageView avatarView;
            public TextView nameText;
            public View colorView;
            public CardView cardView;

            public ListHolder(View v) {
                super(v);
                cardView = (CardView) v.findViewById(R.id.list_item_card_view);
                avatarView = (ImageView) v.findViewById(R.id.list_item_avatar);
                nameText = (TextView) v.findViewById(R.id.list_item_name);
                colorView = v.findViewById(R.id.list_item_color);
            }
        }

        public class GridHolder extends RecyclerView.ViewHolder {

            public ImageView coverView;
            public TextView nameText;
            public View colorView;
            public CardView cardView;

            public GridHolder(View v) {
                super(v);
                cardView = (CardView) v.findViewById(R.id.grid_item_card_view);
                coverView = (ImageView) v.findViewById(R.id.grid_item_cover);
                nameText = (TextView) v.findViewById(R.id.grid_item_name);
                colorView = v.findViewById(R.id.grid_item_color);
            }
        }
    }

}
