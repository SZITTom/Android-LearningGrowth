package com.yun.android_learninggrowth.view.activity.file;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseAppCompatActivity;
import com.yun.android_learninggrowth.view.fragment.file.FileFragment;

public class FragmentActivity extends BaseAppCompatActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FileFragment fileFragment = FileFragment.newInstance();
        transaction.replace(R.id.container, fileFragment);
        transaction.commit();
    }
}
