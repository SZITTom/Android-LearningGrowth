package com.yun.android_learninggrowth.base.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;
/**
 * 抽象的LiseView，GrdView实现类,封装了内容为View的公共操作.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	private List<T> datas;
	private int layoutId;
	protected Context context;

	public CommonAdapter(Context context, List<T> datas) {
		this.datas = datas;
		this.context = context;
	}
	
	@Override
	public int getViewTypeCount() {
		this.layoutId = getLayoutId();
		return super.getViewTypeCount();
	}

	@Override
	public int getCount() {
		return datas == null || datas.isEmpty() ? 0 : datas.size();
	}

	@Override
	public T getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.get(context, convertView, layoutId);
		bindView(viewHolder,position,getItem(position));
		return viewHolder.getConvertView();
	}
	
	public void setDatas(List<T> datas){
		this.datas = datas;
		notifyDataSetChanged();
	}
	
	public List<T> getDatas(){
		return datas;
	}

	public void setSelection(final ListView mListView, final int selection){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if(selection < datas.size()){
					mListView.setSelection(selection);
				}
			}
		},1*1000);
	}

	/**
	 * 由子类重写返回布局ID
	 * @return
	 */
	public abstract int getLayoutId();

	public abstract void bindView(ViewHolder viewHolder,int position,T item);
}
