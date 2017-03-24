package com.yun.android_learninggrowth.view.fragment;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.adapter.TextAdapter;
import com.yun.android_learninggrowth.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 框架的运用.
 */
public class FrameUseFragment extends BaseFragment {

    @BindView(R.id.fra_listview)
    ListView mListView;

    public FrameUseFragment() {
    }

    public static FrameUseFragment newInstance(String tabTitle) {
        FrameUseFragment fragment = new FrameUseFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_frame_use;
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



}
