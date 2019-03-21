package com.ivy.baseproject.test.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cg.baseproject.request.data.pojo.GankResp;
import com.ivy.baseproject.test.R;
import com.ivy.baseproject.test.adapter.GanKAdapter;
import com.ivy.baseproject.test.deprecated.RequestBusiness;

import retrofit2.Call;
import retrofit2.Callback;

public class GirlListActivity extends AppCompatActivity {
    private RecyclerView mRecyuclerView;
    private GanKAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_list);
        initView();
        getGankList();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(R.string.award_list);
        mRecyuclerView = (RecyclerView) findViewById(R.id.gank_recycler_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyuclerView.setLayoutManager(manager);
        mRecyuclerView.addItemDecoration(new MyItemDecoration());
        mAdapter = new GanKAdapter();
        mRecyuclerView.setAdapter(mAdapter);
    }


    private void getGankList(){
        RequestBusiness.getInstance().getAPI().
                getGank("http://gank.io/api/data/福利/1000/1").
                enqueue(new Callback<GankResp>() {
                    @Override
                    public void onResponse(Call<GankResp> call, retrofit2.Response<GankResp> response) {
                       
                       /*
                        An HTTP response may still indicate an application-level failure such as a 404 or 500. 
                        Call Response.isSuccessful() to determine if the response indicates success.
                        HTTP response 仍然可以指示应用程序级故障，如404或500
                        调用Response.isSuccessful()，以确定是否该响应指示成功。
                         */
                        if(response.isSuccessful()){
                            //对数据的处理操作
                            mAdapter.setData(response.body().results);
                            mAdapter.notifyDataSetChanged();
                        }else{
                            //请求出现错误例如：404 或者 500
                            Log.d("cg", "onResponse: "+response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<GankResp> call, Throwable t) {
                        /**
                         * Invoked when a network exception occurred talking to the server or when an unexpected exception
                         * occurred creating the request or processing the response.
                         当连接服务器时出现网络异常 或者 在创建请求、处理响应结果 的时候突发异常 都会被调用。
                         通过自己测试发现了几种调用情况：GSON解析数据转换错误，手机断网或者网络异常。
                         */
                        Log.d("cg", "onFailure callTypeGet: ");
                    }
                });
    }

    public static class MyItemDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,20,20);
        }
    }

}
