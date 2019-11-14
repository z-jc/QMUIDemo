package com.qmui.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmui.android.R;
import com.qmuiteam.qmui.widget.QMUIFontFitTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @particulars
 * @time 2019\6\19 0019 8:59
 * @class describe
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.BaseHolder> {

    private Context mContext;
    public List<String> list = null;
    private OnLister lister;

    public MainAdapter(Context context, OnLister lister) {
        this.mContext = context;
        this.list = new ArrayList<>();
        this.lister = lister;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, viewGroup, false);
        BaseHolder holder = new BaseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseHolder viewHolder, final int position) {
        String data = list.get(position);
        viewHolder.textView.setText(data);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lister != null) {
                    lister.lister(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    /**
     * 刷新数据
     *
     * @param list
     */
    public void setData(List<String> list) {
        if (this.list != null) {
            if (this.list.size() > 0) {
                this.list.clear();
            }
        }
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    public void clear() {
        if (this.list != null) {
            if (this.list.size() > 0) {
                this.list.clear();
            }
        }
        this.notifyDataSetChanged();
    }

    class BaseHolder extends RecyclerView.ViewHolder {
        public QMUIFontFitTextView textView;

        public BaseHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

    public interface OnLister {
        void lister(int position);
    }
}