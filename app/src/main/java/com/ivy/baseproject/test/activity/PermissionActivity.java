package com.ivy.baseproject.test.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cg.baseproject.utils.android.EasyPermissionUtils;
import com.cg.baseproject.utils.android.ToastUtils;
import com.ivy.baseproject.test.R;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "PermissionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EasyPermissionUtils.requestPermission(this,"必须权限",23,EasyPermissionUtils.perms);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        ToastUtils.showShort("已经授权onPermissionsGranted:"+requestCode);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtils.showShort("!!!onPermissionsDenied:"+requestCode);
//        EasyPermissionUtils.requestPermission(this,"必须权限",23,EasyPermissionUtils.perms);
    }
}
