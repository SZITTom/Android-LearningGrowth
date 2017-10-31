package com.yun.android_learninggrowth.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.bean.DataBean;
import com.yun.library.weight.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by yeyunfei on 2017/10/31.
 *
 */

public class ExpandableTextAdapter extends RecyclerView.Adapter<ExpandableTextAdapter.MainViewHolder> {
    Context context;
    ArrayList<DataBean> models;

    public ExpandableTextAdapter(Context context, ArrayList<DataBean> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_adapter, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.expandable_text.setText(models.get(position).getText(), models.get(position).isCollapsed());
        holder.expandable_text.setListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(boolean isExpanded) {
                models.get(position).setCollapsed(isExpanded);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        ExpandableTextView expandable_text;

        public MainViewHolder(View itemView) {
            super(itemView);

            expandable_text= (ExpandableTextView) itemView.findViewById(R.id.expandable_text);
        }
    }
}
