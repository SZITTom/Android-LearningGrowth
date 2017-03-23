package com.yun.library.tablayout;

import com.yun.library.tablayout.listener.CustomTabEntity;

/**
 * Created by SZITTom on 2017/3/23.
 */

public class TabEntity implements CustomTabEntity{

    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }

}
