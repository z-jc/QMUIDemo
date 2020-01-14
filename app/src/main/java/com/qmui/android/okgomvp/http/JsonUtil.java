package com.qmui.android.okgomvp.http;

import com.google.gson.Gson;
import com.qmui.android.okgomvp.entity.BaseEntity;

/**
 * Author : Z-JC
 * Date : 2020/1/12
 * Description :
 */
public class JsonUtil {

    /**
     * json串转实体类对象
     *
     * @param response
     * @param baseEntity
     * @return
     */
    public static <T> T fromJson(String response, BaseEntity baseEntity) {
        baseEntity = new Gson().fromJson(response, baseEntity.getClass());
        return (T) baseEntity;
    }

}