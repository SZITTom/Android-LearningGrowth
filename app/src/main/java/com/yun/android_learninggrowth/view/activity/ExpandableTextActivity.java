package com.yun.android_learninggrowth.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseAppCompatActivity;
import com.yun.android_learninggrowth.bean.DataBean;
import com.yun.android_learninggrowth.view.adapter.ExpandableTextAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 展开折叠功能的TextView
 */
public class ExpandableTextActivity extends BaseAppCompatActivity {

    @BindView(R.id.main_rv)
    RecyclerView recyclerView;

    ExpandableTextAdapter adapter;

    ArrayList<DataBean> models;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_expandable_text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {
        models=new ArrayList<>();
        String[] arrays=getResources().getStringArray(R.array.news);
        for (String array : arrays) {
            DataBean bean=new DataBean();
            bean.setText(array);
            models.add(bean);
        }

        recyclerView= (RecyclerView) findViewById(R.id.main_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ExpandableTextAdapter(this, models);
        recyclerView.setAdapter(adapter);
    }
}
