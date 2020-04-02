package com.qmui.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmui.android.R;

/**
 * Author : Z-JC
 * Date : 2020/4/2
 * Description :
 */
public class GridFragment extends Fragment {

    public static GridFragment create() {
        return new GridFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid,null);
        return view;
    }

}