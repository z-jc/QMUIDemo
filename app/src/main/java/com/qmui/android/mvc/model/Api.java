package com.qmui.android.mvc.model;

import com.qmui.android.mvc.entity.WeatherInfo;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * CreateName : Z-JC
 * Date : 2019/10/29
 * Describe :
 */
public interface Api {

    /**
     * 获取天气数据
     *
     * @param map
     * @return
     */
    @GET("weather_mini")
    Observable<WeatherInfo> getWeather(@QueryMap Map<String, String> map);

}
