package com.qmui.android.http;

import com.qmui.android.config.AppConfig;
import com.qmui.android.mvc.model.Api;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author cowards
 * created on 2018\12\23 0013
 **/
public class RetrofitClint {

    private static volatile Retrofit retrofit = null;

    /**
     * 初始化Retrofit
     *
     * @return
     */
    public static Retrofit initRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitClint.class) {
                if (retrofit == null) {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .proxy(Proxy.NO_PROXY)//设置不使用代理
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(AppConfig.BASE_URL)
                            //设置 Json 转换器
                            .addConverterFactory(GsonConverterFactory.create())
                            //RxJava 适配器
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            //RxJava2 适配器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHttpClient)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static Api getApi() {
        return initRetrofit().create(Api.class);
    }

}