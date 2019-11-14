package com.qmui.android.mvp.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @particulars
 * @time 2019\7\24 0024 8:54
 * @class describe
 */
public class ApiModelImpl implements ApiModel {

    private String TAG = getClass().getSimpleName();

    @Override
    public void getMainData(final OnApiListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("1.多种多样按钮");
                list.add("2.进度条");
                list.add("3.折叠式TabLayout");
                list.add("4.识别颜色RGB");
                list.add("5.MVC练习");
                list.add("6.约束布局,Okgo网络框架");
                list.add("7.去拍照");
                list.add("8.去扫码");
                list.add("9.美图秀秀拼图");
                list.add("10.自定义View");
                listener.onSuccess(list);
            }
        }, 500);
    }
}