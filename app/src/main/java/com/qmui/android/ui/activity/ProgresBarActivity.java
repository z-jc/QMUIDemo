package com.qmui.android.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.bean.ProgressBuilder;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * CreateName : Z-JC
 * Date : 2019/10/23
 * Describe :
 */
public class ProgresBarActivity extends BaseActivity {

    private String TAG = getClass().getSimpleName();

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.progress_1)
    public QMUIProgressBar progress1;
    @BindView(R.id.progress_2)
    public QMUIProgressBar progress2;
    @BindView(R.id.startBtn)
    public QMUIRoundButton startBtn;
    @BindView(R.id.endBtn)
    public QMUIRoundButton endBtn;
    @BindView(R.id.start_btn_progress)
    public QMUIRoundButton startBtnProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("进度条");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });
        /*progress1.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return value + "/" + maxValue;
            }
        });*/
        progress2.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return value + "/" + maxValue;
            }
        });

        testRunnable = new TestRunnable();
    }

    @OnClick({R.id.startBtn, R.id.endBtn, R.id.start_btn_progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                progress1.setProgress(100);
                progress2.setProgress(100);
                break;
            case R.id.endBtn:
                progress1.setProgress(0);
                progress2.setProgress(0);
                break;
            case R.id.start_btn_progress:
                ProgressBuilder progressBuilder = new ProgressBuilder.Builder()
                        .setTitle("下载App进度框")
                        .setTitleMessage("正在下载请稍等...")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .setMax(100)
                        .setStyle(ProgressDialog.STYLE_HORIZONTAL)
                        .setProgress(0)
                        .build();

                progressDialog = new ProgressDialog(this);
                /*progressDialog.setTitle(progressBuilder.title);*/
                progressDialog.setMessage(progressBuilder.titleMessage);
                progressDialog.setProgressStyle(progressBuilder.style);
                progressDialog.setCancelable(progressBuilder.cancelable);
                progressDialog.setCanceledOnTouchOutside(progressBuilder.canceledOnTouchOutside);
                progressDialog.setMax(progressBuilder.max);
                progressDialog.setIndeterminate(false);
                progressDialog.setProgress(progressBuilder.progress);
                progressDialog.show();
                new Thread(testRunnable).start();
                break;
        }
    }

    private ProgressDialog progressDialog = null;
    private TestRunnable testRunnable = null;
    private volatile boolean isStart = false;        //控制线程运行

    private class TestRunnable implements Runnable {
        @Override
        public void run() {
            int count = 0;
            isStart = true;
            while (isStart) {
                if (!isStart) {
                    break;
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = 1;
                msg.obj = count++;
                handler.sendMessage(msg);
                SystemClock.sleep(100);
                if (count == progressDialog.getMax()) {
                    isStart = false;
                    Log.e(TAG, "退出循环");
                    Message dismissMsg = handler.obtainMessage();
                    dismissMsg.arg1 = 0;
                    handler.sendMessage(dismissMsg);
                    break;
                }
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case 1:
                    Log.e(TAG, "obj:" + msg.obj);
                    progressDialog.setProgress((Integer) msg.obj);
                    break;
                case 0:
                    ToastUtil.showShortToast(ProgresBarActivity.this, "下载完成");
                    progressDialog.cancel();
                    progressDialog.dismiss();
                    progressDialog = null;
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isStart = false;
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog.dismiss();
            progressDialog = null;
        }
        handler.removeCallbacksAndMessages(testRunnable);
    }
}