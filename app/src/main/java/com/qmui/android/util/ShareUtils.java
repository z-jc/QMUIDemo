package com.qmui.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import java.io.File;

/**
 * CreateName : Z-JC
 * Date : 2019/10/29
 * Describe :
 */
public class ShareUtils {

    /**
     * 原生分享图片
     *
     * @param file
     * @param activity
     */
    public static void sendShare(File file, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(activity, getAppPackageName(activity) + ".fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            fileUri = Uri.fromFile(file);
        }
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        intent.setType("image/*");
        Intent chooser = Intent.createChooser(intent, "");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(chooser);
        }
    }

    public static String getAppPackageName(Context context) {
        String packageName = "";
        try {
            packageName = context.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageName;
    }



}