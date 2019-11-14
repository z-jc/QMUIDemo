package com.qmui.android.mvp.view;

import java.util.List;

/**
 * CreateName : Z-JC
 * Date : 2019/10/14
 * Describe :
 */
public interface MainView {
    void error(String error);

    void success(List<String> list);
}