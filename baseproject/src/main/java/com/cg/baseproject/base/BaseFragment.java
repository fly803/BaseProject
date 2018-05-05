package com.cg.baseproject.base;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/2
 * https://blog.csdn.net/xx244488877/article/details/66144690?locationNum=3&fps=1
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected View mRootView;//根view
    protected boolean isLazyLoad = true;//是否懒加载
    private boolean isFirstLoad;//是否是第一次加载
    private boolean isVisible;//是否对用户可见
    private boolean isInitView;//是否初始化控件
    private SparseArray<View> mViews;//管理View的集合
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected abstract int getFragmentLayoutId();
    protected abstract void initData(Bundle savedInstanceState);//初始化数据，如：网络请求获取数据
    protected abstract void registerListener();//注册监听事件
    protected abstract void initViews();//初始化控件

    
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(getFragmentLayoutId(), container, false);
//        mViews = new SparseArray<View>();//初始化集合
        mRootView = inflater.inflate(getFragmentLayoutId(), container, false);//用布局填充器填充布局
        initViews();//初始化控件
        isInitView = true;//已经初始化
        if(!isLazyLoad){//是否懒加载，懒加载在用户可见时候才加载数据
            initData(savedInstanceState);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initData(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /*
    SDK API<23时，onAttach(Context)不执行，需要使用onAttach(Activity)。Fragment自身的Bug，v4的没有此问题
    https://blog.csdn.net/ChrisPan99/article/details/53173825
    https://www.jianshu.com/p/91e9538631f9
    */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Code here
            mActivity = (BaseActivity) activity;
        }
    }


    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        //do something  
    }

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if (isVisibleToUser) {
            onVisibleToUser(isLazyLoad);
        }
    }

    /**
     * 用户可见时执行的操作
     * @date 2016-5-26 下午4:09:39
     */
    protected void onVisibleToUser(boolean isLazyLoad) {
        if (isInitView && isVisible) {
            if(isLazyLoad){
                onLazyLoadData();
            }
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     * @date 2016-5-26 下午4:10:20
     */
    protected void onLazyLoadData() {
        initData(getArguments());;//初始化数据
        isFirstLoad = false;//已经不是第一次加载
    }
    
    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     * @date 2016-5-26 下午4:10:20
     */
/*    protected void onLazyLoad() {
        registerListener();//注册监听事件
        if(!isFirstLoad || !isInitView || !isVisible){
            return;
        }
        initData(getArguments());;//初始化数据
        isFirstLoad = false;//已经不是第一次加载
    }*/
    
   /* @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(int id) {
        if (mRootView == null) {
            return null;
        }
        return (T) mRootView.findViewById(id);
    }*/

}
