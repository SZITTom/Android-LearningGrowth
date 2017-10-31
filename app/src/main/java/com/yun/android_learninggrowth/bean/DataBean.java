package com.yun.android_learninggrowth.bean;

/**
 * Created by yeyunfei on 2017/10/31.
 */

public class DataBean {
    String text;
    boolean isCollapsed=true;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
    }
}
