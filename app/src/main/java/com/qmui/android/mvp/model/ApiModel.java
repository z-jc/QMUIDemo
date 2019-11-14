package com.qmui.android.mvp.model;

/**
 * @author Administrator
 * @particulars
 * @time 2019\7\24 0024 8:51
 * @class describe
 */
public interface ApiModel {

    /**
     * 获取首页列表
     *
     * @param listener
     */
    void getMainData(OnApiListener listener);
}