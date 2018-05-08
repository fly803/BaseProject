package com.cg.baseproject.view.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cg.baseproject.R;

public class ProgressBarLayout extends RelativeLayout {
	private View view;
	private ProgressBar imgProgress;
	private TextView tvProgress;
	private ScaleAnimation animation;
	public ProgressBarLayout(Context context) {
		super(context);
		addProgressView(context);
	}

	public ProgressBarLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		addProgressView(context);
	}
	private void addProgressView(Context context){
		view= View.inflate(context, R.layout.progress_bar_layout, null);
		imgProgress=(ProgressBar)view.findViewById(R.id.imgProgress);
		tvProgress=(TextView)view.findViewById(R.id.tvProgress);
		addView(view);
	}
	public void show(){
		setVisibility(VISIBLE);
	}
	public void hide(){
		setVisibility(GONE);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return true;
	}
}
