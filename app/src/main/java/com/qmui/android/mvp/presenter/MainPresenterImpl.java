package com.qmui.android.mvp.presenter;

import com.qmui.android.mvp.model.ApiModel;
import com.qmui.android.mvp.model.ApiModelImpl;
import com.qmui.android.mvp.model.OnApiListener;
import com.qmui.android.mvp.view.MainView;

import java.util.List;

/**
 * @author Administrator
 * @particulars
 * @time 2019\7\24 0024 8:54
 * @class describe
 */
public class MainPresenterImpl implements MainPresenter, OnApiListener {

    private MainView mainView;
    private ApiModel apiModel;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.apiModel = new ApiModelImpl();
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void getMainData() {
        apiModel.getMainData(this);
    }

    @Override
    public void onError(String error) {
        if (mainView != null) {
            mainView.error(error);
        }
    }

    @Override
    public void onSuccess(List<String> list) {
        if (mainView != null) {
            mainView.success(list);
        }
    }
}