package com.yun.android_learninggrowth.view.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.adapter.CommonAdapter;
import com.yun.android_learninggrowth.base.adapter.ViewHolder;

import java.util.List;

/**
 * Created by SZITTom on 2017/3/23.
 */
public class TextAdapter extends CommonAdapter<String> {

    public TextAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text;
    }

    @Override
    public void bindView(ViewHolder viewHolder, int position, String item) {
        TextView textView = viewHolder.findViewById(R.id.tv_text);
        textView.setText(item);
    }
}
