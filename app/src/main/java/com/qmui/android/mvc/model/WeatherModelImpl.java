package com.qmui.android.mvc.model;

import android.util.Log;

import com.qmui.android.http.RetrofitClint;
import com.qmui.android.http.RxApiManager;
import com.qmui.android.mvc.entity.Weather;
import com.qmui.android.mvc.entity.WeatherInfo;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:从网络获取天气信息接口实现
 * User: xjp
 * Date: 2015/6/3
 * Time: 15:40
 */
public class WeatherModelImpl implements WeatherModel {

    /**
     * 获取天气数据
     *
     * @param cityNumber
     * @param listener
     */
    @Override
    public void getWeather(String cityNumber, final OnWeatherListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("city", cityNumber);
        Observable<WeatherInfo> observable = RetrofitClint.getApi().getWeather(map);
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getClass().getSimpleName(), "56-e：" + e.toString());
                        if (listener != null) {
                            listener.onError();
                        }
                    }

                    @Override
                    public void onNext(WeatherInfo weatherInfo) {
                        if (weatherInfo != null) {
                            if (listener != null) {
                                Weather weather = new Weather();
                                weather.setWeatherinfo(weatherInfo);
                                listener.onSuccess(weather);
                            } else {
                                listener.onError();
                            }
                        } else {
                            listener.onError();
                        }
                    }
                });
        RxApiManager.getsInstance().add("getWeather", subscription);
    }
}