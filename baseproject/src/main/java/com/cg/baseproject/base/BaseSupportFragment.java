package com.cg.baseproject.base;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/2
 * https://blog.csdn.net/xx244488877/article/details/66144690?locationNum=3&fps=1
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cg.baseproject.utils.ViewUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;

public abstract class BaseSupportFragment extends Fragment {
    protected BaseSupportActivity mActivity;
    protected View mRootView;//根view
    public ContentPage contentPage;
    public ProgressDialog pdLoading;
    private ArrayList<Subscriber> subscribers;
    private boolean isRequestPORTRAIT;//强制竖屏
    protected boolean isLazyLoad = true;//是否懒加载
    private boolean isFirstLoad = true;//是否是第一次加载
    private boolean isVisible = false;//是否对用户可见
    private boolean isInitView;//是否初始化控件
    private SparseArray<View> mViews;//管理View的集合

    Unbinder unbinder;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected abstract int getFragmentLayoutId();//获得布局资源ID
    protected abstract void initViews();//强制子类重写,实现子类不同的UI效果,使用BufferKnife
    protected abstract void registerListener();//注册监听事件
    protected abstract void initData(Bundle savedInstanceState);//初始化数据，如：网络请求获取数据
    protected abstract View getSuccessView();//返回据的fragment填充的具体View
    protected abstract Object requestData();//返回请求服务器的数据

    public void refreshPage(Object o) {
        contentPage.refreshPage(o);
    }
    
    /**
     * 记录是否已经创建了,防止重复创建
     */
    private boolean viewCreated;

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
//        return inflater.inflate(getFragmentLayoutId(), container, false);
//        mViews = new SparseArray<View>();//初始化集合
        if (null == mRootView) {
            // 强制竖屏显示
            if(isRequestPORTRAIT){
                mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }else {
                mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            int layoutResId = getFragmentLayoutId();
            if (layoutResId > 0) {
                mRootView = createSubscriber();
//                mRootView = inflater.inflate(getFragmentLayoutId(), container, false);
                unbinder = ButterKnife.bind(this, mRootView);
            }
            // 解决点击穿透问题,或者在每个fragment布局的根节点加一条android:clickable="true"。
//            mRootView.setOnTouchListener((View.OnTouchListener) this);
            /*mRootView.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });*/
        }
        return mRootView;
    }
    
    private View createSubscriber(){
        /**
         * 初始化pdLoading
         */
        pdLoading = new ProgressDialog(getActivity());
        pdLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdLoading.setMessage("拼命加载中...");
        pdLoading.setCanceledOnTouchOutside(false);
        pdLoading.setCancelable(true);
        /**
         * 创建Subscriber容器
         */
        subscribers  = new ArrayList<>();
        if (contentPage == null) {
            contentPage = new ContentPage(getActivity()) {
                @Override
                protected Object loadData() {
                    return requestData();
                }

                @Override
                protected View createSuccessView() {
                    return getSuccessView();
                }
            };
        } else {
            ViewUtils.removeSelfFromParent(contentPage);
        }
        return contentPage;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewCreated) {
            initViews();//初始化控件
            isInitView = true;//已经初始化
            if(!isLazyLoad){//是否懒加载，懒加载在用户可见时候才加载数据
                registerListener();//注册监听事件
                initData(savedInstanceState);
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
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(Subscriber subscriber:subscribers){
            if(!subscriber.isUnsubscribed()){
                subscriber.unsubscribe();
            }
        }
    }

    public <T> Subscriber<T> addSubscriber(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
        return subscriber;
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mActivity = (BaseSupportActivity) activity;
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
    /*protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }*/

    //移除fragment
   /* protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }*/

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
            if(isLazyLoad && isFirstLoad){
                onLazyLoadData();
            }
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     * @date 2016-5-26 下午4:10:20
     */
    protected void onLazyLoadData() {
        registerListener();//注册监听事件
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
