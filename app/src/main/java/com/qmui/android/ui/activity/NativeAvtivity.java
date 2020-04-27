package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmui.android.ui.fragment.GridFragment;
import com.qmui.android.ui.fragment.LinearFragment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Z-JC
 * Date : 2020/4/2
 * Description :
 */
public class NativeAvtivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"线性布局", "表格布局"};

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.tabLayout)
    public SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;

    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("列表原生广告测试");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });

        mFragments.add(LinearFragment.create());
        mFragments.add(GridFragment.create());

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setCurrentTab(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}