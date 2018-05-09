package com.cg.baseproject.base;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//
//import java.util.ArrayList;
//
//import shanyao.simpleapp.R;
//import shanyao.simpleapp.utils.ConstantUtils;


/**
 * Created by zs on 2015/12/21.
 */
public abstract class BaseListFragment<T> extends BaseFragment {
//    public PullToRefreshListView refreshListView;
//    public ListView listView;
//    public ArrayList<T> list = new ArrayList<>();
//    public Context mContext;
//    public MyAdapter adapter;
//    public  Handler handler;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        setActionBar();
//        initPullToRefresh();
//    }
//
//    @Override
//    protected View getSuccessView() {
//        setPullRefreshView();
//        handler = new Handler();
//        adapter = new MyAdapter();
//        listView.setAdapter(adapter);
//        return refreshListView;
//    }
//
//    public void setList(ArrayList<T> list) {
//        this.list = list;
//    }
//
//    public void setmContext(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    /**
//     * 列表适配器
//     *
//     */
//    public class MyAdapter extends BaseAdapter {
//        public MyAdapter() {
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            return setView(position, convertView, parent);
//        }
//    }
//
//    /**
//     * 初始化PullToRefresh
//     */
//    public void initPullToRefresh() {
//        refreshListView = (PullToRefreshListView) View.inflate(getActivity(),
//                R.layout.ptr_listview, null);
//        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            /**
//             * 上拉刷新和下拉加载更多都会执行该方法
//             */
//            @Override
//            public void onRefresh(final PullToRefreshBase<ListView> refreshView) {
//                // 下拉刷新
//                if (refreshListView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
//                            setRefresh();// 下拉刷新
//                }
//                // 上拉加载更多
//                else {
//                    // 加载更多
//                    loadMore();
//                }
//            }
//        });
//        listView = refreshListView.getRefreshableView();
//        listView.setDividerHeight(0);
//        listView.setSelector(android.R.color.transparent);
//        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
//    }
//    /**
//     * 根据数据的多少调整是否需要加载更多，因为在数据不够一页的时候，加载更多有bug
//     */
//    private void setPullRefreshView() {
//        if(list.size()>= ConstantUtils.MAX_ITEM_LOAD_MORE){
//            refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
//        }else{
//            refreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
//        }
//    }
//    protected abstract View setView(int position, View convertView, ViewGroup parent);
//    protected abstract void setRefresh();
//    protected abstract void loadMore();
//    protected abstract void setActionBar();
}
