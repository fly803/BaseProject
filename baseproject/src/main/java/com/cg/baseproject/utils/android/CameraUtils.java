package com.cg.baseproject.utils.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by sam on 2017/6/1.
 * 调用相机工具类
 */

public class CameraUtils {

    public static Uri takePhotoUri = null;

    public static Uri albumPhotonUri = null;

    public static final int CODE_TAKE_PHOTO = 0;

    public static final int CODE_TAKE_PHOTO_ZOOM = 1;

    public static final int CODE_ALBUM_CHOOSE = 2;

    public static final int CODE_ALBUM_CHOOSE_ZOOM = 3;

    /**
     * 调用相机拍照
     * @param activity 调用的Activity
     */
    public static void takePhoto(Activity activity) {

        String photoName = System.currentTimeMillis() + ".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), photoName);
        Uri uri = Uri.fromFile(file);
        takePhotoUri = uri;
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, CODE_TAKE_PHOTO);
    }

    /**
     * 相机拍照裁剪
     * @param activity
     */
    public static void takePhotoZoom(Activity activity) {
        if (null != takePhotoUri) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(takePhotoUri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, takePhotoUri);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            activity.startActivityForResult(intent, CODE_TAKE_PHOTO_ZOOM);
        }
    }

    /**
     * 相册选择图片
     * @param activity
     */
    public static void albumChoose(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, CODE_ALBUM_CHOOSE);
    }

    /**
     * 裁剪相册选择的图片
     * @param activity
     * @param uri
     */
    public static void albumChooseZoom(Activity activity, Uri uri) {
        if (null != uri) {

            /*String photoName = System.currentTimeMillis() + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory(), photoName);
            try {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            albumPhotonUri = Uri.fromFile(file);*/

            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, albumPhotonUri);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            activity.startActivityForResult(intent, CODE_ALBUM_CHOOSE_ZOOM);
        }
    }

    @TargetApi(19)
    public static void handleImageOnKitKat(Activity activity, Intent data) {
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(activity, uri)) {
            // 如果是document类型的Uri，则通过document id进行处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                albumPhotonUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("com.android.provides.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                albumPhotonUri = contentUri;
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果不是document类型的uri，则使用普通方式处理
            albumPhotonUri = uri;
        }

        albumChooseZoom(activity, uri);
    }

    public static void handleImageBeforeKitKat(Activity activity, Intent data) {
        Uri uri = data.getData();

        albumChooseZoom(activity, uri);
    }

    /**
     * 通过Uri得到图片路径
     *
     * @param uri
     * @param selection
     * @return
     */
    private static String getImagePath(Activity activity, Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = activity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }

}
