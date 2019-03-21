package com.ivy.baseproject.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cg.baseproject.request.data.pojo.MovieSubject;
import com.ivy.baseproject.test.R;
import com.ivy.baseproject.test.adapter.MovieAdapter;
import com.ivy.baseproject.test.deprecated.RequestBusiness;
import com.ivy.baseproject.test.view.decoration.MovieDecoration;

import retrofit2.Call;
import retrofit2.Callback;


public class MovieListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(R.string.movie_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        getMovieList();

    }

    /**
     * 获取电影列表
     */
    private void getMovieList(){
        RequestBusiness.getInstance().getAPI().
                getTop250( 0, 10).
                enqueue(new Callback<MovieSubject>() {
                    @Override
                    public void onResponse(Call<MovieSubject> call, retrofit2.Response<MovieSubject> response) {
                       
                       /*
                        An HTTP response may still indicate an application-level failure such as a 404 or 500. 
                        Call Response.isSuccessful() to determine if the response indicates success.
                        HTTP response 仍然可以指示应用程序级故障，如404或500
                        调用Response.isSuccessful()，以确定是否该响应指示成功。
                         */
                        if(response.isSuccessful()){
                            //对数据的处理操作
                            mMovieAdapter.setMovies(response.body().subjects);
                            mRecyclerView.setAdapter(mMovieAdapter);
                            mMovieAdapter.notifyDataSetChanged();
                        }else{
                            //请求出现错误例如：404 或者 500
                            Log.d("cg", "onResponse: "+response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<MovieSubject> call, Throwable t) {
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

}
