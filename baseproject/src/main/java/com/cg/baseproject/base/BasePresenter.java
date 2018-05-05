package com.cg.baseproject.base;

/**
 * @author
 * @version 1.0
 * @date 2018/5/4
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
