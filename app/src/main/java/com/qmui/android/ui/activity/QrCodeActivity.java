package com.qmui.android.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.ui.adapter.QrCodeAdapter;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;

public class QrCodeActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();

    @BindView(R.id.btn_once)
    public QMUIRoundButton btnOnce;
    @BindView(R.id.recycler)
    public RecyclerView recycler;
    @BindView(R.id.topbar)
    public QMUITopBar topbar;

    private Vibrator vibrator;
    private QrCodeAdapter qrCodeAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_qrcode;
    }

    /**
     * 初始化UI
     */
    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("扫码识别");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        btnOnce.setOnClickListener(this);
        qrCodeAdapter = new QrCodeAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler.setAdapter(qrCodeAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_once:
                start(QrConfig.TYPE_ONCE);
                break;
        }
    }

    /**
     * 扫码界面
     */
    private void start(int code) {
        int scan_type = 0;
        int scan_view_type = 0;
        QrConfig qrConfig = new QrConfig.Builder()
                .setDesText("开始扫码")//扫描框下文字
                .setShowDes(true)//是否显示扫描框下面文字
                .setShowLight(true)//显示手电筒按钮
                .setShowTitle(false)//显示Title
                .setShowAlbum(true)//显示从相册选择按钮
                .setCornerColor(Color.parseColor("#E42E30"))//设置扫描框颜色
                .setLineColor(Color.parseColor("#E42E30"))//设置扫描线颜色
                .setLineSpeed(QrConfig.LINE_FAST)//设置扫描线速度
                .setScanType(scan_type)//设置扫码类型（二维码，条形码，全部，自定义，默认为二维码）
                .setScanViewType(scan_view_type)//设置扫描框类型（二维码还是条形码，默认为二维码）
                .setCustombarcodeformat(QrConfig.BARCODE_EAN13)//此项只有在扫码类型为TYPE_CUSTOM时才有效
                .setPlaySound(false)//是否扫描成功后bi~的声音
                .setIsOnlyCenter(true)//是否只识别框中内容(默认为全屏识别)
                .setTitleText("扫描二维码")//设置Tilte文字
                .setIdentify_type(code)//1:连续扫码  2:单次扫码
                .setTitleBackgroudColor(Color.parseColor("#262020"))//设置状态栏颜色
                .setTitleTextColor(Color.WHITE)//设置Title文字颜色
                .create();
        QrManager.getInstance().init(qrConfig).startScan(QrCodeActivity.this, new QrManager.OnScanResultCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onScanSuccess(int code, String result) {
                Log.e(TAG, "result:" + result + ",code:" + code);
                ToastUtil.showShortToast(QrCodeActivity.this, "result:" + result);
                vibrator.vibrate(30);
                switch (code) {
                    case QrConfig.TYPE_IDENTIFY://连续扫码

                        break;
                    case QrConfig.TYPE_ONCE://单次扫码
                        qrCodeAdapter.addData(result);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}