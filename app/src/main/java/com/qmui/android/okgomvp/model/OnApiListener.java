package com.qmui.android.okgomvp.model;

/**
 * @author Administrator
 * @particulars
 * @time 2019\7\24 0024 8:54
 * @class describe
 */
public interface OnApiListener {

    /**
     * 请求开始
     */
    void onStart();

    /**
     * 失败
     *
     * @param error
     */
    void onError(String error);

    /**
     * 成功
     *
     * @param response
     */
    void onSuccess(String response);

    /**
     * 请求结束
     */
    void onFinish();
}