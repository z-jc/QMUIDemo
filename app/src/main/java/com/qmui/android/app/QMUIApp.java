package com.qmui.android.app;

import android.app.Application;
import android.content.Context;

import com.qmui.android.util.AdManager;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

/**
 * CreateName : Z-JC
 * Date : 2019/10/23
 * Describe :
 */
public class QMUIApp extends Application {

    public static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        AdManager.getInstance().initEntry(base);
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        QMUISwipeBackActivityManager.init(this);
        context = getApplicationContext();
        AdManager.getInstance().initSDK(context, "test","C1000", false, true);
    }
}