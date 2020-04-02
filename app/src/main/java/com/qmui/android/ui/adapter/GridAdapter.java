package com.qmui.android.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.qmui.android.R;
import com.qmui.android.bean.NativeEntity;
import com.qmui.android.util.AdManager;
import com.qq.e.o.HXInfoAdStyle;
import com.qq.e.o.utils.DisplayUtil;

import java.util.List;

/**
 * Author : Z-JC
 * Date : 2020/4/2
 * Description :
 */
public class GridAdapter extends BaseQuickAdapter<NativeEntity, BaseViewHolder> {

    public static int INTERVAL = 5;//四条数据一组（3条数据加一条广告）

    public GridAdapter(@NonNull List<NativeEntity> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<NativeEntity>() {
            @Override
            protected int getItemType(NativeEntity nativeEntity) {
                return nativeEntity.getItemType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(NativeEntity.TYPE_DATA, R.layout.item_native_grid_data)
                .registerItemType(NativeEntity.TYPE_AD, R.layout.item_native_grid_ad);
    }

    @Override
    public int getItemViewType(int position) {
        if ((position + (INTERVAL - 1)) % INTERVAL == 0) {
            return NativeEntity.TYPE_AD;
        } else {
            return NativeEntity.TYPE_DATA;
        }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NativeEntity item) {
        switch (helper.getItemViewType()) {
            case NativeEntity.TYPE_DATA:
                helper.setText(R.id.tv_value, item.getContent());
                break;
            case NativeEntity.TYPE_AD:
                FrameLayout frameLayout = helper.getView(R.id.fl_ad);
                AdManager.getInstance().showNativeAD((Activity) helper.itemView.getContext(), frameLayout,
                        (int) DisplayUtil.getScreenWidthDp(helper.itemView.getContext()) / 2,
                        0, HXInfoAdStyle.RIGHT_IMAGE);
                break;
        }
    }
}