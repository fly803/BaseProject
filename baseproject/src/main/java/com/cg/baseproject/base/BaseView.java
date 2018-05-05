package com.cg.baseproject.base;

/**
 * @author
 * @version 1.0
 * @date 2018/5/4
 */
/**
 * Created by wangjitao on 2016/11/8 0008.
 * 一般的Activity中要用到View操作无非是显示加载框、影藏加载框、显示出错信息、显示当数据为空的时候的view之类的
 */
public interface BaseView {

    void showError(String msg);

    void useNightMode(boolean isNight);

}