package com.qmui.android.http;

import rx.Subscription;

/**
 * author cowards
 * created on 2019\1\5 0005
 **/
public interface RxActionManager<T> {

    void add(T tag, Subscription subscription);

    void remove(T tag);

    void cancel(T tag);

    void cancelAll();
}
