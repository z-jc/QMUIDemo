package com.qmui.android.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.mvc.entity.Weather;
import com.qmui.android.mvc.model.OnWeatherListener;
import com.qmui.android.mvc.model.WeatherModel;
import com.qmui.android.mvc.model.WeatherModelImpl;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MvcActivity extends BaseActivity implements OnWeatherListener, View.OnClickListener {

    private WeatherModel weatherModel;
    private Dialog loadingDialog;

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.btn_get)
    public QMUIRoundButton roundbutton;
    @BindView(R.id.edit_city)
    public EditText editCity;
    @BindView(R.id.tv_data)
    public TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        ButterKnife.bind(this);
        weatherModel = new WeatherModelImpl();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("MVC测试Demo");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });

        roundbutton.setOnClickListener(this);
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("加载天气中...");
    }

    /**
     * 隐藏进度对话框
     */
    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                tvData.setText("");
                loadingDialog.show();
                weatherModel.getWeather(editCity.getText().toString().trim(), this);
                break;
        }
    }

    @Override
    public void onSuccess(Weather weather) {
        hideLoadingDialog();
        tvData.setText(weather.getWeatherinfo().toString());
    }

    @Override
    public void onError() {
        hideLoadingDialog();
        tvData.setText("");
        Toast.makeText(this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
    }
}