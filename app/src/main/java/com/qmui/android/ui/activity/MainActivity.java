package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.PuzzleCallback;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.qmui.android.R;
import com.qmui.android.adapter.MainAdapter;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.mvp.presenter.MainPresenter;
import com.qmui.android.mvp.presenter.MainPresenterImpl;
import com.qmui.android.mvp.view.MainView;
import com.qmui.android.ui.glide.GlideEngine;
import com.qmui.android.util.ToastUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainAdapter.OnLister, MainView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.roundbutton)
    public QMUIRoundButton roundbutton;
    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    public SwipeRefreshLayout refreshlayout;
    private MainAdapter mainAdapter;

    private MainPresenter presenter;
    private QMUITipDialog tipDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTopBar();
    }

    private void initTopBar() {
        presenter = new MainPresenterImpl(this);
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("控件列表");

        mainAdapter = new MainAdapter(this, this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(mainAdapter);

        refreshlayout.setOnRefreshListener(this);
        refreshlayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @OnClick(R.id.roundbutton)
    public void onViewClicked() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setMessage("确认请求最新数据吗?")
                .setCancelable(true)//禁止按下返回false
                .setCanceledOnTouchOutside(true)//禁止触摸false
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        mainAdapter.clear();
                        presenter.getMainData();
                        // TODO: 2019/10/23 去刷新
                        tipDialog = new QMUITipDialog.Builder(MainActivity.this)
                                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                                .setTipWord("正在刷新")
                                .create();
                        tipDialog.show();
                    }
                }).show();
    }

    @Override
    public void lister(int position) {
        switch (position) {
            case 0:
                startActivity(getIntent(this, new ButtonActivity()));
                break;
            case 1:
                startActivity(getIntent(this, new ProgresBarActivity()));
                break;
            case 2:
                startActivity(getIntent(this, new TabActivity()));
                break;
            case 3:
                ToastUtil.showShortToastCenter(this, "该功能暂未开放");
                break;
            case 4:
                startActivity(getIntent(this, new MvcActivity()));
                break;
            case 5:
                startActivity(getIntent(this, new OkgoActivity()));
                break;
            case 6:
                EasyPhotos.createCamera(this)//参数说明：上下文
                        .setFileProviderAuthority("com.qmui.android.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .start(new SelectCallback() {
                            @Override
                            public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
                                Log.e(getClass().getSimpleName(), "拍照完成:" + Arrays.toString(paths.toArray()));
                            }
                        });
                break;
            case 7:
                startActivity(getIntent(this, new QrCodeActivity()));
                break;
            case 8:
                EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.qmui.android.fileprovider")
                        .setPuzzleMenu(false)
                        .setCount(9)
                        .setSelectedPhotos(new ArrayList<Photo>())
//                        .setSelectedPhotoPaths(selectedPhotoPathList)两种方式参数类型不同，根据情况任选
                        .start(new SelectCallback() {
                            @Override
                            public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
                                EasyPhotos.startPuzzleWithPhotos(MainActivity.this, photos, Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera", "LSC" + System.currentTimeMillis(), false, GlideEngine.getInstance(), new PuzzleCallback() {
                                    @Override
                                    public void onResult(Photo photo, String path) {
                                        ToastUtil.showShortToast(MainActivity.this, "制做完成:" + path);
                                    }
                                });
                            }
                        });
                break;
            case 9:
                startActivity(getIntent(this,new CustomActivity()));
                break;
            default:
                ToastUtil.showShortToastCenter(this, mainAdapter.list.get(position));
                break;
        }
    }

    @Override
    public void error(String error) {
        tipDialog.dismiss();
        ToastUtil.showShortToastCenter(this, "请求出错");
    }

    @Override
    public void success(List<String> list) {
        refreshlayout.setVisibility(View.VISIBLE);
        refreshlayout.setRefreshing(false);
        if (tipDialog != null) {
            tipDialog.dismiss();
        }
        mainAdapter.setData(list);
    }

    @Override
    public void onRefresh() {
        mainAdapter.clear();
        presenter.getMainData();
    }
}