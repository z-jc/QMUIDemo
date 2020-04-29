package com.qmui.android.util;

import android.os.Environment;

/**
 * Author : Z-JC
 * Date : 2019/11/25
 * Description :
 */
public class Constant {
    private static String PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/";

    public static String getDCIM() {
        return getPath() + "DCIM";
    }

    public static String getPath() {
        return PATH;
    }

}
