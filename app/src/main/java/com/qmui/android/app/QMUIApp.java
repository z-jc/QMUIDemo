package com.qmui.android.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.qmui.android.util.AdManager;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

/**
 * CreateName : Z-JC
 * Date : 2019/10/23
 * Describe :
 */
public class QMUIApp extends MultiDexApplication {

    public static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
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