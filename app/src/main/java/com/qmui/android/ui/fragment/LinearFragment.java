package com.qmui.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmui.android.R;
import com.qmui.android.bean.NativeEntity;
import com.qmui.android.ui.adapter.LinearAdapter;
import com.qq.e.o.utils.ILog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author : Z-JC
 * Date : 2020/4/2
 * Description :
 */
public class LinearFragment extends Fragment {

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    public Unbinder unbinder;
    public LinearAdapter linearAdapter;

    public static LinearFragment create() {
        return new LinearFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linear, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        linearAdapter = new LinearAdapter(null);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(linearAdapter);
        linearAdapter.setNewData(getDataList());

        //开启动画（默认为渐显效果）
        //linearAdapter.openLoadAnimation();
    }

    public List<NativeEntity> getDataList() {
        List<NativeEntity> nativeEntities = new ArrayList<>();
        NativeEntity nativeEntity = null;
        int id = 0;
        int size = 100;         //接口回来数据总数
        for (int index = 0; index < size + (size / (LinearAdapter.INTERVAL - 1)); index++) {
            if ((index + LinearAdapter.INTERVAL - 1) % LinearAdapter.INTERVAL == 0) {
                nativeEntity = new NativeEntity(NativeEntity.TYPE_AD);
            } else {
                nativeEntity = new NativeEntity(NativeEntity.TYPE_DATA);
                nativeEntity.setContent("条目" + id);
                id++;
            }
            nativeEntities.add(nativeEntity);
        }
        ILog.e("列表总条数:" + nativeEntities.size());
        return nativeEntities;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}