package com.qmui.android.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUIFontFitTextView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hugeterry(http://hugeterry.cn)
 */
public class TabActivity extends BaseActivity {

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    private List<String> mData;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_tab;
    }

    public void initData(int index) {
        mData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mData.add("pager" + index + " 第" + i + "个item");
        }
    }

    public void initData() {
        mData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mData.add("pager" + 1 + " 第" + i + "个item");
        }
    }

    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("阴影背景");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });

        //设置TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 1; i < 8; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
        }
        //TabLayout的切换监听
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initData(tab.getPosition() + 1);
                setScrollViewContent();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setScrollViewContent();
    }

    /**
     * 刷新ScrollView的内容
     */
    private void setScrollViewContent() {
        //NestedScrollView下的LinearLayout
        LinearLayout layout = (LinearLayout) findViewById(R.id.ll_sc_content);
        layout.removeAllViews();
        for (int i = 0; i < mData.size(); i++) {
            View view = View.inflate(TabActivity.this, R.layout.item_main, null);
            ((QMUIFontFitTextView) view.findViewById(R.id.textview)).setText(mData.get(i));
            //动态添加子View
            layout.addView(view, i);
        }
    }
}