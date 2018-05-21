package com.cg.baseproject.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cg.baseproject.R;
import com.cg.baseproject.configs.IConstants;

/**
 * 对界面加载管理：不同state下，能够显示不同的View
 *
 * @author Administrator
 */
public abstract class ContentPage extends FrameLayout {

    public ContentPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initContentPage();
    }

    public ContentPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContentPage();
    }

    public ContentPage(Context context) {
        super(context);
        initContentPage();
    }

    /**
     * 定义加载状态常量,给枚举传入自定义的int值.
     */
    enum PageState {
        /** 加载中的状态. */
        STATE_LOADING(0),
        /** 加载成功的状态. */
        STATE_SUCCESS(1),
        /** 加载数据失败的状态. */
        STATE_ERROR(2);

        private int value;

        PageState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /** 表示当前的状态,默认是加载中的状态. */
    private PageState mState = PageState.STATE_LOADING;
    /** 加载中对应的View. */
    private View loadingView;
    /** 加载数据失败的View. */
    private View errorView;
    /** 加载数据成功的View. */
    private View successView;

    /**
     * 初始化ContentPage
     */
    private void initContentPage() {
        //2.显示一下默认的VIew
        showPage();
        //3.请求服务器的数据，然后根据请求回来的数据刷新page
        loadDataAndRefreshPage();
    }

    /**
     * 向服务器请求数据，然后根据请求回来的数据刷新page
     */
    public void loadDataAndRefreshPage() {

        //1.获取从服务器请求回来的数据对象
        Object data = loadData();

        //2.根据请求回来的数据对象，判断它对应的state,并赋值给当前的state
        mState = checkData(data);

        //3.根据最新的state，刷新page
        showPage();
    }

    public void refreshPage(Object o) {
        mState = checkData(o);
        showPage();
    }

    /**
     * 对请求回来的数据对象进行判断，得到它对应的state
     *
     * @param data
     * @return
     */
    private PageState checkData(Object data) {
        final String status = (String) data;
        if (IConstants.STATE_FAILED.equals(status)) {
            //说明木有数据，那么对应的state应该是error
            return PageState.STATE_ERROR;
        } else if (IConstants.STATE_LOADING.equals(status)) {
            return PageState.STATE_LOADING;
        } else {
            //说明请求回来的有数据，那么对应的state应该是success
            return PageState.STATE_SUCCESS;
        }
    }

    /**
     * 根据当前的state控制对应的View显示，其他的view隐藏
     */
    private void showPage() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        switch (mState.getValue()) {
            //加载中的状态:
            case 0:
                if (loadingView == null) {
                    loadingView = View.inflate(getContext(), R.layout.content_page_loading,
                            null);
                }
                removeAllViews();
                addView(loadingView, params);
                break;

            //加载成功的状态
            case 1:
                removeAllViews();
                successView = createSuccessView();
                addView(successView, params);
                break;

            //加载失败的状态
            case 2:
                if (errorView == null) {
                    errorView = View.inflate(getContext(), R.layout.reset_error_layout, null);
                    TextView btn_reload = (TextView) errorView.findViewById(R.id.reset_button);
                    btn_reload.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //1.先显示loadingView
                            mState = PageState.STATE_LOADING;
                            //2.重新请求数据，然后刷新page
                            initContentPage();
                        }
                    });
                }
                removeAllViews();
                addView(errorView, params);
                break;

            default:
                break;
        }
    }

    /**
     * 每个子界面的成功的View不一样，所以应由每个子界面自己去实现，我不用关心你如何实现
     *
     * @return
     */
    protected abstract View createSuccessView();

    /**
     * 每个子界面请求数据和解析数据的过程都不一样，而我只关心每个界面请求回来的数据的对象，那么
     * 每个子界面请求数据和解析的过程应该由每个子界面自己实现
     *
     * @return
     */
    protected abstract Object loadData();
}
