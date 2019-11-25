package com.qmui.android.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.qq.e.o.ads.v2.Init;

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
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        QMUISwipeBackActivityManager.init(this);
        context = getApplicationContext();
        Init.initSDK(this,"test","C1000");
    }
}