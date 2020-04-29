package com.qmui.android.util;

import android.media.ExifInterface;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Author : Z-JC
 * Date : 2019/11/25
 * Description :
 */
public class ExifInterfaceUtil {

    /**
     * 获取照片属性
     *
     * @return
     */
    public static String getImagedata(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(path);
            stringBuilder.append("照片尺寸(长*宽):" + exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH) + "*" + exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 获取指定文件大小
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 转换文件大小
     */
    public static String toFileSize(File file) {
        long fileS = 0;
        try {
            fileS = getFileSize(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

}