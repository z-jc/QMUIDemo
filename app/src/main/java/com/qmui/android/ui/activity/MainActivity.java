package com.qmui.android.ui.activity;

import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.PuzzleCallback;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.mvp.presenter.MainPresenter;
import com.qmui.android.mvp.presenter.MainPresenterImpl;
import com.qmui.android.mvp.view.MainView;
import com.qmui.android.ui.adapter.MainAdapter;
import com.qmui.android.ui.glide.GlideEngine;
import com.qmui.android.util.SystemUtil;
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

public class MainActivity extends BaseActivity implements MainAdapter.OnClicklinter, MainView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    public SwipeRefreshLayout refreshlayout;
    private MainAdapter mainAdapter;

    private MainPresenter presenter;
    private QMUITipDialog tipDialog;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initTopBar();
    }

    private void initTopBar() {
        presenter = new MainPresenterImpl(this);
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("控件列表");

        mainAdapter = new MainAdapter(null, this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(mainAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_main_header, null);
        mainAdapter.setHeaderView(view);

        refreshlayout.setOnRefreshListener(this);
        refreshlayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        QMUIRoundButton roundbutton = view.findViewById(R.id.roundbutton);
        roundbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new QMUIDialog.MessageDialogBuilder(MainActivity.this)
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
                                mainAdapter.getData().clear();
                                mainAdapter.notifyDataSetChanged();
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
        });
    }

    @Override
    public void onClick(int position) {
        switch (position) {
            case 1:
                startActivity(getIntent(this, new ButtonActivity()));
                break;
            case 2:
                startActivity(getIntent(this, new ProgresBarActivity()));
                break;
            case 3:
                startActivity(getIntent(this, new TabActivity()));
                break;
            case 4:
                ToastUtil.showShortToastCenter(this, "该功能暂未开放");
                break;
            case 5:
                startActivity(getIntent(this, new MvcActivity()));
                break;
            case 6:
                startActivity(getIntent(this, new OkgoActivity()));
                break;
            case 7:
                EasyPhotos.createCamera(this)//参数说明：上下文
                        .setFileProviderAuthority(SystemUtil.getPackageName() + ".custom.fileprovider")//参数说明：见下方`FileProvider的配置`
                        .start(new SelectCallback() {
                            @Override
                            public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
                                Log.e(getClass().getSimpleName(), "拍照完成:" + Arrays.toString(paths.toArray()));
                            }
                        });
                break;
            case 8:
                startActivity(getIntent(this, new QrCodeActivity()));
                break;
            case 9:
                EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                        .setFileProviderAuthority(SystemUtil.getPackageName() + ".custom.fileprovider")
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
            case 10:
                startActivity(getIntent(this, new CustomActivity()));
                break;
            case 11:
                startActivity(getIntent(this, new AdActivity()));
                break;
            case 12:
                startActivity(getIntent(this, new VideoActivity()));
                break;
            case 13:
                startActivity(getIntent(this, new ImageComPressionActivity()));
                break;
            case 14:
                startActivity(getIntent(this, new JiGuangShareActivity()));
                break;
            case 15:
                startActivity(getIntent(this, new LogisticsActivity()));
                break;
            case 16:
                startActivity(getIntent(this, new NativeAvtivity()));
                break;
            case 17:
                EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                        .setPuzzleMenu(false)
                        .setCleanMenu(false)
                        .start(new SelectCallback() {
                            @Override
                            public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
                                SkinActivity.startAct(MainActivity.this, paths.get(0));
                            }
                        });
                break;
            case 18:
                startActivity(getIntent(this, new JsoupActivity()));
                break;
            default:
                ToastUtil.showShortToastCenter(this, mainAdapter.getData().get(position));
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
        mainAdapter.setNewData(list);
    }

    @Override
    public void onRefresh() {
        mainAdapter.getData().clear();
        presenter.getMainData();
    }

}