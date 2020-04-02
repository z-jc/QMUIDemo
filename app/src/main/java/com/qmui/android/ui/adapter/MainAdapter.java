package com.qmui.android.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmui.android.R;

import java.util.List;

/**
 * @author Administrator
 * @particulars
 * @time 2019\6\19 0019 8:59
 * @class describe
 */
public class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public OnClicklinter onClicklinter;
    public MainAdapter(List<String> data,OnClicklinter clicklinter) {
        super(R.layout.item_main, data);
        this.onClicklinter = clicklinter;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.textview, item);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClicklinter != null){
                    onClicklinter.onClick(helper.getLayoutPosition());
                }
            }
        });
    }

    public interface OnClicklinter {
        void onClick(int position);
    }
}