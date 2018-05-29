package com.cg.baseproject.utils.android;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/**
 * Created by Administrator on 2018/4/8.
 */

public class FocusUtils {

    /**
     * @param outSide .9.png  outside
     * @param focusBox  Focus on the box
     * @param newFocus  next focusedView
     * */
    public static void focusView(final View focusBox, final View newFocus, final float outSide){

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(300);
        final float focusBox_x = focusBox.getX();
        final float focusBox_y = focusBox.getY();
        final int focusBox_left = focusBox.getLeft();
        final int focusBox_top = focusBox.getTop();
        final int focusBox_right = focusBox.getRight();
        final int focusBox_bottom = focusBox.getBottom();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = Float.parseFloat(animation.getAnimatedValue().toString());
                Log.i("===f", "" + f );

                Log.i("===l", (int) (focusBox_left+(newFocus.getLeft()-outSide-focusBox_left)*f)+"");
                Log.i("===t", (int) (focusBox_top+(newFocus.getTop()-outSide-focusBox_top)*f)+"");
                Log.i("===r", (int) (focusBox_right+(newFocus.getRight()+outSide-focusBox_right)*f)+"");
                Log.i("===b", (int) (focusBox_bottom+(newFocus.getBottom()+outSide-focusBox_bottom)*f)+"");

                Log.i("===x", focusBox.getX()+(newFocus.getX()-outSide+getParentX(newFocus,0)-focusBox.getX())*f+"");
                Log.i("===y", focusBox.getY()+(newFocus.getY()-outSide+getParentY(newFocus,0)-focusBox.getY())*f+"");
                focusBox.layout((int) (focusBox_left+(newFocus.getLeft()-outSide-focusBox_left)*f),
                        (int) (focusBox_top+(newFocus.getTop()-outSide-focusBox_top)*f),
                        (int) (focusBox_right+(newFocus.getRight()+outSide-focusBox_right)*f),
                        (int) (focusBox_bottom+(newFocus.getBottom()+outSide-focusBox_bottom)*f));
                focusBox.setX(focusBox_x+(newFocus.getX()-outSide+getParentX(newFocus,0)-focusBox_x)*f);
                focusBox.setY(focusBox_y+(newFocus.getY()-outSide+getParentY(newFocus,0)-focusBox_y)*f);
                if(f == 1){
                    focusBox.setVisibility(View.VISIBLE);
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator);
        animatorSet.start();
    }

    public static float getParentX(View view,float x){
        ViewParent parent = view.getParent();
        if(parent != null){
            try{
                View parentView = (View) parent;
                x = x + parentView.getX();
                return getParentX(parentView,x);
            }catch (ClassCastException e){
                return x;
            }
        }else{
            return x;
        }
    }

    public static float getParentY(View view,float y){
        ViewParent parent = view.getParent();
        if(parent != null){
            try {
                View parentView = (View) parent;
                y = y + parentView.getY();
                return getParentY(parentView,y);
            }catch (ClassCastException e){
                return y;
            }
        }else{
            return y;
        }
    }
}
