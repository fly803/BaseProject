package com.cg.baseproject.utils.android;

import com.cg.baseproject.BaseApplication;

/**
 * @author
 * @version 1.0
 * @date 2018/3/5
 */

public class SettingUtils {
    private static SettingUtils instance;
    private SharedPreferencesUtils preferenceUtils;
    private static final String IS_LOGIN = "is_login";//用户是否登录

    /**
     * SettingUtils.getInstance().setIsLogin(true);//设置登录配置项
     SettingUtils.getInstance().getIsLogin();//获取登录配置项，false为默认值
     * @param isLogin
     */
    public void setIsLogin(boolean isLogin){
        preferenceUtils.setParam(IS_LOGIN,isLogin);
    }

    public boolean getIsLogin(){
        return (Boolean) preferenceUtils.getParam(IS_LOGIN,false);
    }


    /**
     * 创建一个新的实例 SettingsUtils.
     */
    private SettingUtils() {
        preferenceUtils = SharedPreferencesUtils.getInstance();
    }

    public static synchronized SettingUtils getInstance(){
        if (instance == null) {
            instance = new SettingUtils();
        }
        return instance;
    }

    public void clearSettings() {
        preferenceUtils.clear(BaseApplication.getContext());
    }
}
