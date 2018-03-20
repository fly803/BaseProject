package com.cg.baseproject.interfaces;

import java.util.List;

/**
 * Created by sam on 2017/4/19.
 * 权限回调接口
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
