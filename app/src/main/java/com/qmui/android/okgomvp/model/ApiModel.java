package com.qmui.android.okgomvp.model;

import java.util.Map;

/**
 * @author Administrator
 * @particulars
 * @time 2019\7\24 0024 8:51
 * @class describe
 */
public interface ApiModel {

    /**
     * 登录接口
     *
     * @param params   请求参数
     * @param listener 请求回调
     */
    void getLogin(Map<String, String> params, OnApiListener listener);

    /**
     * 获取物流信息
     *
     * @param params
     * @param listener
     */
    void getLogistics(Map<String, String> params, OnApiListener listener);

    /**
     * 获取快递公司编号
     *
     * @param params
     */
    void getCourier(Map<String, String> params, OnApiListener listener);
}