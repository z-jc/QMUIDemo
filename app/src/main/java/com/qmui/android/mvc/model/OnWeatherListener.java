package com.qmui.android.mvc.model;

import com.qmui.android.mvc.entity.Weather;

/**
 * Description:得到网络请求结果接口
 * User: xjp
 * Date: 2015/6/3
 * Time: 15:42
 */
public interface OnWeatherListener {
    void onSuccess(Weather weather);
    void onError();
}
