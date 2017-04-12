package com.yun.android_learninggrowth.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.view.adapter.TextAdapter;
import com.yun.android_learninggrowth.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 安卓版本新功能
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_listview)
    ListView mListView;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String tabTitle) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        List<String> datas = new ArrayList<>();
        datas.add("1");
        datas.add("2");
        datas.add("3");
        datas.add("4");
        datas.add("5");
        TextAdapter textAdapter = new TextAdapter(getActivity(), datas);
        mListView.setAdapter(textAdapter);
    }

    @Override
    protected void initListeren() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
