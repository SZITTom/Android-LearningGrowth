package com.yun.android_learninggrowth.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yun.android_learninggrowth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ControlsFragment extends Fragment {


    public ControlsFragment() {
        // Required empty public constructor
    }

    public static ControlsFragment newInstance(String tabTitle) {
        ControlsFragment fragment = new ControlsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_controls, container, false);
    }


}
