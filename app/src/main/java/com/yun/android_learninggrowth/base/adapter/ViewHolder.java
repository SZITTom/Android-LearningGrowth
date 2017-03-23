package com.yun.android_learninggrowth.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

public class ViewHolder {

	private SparseArray<View> views;
	private View convertView;
	
	public ViewHolder(Context context,int layoutId){
		this.views = new SparseArray<View>();
		this.convertView = LayoutInflater.from(context).inflate(layoutId,null);
		this.convertView.setTag(this);
	}
	
	public static ViewHolder get(Context context,View convertView,int layoutId){
		if (convertView == null) {
			return new ViewHolder(context,layoutId);
		}
		return (ViewHolder) convertView.getTag();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends View> T findViewById(int viewId){
		View view = views.get(viewId);
		if (null == view) {
			view = convertView.findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}
	
	public View getConvertView(){
		return convertView;
	}
}
