package com.qmui.android.okgomvp.model;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmui.android.okgomvp.http.OkgoConfig;
import com.qmui.android.okgomvp.http.OkgoUtil;

import java.util.Map;

/**
 * Author : Z-JC
 * Date : 2020/1/12
 * Description :
 */
public class ApiModelImpl implements ApiModel {

    /**
     * 获取物流信息
     *
     * @param params   请求参数
     * @param listener 请求回调
     */
    @Override
    public void getLogin(Map<String, String> params, final OnApiListener listener) {
        listener.onStart();
        OkgoUtil.getOkGo(OkgoConfig.LOGISTICS_URL, params, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError(response.getException().toString());
                }
                listener.onFinish();
            }
        });
    }

    /**
     * 登录接口
     *
     * @param params   请求参数
     * @param listener 请求回调
     */
    @Override
    public void getLogistics(Map<String, String> params, final OnApiListener listener) {
        listener.onStart();
        OkgoUtil.getOkGo(OkgoConfig.LOGIN_URL, params, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError(response.getException().toString());
                }
                listener.onFinish();
            }
        });
    }

    /**
     * 获取快递公司编号
     *
     * @param params
     * @param listener
     */
    @Override
    public void getCourier(Map<String, String> params, final OnApiListener listener) {
        listener.onStart();
        OkgoUtil.getOkGo(OkgoConfig.COURIER_URL, params, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError(response.getException().toString());
                }
                listener.onFinish();
            }
        });
    }
}