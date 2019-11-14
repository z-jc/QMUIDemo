package com.qmui.android.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmui.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @particulars
 * @time 2019\6\19 0019 8:59
 * @class describe
 */
public class QrCodeAdapter extends RecyclerView.Adapter<QrCodeAdapter.MyHolder> {

    private Context mContext;
    public List<String> list = null;

    public QrCodeAdapter(Context context) {
        this.mContext = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qrcode, viewGroup, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder viewHolder, final int position) {
        String data = list.get(position);
        viewHolder.textView.setText(data);
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
     * 添加数据
     *
     * @param data
     */
    public void addData(String data) {
        this.list.add(0, data);
        this.notifyDataSetChanged();
    }

    /**
     * 加载添加数据
     *
     * @param list
     */
    public void addDataAll(List<String> list) {
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

    class MyHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

}