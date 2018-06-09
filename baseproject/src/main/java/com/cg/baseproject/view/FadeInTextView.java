package com.cg.baseproject.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/04
 *     描述   : Created by allen on 2017/6/5
 *              字符串逐字显示的view
 *              fadeInTextView
 *               .setTextString("自己的字符串")
 *               .startFadeInAnimation()
 *               .setTextAnimationListener(new FadeInTextView.TextAnimationListener()
 *               {
 *                   @Override
 *                   public void animationFinish() {
 *
 *                   }
 *               });
 *     版本   : 1.0
 * </pre>
 */

public class FadeInTextView extends android.support.v7.widget.AppCompatTextView
{
    private Rect textRect = new Rect();

    private StringBuffer stringBuffer = new StringBuffer();

    private String[] arr;

    private int textCount;

    private int currentIndex = -1;

    /**
     * 每个字出现的时间
     */
    private long duration = 700;
    private ValueAnimator textAnimation;
    private String contentStr;

    private TextAnimationListener textAnimationListener;


    public FadeInTextView setTextAnimationListener(TextAnimationListener textAnimationListener)
    {
        this.textAnimationListener = textAnimationListener;
        return this;
    }

    public FadeInTextView(Context context)
    {
        this(context, null);
    }

    public FadeInTextView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private Paint mPaint;

    private void init()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float width = mPaint.measureText(contentStr + "...");
        setWidth((int) width);
    }

    /**
     * 文字逐个显示动画  通过插值的方式改变数据源
     */
    private void initAnimation()
    {
        if (textAnimation != null)
        {
            return;
        }
        //从0到textCount - 1  是设置从第一个字到最后一个字的变化因子
        textAnimation = ValueAnimator.ofInt(0, textCount);
        //执行总时间就是每个字的时间乘以字数
        textAnimation.setDuration((textCount - 1) * duration);
        //匀速显示文字
        textAnimation.setInterpolator(new LinearInterpolator());
        textAnimation.setRepeatCount(ValueAnimator.INFINITE);
        textAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                int index = (int) valueAnimator.getAnimatedValue();
                //过滤去重，保证每个字只重绘一次
                if (currentIndex != index)
                {
                    stringBuffer.append(arr[index]);
                    //重复动画使用append会造成内存泄漏
                    // append(arr[index]);

                    currentIndex = index;
                    //所有文字都显示完成之后进度回调结束动画
                    if (currentIndex == (textCount - 1))
                    {
                        stringBuffer.setLength(0);
                        if (textAnimationListener != null)
                        {
                            textAnimationListener.animationFinish();
                        }
                    }
                    setText(contentStr + stringBuffer.toString());
                }
            }
        });
    }


    /**
     * 设置逐渐显示的字符串
     *
     * @param textString 主体文字
     * @param flickerStr 动画的字体,一般设置为...
     * @return
     */
    public FadeInTextView setTextString(String textString, String flickerStr)
    {
        if (textString != null && flickerStr != null)
        {
            mPaint.setTextSize(getTextSize());
            contentStr = textString;
            setText(contentStr);
            //总字数
            textCount = flickerStr.length();
            //存放单个字的数组
            arr = new String[textCount];
            for (int i = 0; i < textCount; i++)
            {
                arr[i] = flickerStr.substring(i, i + 1);
            }
            initAnimation();
        }

        return this;
    }

    public FadeInTextView setDuration(long duration)
    {
        this.duration = duration;
        return this;
    }

    private boolean isLoading = false;

    /**
     * 开启动画
     *
     * @return
     */
    public FadeInTextView startFadeInAnimation()
    {
        if (textAnimation != null)
        {
            isLoading = true;
            stringBuffer.setLength(0);
            currentIndex = -1;
            textAnimation.start();
        }
        return this;
    }

    public boolean isLoading()
    {
        return isLoading;
    }

    /**
     * 停止动画
     *
     * @return
     */
    public FadeInTextView stopFadeInAnimation()
    {
        if (textAnimation != null)
        {
            isLoading = false;
            stringBuffer.setLength(0);
            textAnimation.cancel();
        }
        return this;
    }

    /**
     * 回调接口
     */
    public interface TextAnimationListener
    {
        void animationFinish();
    }

}
