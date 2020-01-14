package com.qmui.android.okgomvp.http;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.Map;

/**
 * Author : Z-JC
 * Date : 2020/1/12
 * Description :
 */
public class OkgoUtil {


    /**
     * okgo初始化
     *
     * @param application
     */
    public static void init(Application application) {
        OkGo.getInstance().init(application);
    }

    /**
     * GET异步请求
     *
     * @param reqUrl   请求url
     * @param params   请求参数
     * @param callback 请求结果
     */
    public static void getOkGo(String reqUrl, Map<String, String> params, StringCallback callback) {
        OkGo.<String>get(OkgoConfig.BASE_URL + reqUrl)
                .params(params)
                .cacheKey("cachekey")
                .headers("Content-Type", "application/json; charset=utf-8")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * POST异步请求
     *
     * @param reqUrl   请求url
     * @param params   请求参数
     * @param callback 请求结果
     */
    public static void postOkGo(String reqUrl, Map<String, String> params, StringCallback callback) {
        OkGo.<String>post(OkgoConfig.BASE_URL + reqUrl)
                .params(params)
                .cacheKey("cachekey")
                .headers("Content-Type", "application/json; charset=utf-8")
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * 取消全部请求
     */
    public static void cancelAll() {
        OkGo.getInstance().cancelAll();
    }
}