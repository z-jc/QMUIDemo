package com.qmui.android.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author : Z-JC
 * Date : 2020/4/2
 * Description :
 */
public class NativeEntity {

    public static final int TYPE_DATA = 101;
    public static final int TYPE_AD = 201;
    private int itemType;

    private String content;

    public NativeEntity(int itemType) {
        this.itemType = itemType;
    }

    public NativeEntity(int itemType, String content) {
        this.itemType = itemType;
        this.content = content;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
