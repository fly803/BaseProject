package com.ivy.baseproject.test.adapter;

import java.util.List;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ivy.baseproject.test.R;
import com.ivy.baseproject.test.view.decoration.swtichgridlist.MainInterfaceItem;

public class MainInterfaceListAdapter extends BaseQuickAdapter<MainInterfaceItem, BaseViewHolder> {
    public MainInterfaceListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MainInterfaceItem dataIndex) {
        baseViewHolder.setText(R.id.list_item_name, dataIndex.getName());
        baseViewHolder.setBackgroundColor(R.id.list_item_color, dataIndex.getBackgroundColor());
    }
}