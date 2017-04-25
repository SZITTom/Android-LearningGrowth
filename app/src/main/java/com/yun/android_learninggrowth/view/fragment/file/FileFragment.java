package com.yun.android_learninggrowth.view.fragment.file;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.lfilepickerlibrary.LFilePicker;
import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseFragment;
import com.yun.android_learninggrowth.view.activity.file.FilePickActivity;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FileFragment extends BaseFragment {

    private FileFragment mFragment;
    @BindView(R.id.tv)
    TextView mTv;

    public FileFragment() {
        // Required empty public constructor
    }

    public static FileFragment newInstance() {
        FileFragment fragment = new FileFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_file;
    }

    @Override
    protected void initView() {
        this.mFragment = this;

    }

    @Override
    protected void initListeren() {
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LFilePicker().withSupportFragment(mFragment)
                        .withRequestCode(FilePickActivity.REQUESTCODE_FROM_FRAGMENT)
                        .withTitle("Open From Fragment")
                        .start();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FilePickActivity.REQUESTCODE_FROM_FRAGMENT) {
                List<String> list = data.getStringArrayListExtra("paths");
                for (String s : list) {
                    Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
