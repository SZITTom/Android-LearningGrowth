package com.yun.android_learninggrowth.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>选择适配器</h2>
 * <br>准备工作：
 * <br>第一步：创建你的Item并实现CheckAdapter.CheckItem接口
 * <br>第二步：创建你的适配器并继承于CheckAdapter并同时通过泛型指定Item类型
 * <br>第三步：在构造函数中传给父类Item集合
 * <br>第四步：在getView()方法中调用handleCheckBox()方法处理CheckBox
 * <br>第五步：在ListView的OnItemClickListener中调用clickIitem()方法处理点击事件
 * <br>至此，全部准备工作已经完结
 * <br>
 * <br>接下来你可以：
 * <br>调用setSingleMode()方法修改选择模式为单选模式（默认是多选模式）
 * <br>调用enableCheckMode()方法开启选择模式
 * <br>调用cancelCheckMode()方法取消选择模式
 * <br>调用isEnableCheckMode()方法判断是否正处于选择模式
 * <br>调用getCheckedItems()方法获取全部选中的项
 * <br>调用deleteCheckedItems()方法删除全部选中的项
 * <p/>
 * classes: com.yun.picassodome.utils.CheckAdapter
 *
 * @author Administrator
 * @date 2016/2/25
 * @description
 */
public abstract class CheckAdapter<T extends CheckAdapter.CheckItem> extends
        BaseAdapter {

    protected Context context;
    private List<T> items;    //列表项
    private boolean enabledCheckMode;    //激活选择模式
    private boolean singleMode;    //单选模式
    private int currentCheckedPosition = -1;
    private
    @LayoutRes
    int layoutId;

    public CheckAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getViewTypeCount() {
        this.layoutId = getLayoutId();
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(context, convertView, layoutId);
        bindView(viewHolder, position, getItem(position));
        return viewHolder.getConvertView();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    /**
     * 是否是单选模式
     *
     * @return
     */
    public boolean isSingleMode() {
        return singleMode;
    }

    /**
     * 设置单选模式，默认是复选模式
     *
     * @param singleMode
     */
    public void setSingleMode(boolean singleMode) {
        this.singleMode = singleMode;
    }

    /**
     * 处理复选框
     *
     * @param compoundButton
     * @param t
     */
    public void handleCompoundButton(CompoundButton compoundButton, T t) {
        compoundButton.setChecked(t.isChecked());
        compoundButton.setVisibility(isEnabledCheckMode() ? View.VISIBLE : View.GONE);
    }

    /**
     * 激活选择模式
     */
    public void enableCheckMode() {
        if (enabledCheckMode) {
            return;
        }
        enabledCheckMode = true;
        notifyDataSetChanged();
    }


    /**
     * 取消选择模式
     */
    public void cancelCheckMode() {
        if (!enabledCheckMode) {
            return;
        }
        enabledCheckMode = false;
        for (T item : items) {
            item.setChecked(false);
        }
        notifyDataSetChanged();
    }

    /**
     * 判定是否已激活选择模式
     *
     * @return
     */
    public boolean isEnabledCheckMode() {
        return enabledCheckMode;
    }

    /**
     * 点击了某一项
     *
     * @param postion
     * @return true：已经激活了选择模式并且设置成功；false：尚未激活选择模式并且设置失败
     */
    public boolean clickItem(int postion) {
        if (isEnabledCheckMode()) {
            if (postion < items.size()) {
                if (singleMode) {
                    if (currentCheckedPosition == -1 || currentCheckedPosition == postion) {
                        T item = items.get(postion);
                        item.setChecked(!item.isChecked());
                    } else {
                        items.get(currentCheckedPosition).setChecked(false);
                        items.get(postion).setChecked(true);
                    }
                    currentCheckedPosition = postion;
                } else {
                    T item = items.get(postion);
                    item.setChecked(!item.isChecked());
                }
                notifyDataSetChanged();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取选中的项
     *
     * @return
     */
    public List<T> getCheckedItems() {
        List<T> checkedItems = new ArrayList<T>();
        if (items != null) {
            for (T item : items) {
                if (item.isChecked()) {
                    checkedItems.add(item);
                }
            }
        } else {
            return null;
        }
        return checkedItems;
    }

    /**
     * 删除选中的项
     *
     * @return
     */
    public List<T> deleteCheckedItems() {
        List<T> checkedItems = new ArrayList<T>();
        Iterator<T> iterator = items.iterator();
        T item;
        while (iterator.hasNext()) {
            item = iterator.next();
            if (item.isChecked()) {
                checkedItems.add(item);
                iterator.remove();
            }
        }
        notifyDataSetChanged();
        return checkedItems;
    }

    /**
     * 由子类重写返回布局ID
     *
     * @return
     */
    public abstract int getLayoutId();

    public abstract void bindView(ViewHolder viewHolder, int position, T item);

    public interface CheckItem {
        boolean isChecked();

        void setChecked(boolean checked);
    }
}
