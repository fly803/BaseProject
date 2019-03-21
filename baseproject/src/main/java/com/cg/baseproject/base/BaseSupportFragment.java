package com.cg.baseproject.base;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/2
 * https://blog.csdn.net/xx244488877/article/details/66144690?locationNum=3&fps=1
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cg.baseproject.configs.BaseProjectConfig;
import com.cg.baseproject.view.loading.CommonLoading;
import com.roger.catloadinglibrary.CatLoadingView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseSupportFragment extends SupportFragment {
    protected BaseSupportActivity mActivity;
    protected View mRootView;//根view
    public ProgressDialog pdLoading;
    Unbinder unbinder;
    private boolean isRequestPORTRAIT;//强制竖屏
    protected boolean isSingleFragment = true;//是否单个fragment
    protected boolean isLazyLoad = true;//是否懒加载
    private boolean isFirstLoad = true;//是否是第一次加载
    private boolean isVisible = false;//是否对用户可见
    private boolean isInitView;//是否初始化控件
    private SparseArray<View> mViews;//管理View的集合
    protected static final int LOADINGSTYLECOMMON = 0;
    protected static final int GETLOADINGSTYLECAT = 1;
    protected OnBackToFirstListener _mBackToFirstListener;

    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected abstract int getFragmentLayoutId();//获得布局资源ID,强制子类重写,实现子类不同的UI效果,使用BufferKnife
    protected abstract void initViews();//强制子类重写,实现子类不同的UI效果,使用BufferKnife
    protected abstract void registerListener();//注册监听事件
    protected abstract void initData();//初始化数据，如：网络请求获取数据
    public interface OnBackToFirstListener {//返回到第一个Fragment
        void onBackToFirstFragment();
    }
    private boolean viewCreated;//记录是否已经创建了,防止重复创建
    CatLoadingView mCatLoadingView;
    CommonLoading mCommonLoading;
    int loadingStyle = 0;

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
            mActivity = (BaseSupportActivity) activity;
            if (activity instanceof OnBackToFirstListener) {
                _mBackToFirstListener = (OnBackToFirstListener) activity;
            } else {
                throw new RuntimeException(activity.toString()
                        + " must implement OnBackToFirstListener");
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 防止重复调用onCreate方法，造成在initData方法中adapter重复初始化问题
        if (!viewCreated) {
            viewCreated = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //        mViews = new SparseArray<View>();//初始化集合
        if (null == mRootView) {
            if (getFragmentLayoutId() > 0) {
                mRootView = inflater.inflate(getFragmentLayoutId(), container, false);
            }
            // 解决点击穿透问题,或者在每个fragment布局的根节点加一条android:clickable="true"。
//                        mRootView.setOnTouchListener((View.OnTouchListener) this);
            mRootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            mCatLoadingView = new CatLoadingView();
            mCommonLoading = new CommonLoading(getActivity(), BaseProjectConfig.loadingMessage);
//            initLoading(loadingStyle);
            unbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewCreated) {
            initViews();//初始化控件
            isInitView = true;//已经初始化
            if (!isLazyLoad) {//是否懒加载，懒加载在用户可见时候才加载数据
                registerListener();//注册监听事件
                initData();
            }
        }
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
        if(isSingleFragment){
            registerListener();//注册监听事件
            initData();
        }
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
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mActivity = (BaseSupportActivity) activity;
        if (activity instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnBackToFirstListener");
        }
    }

    /**
     * 处理回退事件
     *
     * @return
     */
/*    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof ZhihuFirstFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish();
            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener.onBackToFirstFragment();
            }
        }
        return true;
    }*/

    public void loading(int loadingStyle){
        initLoading(loadingStyle);
    }
    
    private void initLoading(int loadingStyle) {
        switch (loadingStyle) {
            case 0:
                if(mCommonLoading!=null){
                    mCommonLoading.showLoading();
                }
                break;
            case 1:
                if(mCatLoadingView!=null){
                    mCatLoadingView.show(getFragmentManager(), "拼命加载中");
                }
                break;
            default:
                break;
        }
    }
    
    public void cancelLoading(int loadingStyle) {
        switch (loadingStyle) {
            case 0:
                if(mCommonLoading!=null){
                    mCommonLoading.closeLoading();
                }
                break;
            case 1:
                if(mCatLoadingView == null && mCatLoadingView.isCancelable()){
                    mCatLoadingView.onStop();
                }
                break;
            default:
                break;
        }
    }
    
    private void initLoading() {
        mCatLoadingView.show(getFragmentManager(), "拼命加载中");
        
        /**
         * 初始化pdLoading
         */
        /*pdLoading = new ProgressDialog(getActivity());
        pdLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdLoading.setMessage("拼命加载中...");
        pdLoading.show();
        pdLoading.setCanceledOnTouchOutside(false);
        pdLoading.setCancelable(true);*/
    }

    public void cancelLoading(){
        if(mCatLoadingView.isCancelable()){
            mCatLoadingView.onStop();
        }
      /*  if(pdLoading!=null){
            pdLoading.cancel();
        }*/
    }
    

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        //do something  
    }

    //获取宿主Activity
    protected BaseSupportActivity getHoldingActivity() {
        return mActivity;
    }

    //添加fragment
    protected void addFragment(BaseSupportFragment fragment) {
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
//        Log.d("cg", "setUserVisibleHint: isVisibleToUser:"+isVisibleToUser);
        this.isVisible = isVisibleToUser;
//        Log.d("cg", "setUserVisibleHint: 0");
        if (isVisibleToUser) {
            onVisibleToUser(isLazyLoad);
//            Log.d("cg", "setUserVisibleHint: 1");
        }
    }

    /**
     * 用户可见时执行的操作
     *
     * @date 2016-5-26 下午4:09:39
     */
    protected void onVisibleToUser(boolean isLazyLoad) {
        if (isInitView && isVisible) {
            if (isLazyLoad && isFirstLoad) {
                onLazyLoadData();
            }
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     *
     * @date 2016-5-26 下午4:10:20
     */
    protected void onLazyLoadData() {
        registerListener();//注册监听事件
        initData();
        //初始化数据
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
