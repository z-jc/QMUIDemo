package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.ui.view.MaskTextView;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * CreateName : Z-JC
 * Date : 2019/10/23
 * Describe :
 */
public class ButtonActivity extends BaseActivity {

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.textview)
    public MaskTextView textview;
    @BindView(R.id.circularButton1)
    public CircularProgressButton circularButton1;
    @BindView(R.id.circularButton2)
    public CircularProgressButton circularButton2;
    @BindView(R.id.spinner)
    public MaterialSpinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("按钮控件");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });

        circularButton1.setIndeterminateProgressMode(true);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton1.getProgress() == 0) {
                    circularButton1.setProgress(50);
                } else if (circularButton1.getProgress() == 100) {
                    circularButton1.setProgress(0);
                } else {
                    circularButton1.setProgress(100);
                }
            }
        });

        circularButton2.setIndeterminateProgressMode(true);
        circularButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton2.getProgress() == 0) {
                    circularButton2.setProgress(50);
                } else if (circularButton2.getProgress() == -1) {
                    circularButton2.setProgress(0);
                } else {
                    circularButton2.setProgress(-1);
                }
            }
        });

        spinner.setItems("北京", "上海", "广州", "深圳", "杭州", "南京", "武汉", "天津", "四川", "济南", "重庆", "西安");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, item, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.textview)
    public void onViewClicked() {
        Toasty.success(this, "Success!", Toast.LENGTH_SHORT, true).show();
    }
}