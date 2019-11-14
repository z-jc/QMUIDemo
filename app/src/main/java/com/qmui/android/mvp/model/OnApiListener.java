package com.qmui.android.mvp.model;

import java.util.List;

/**
 * @author Administrator
 * @particulars
 * @time 2019\7\24 0024 8:54
 * @class describe
 */
public interface OnApiListener {

    /**
     * 获取失败
     *
     * @param error
     */
    void onError(String error);

    /**
     * 获取成功
     *
     * @param list
     */
    void onSuccess(List<String> list);

}