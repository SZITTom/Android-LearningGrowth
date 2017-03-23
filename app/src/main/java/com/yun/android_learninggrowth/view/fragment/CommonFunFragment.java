package com.yun.android_learninggrowth.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.adapter.TextAdapter;
import com.yun.android_learninggrowth.base.BaseFragment;
import com.yun.android_learninggrowth.utils.JumpActivityHelper;
import com.yun.android_learninggrowth.view.activity.DebounceClickActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CommonFunFragment extends BaseFragment {


    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    @BindView(R.id.fun_listview)
    ListView mListView;

    public CommonFunFragment() {
    }

    public static CommonFunFragment newInstance(String param1) {
        CommonFunFragment fragment = new CommonFunFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_common_fun;
    }

    @Override
    protected void initView() {
        List<String> datas = new ArrayList<>();
        datas.add("手机验证码，延时60秒实现");
        datas.add("2");
        datas.add("3");
        datas.add("4");
        TextAdapter textAdapter = new TextAdapter(getActivity(), datas);
        mListView.setAdapter(textAdapter);
    }

    @Override
    protected void initListeren() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://手机验证码
                        JumpActivityHelper.jumpActivity(getActivity(), DebounceClickActivity.class);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
