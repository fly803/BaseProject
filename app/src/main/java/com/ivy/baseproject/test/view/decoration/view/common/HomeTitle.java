package com.ivy.baseproject.test.view.decoration.view.common;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.ivy.baseproject.test.R;


/**
 * @author
 * @version 1.0
 * @date 2018/7/27 0028
 */

public class HomeTitle extends ConstraintLayout {
    private ConstraintLayout clBackground;//背景
    private TextView tvTitle;//文字

    public HomeTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        //导入布局文件
        LayoutInflater.from(context).inflate(R.layout.home_title, this);
        clBackground=(ConstraintLayout) findViewById(R.id.clBackground);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        initParams(context,attrs);
    }

    private void initParams(Context context, AttributeSet attrs) {
        Resources resources = getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HomeTitle);
        if (typedArray != null) {
            clBackground.setBackgroundColor(typedArray.getColor(R.styleable.HomeTitle_backgroundColor,resources.getColor(R.color.colorPrimary)));
            tvTitle.setText(typedArray.getString(R.styleable.HomeTitle_title));
            typedArray.recycle();
        }
    }

    /**
     * 设置文字
     *
     * @param background 需要显示的文字
     */
    public void setHomeTitle(int background,String title){
        setBackground(background);
        setText(title);
    }

    /**
     * 设置文字
     *
     * @param background 需要显示的文字
     */
    public void setBackground(int background){
        clBackground.setBackgroundColor(background);
    }
    
    /**
     * 设置文字
     *
     * @param title 需要显示的文字
     */
    public void setText(String title){
        tvTitle.setText(title);
    }

}