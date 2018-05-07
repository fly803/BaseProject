package com.cg.baseproject.base;

/**
 * @author sam
 * @version 1.0
 * @date 2018/5/7
 */
/**
 * BaseUiView 是应用中所有UiView的顶级抽象类，适合抽取UiView的公共方法和属性
 *
 * UiView：MVP架构中的V。
 *
 * Created by tianlai on 16-3-3.
 */
public interface BaseUiView {

    /**
     * showLoading 方法主要用于页面请求数据时显示加载状态
     */
    public void showLoading();

    /**
     * showEmpty 方法用于请求的数据为空的状态
     */
    public void showEmpty();

    /**
     * showError 方法用于请求数据出错
     */
    public void showError();

    /**
     * showError 方法用于请求数据完成
     */
    public void loadingComplete();


}
