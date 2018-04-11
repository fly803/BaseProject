package com.ivy.baseproject.test.view.decoration.swtichgridlist;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class GridRecyclerView extends RecyclerView {

    public GridRecyclerView(Context context) {
        super(context);
        init();
    }

    public GridRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GridRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        int spanCount = 2;
        GridLayoutManager manager = new GridLayoutManager(getContext(), spanCount);
        setLayoutManager(manager);
    }

}
