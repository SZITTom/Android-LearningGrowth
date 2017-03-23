package com.yun.android_learninggrowth.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseAppCompatActivity;
import com.yun.android_learninggrowth.view.fragment.CommonFunFragment;
import com.yun.android_learninggrowth.view.fragment.ControlsFragment;
import com.yun.android_learninggrowth.view.fragment.FrameUseFragment;
import com.yun.android_learninggrowth.view.fragment.HomeFragment;
import com.yun.library.tablayout.CommonTabLayout;
import com.yun.library.tablayout.TabEntity;
import com.yun.library.tablayout.listener.CustomTabEntity;
import com.yun.library.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity {

    @BindView(R.id.main_tab_bottom)
    CommonTabLayout mCommonTabLayout;

    private ArrayList<Fragment> mFragments;

    private int[] mIconSelectIds = {
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp,
            R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};
    private int[]  mIconUnselectIds= {
            R.drawable.ic_home_gray_24dp, R.drawable.ic_home_gray_24dp,
            R.drawable.ic_home_gray_24dp, R.drawable.ic_home_gray_24dp};

    @BindArray(R.array.main_tab_bottom)
    String [] mTitles;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int mCurrentTab = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }


    /**
     * 防止fragment重叠
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initData() {
        initTabData();
    }
    /**
     * 设置底部Tab
     */
    private void initTabData() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        initFragments();

        mCommonTabLayout.setTabData(mTabEntities, this, R.id.container, mFragments);
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mCurrentTab = position;
                setTabData(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        setTabData(mCurrentTab);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        HomeFragment mHomeFragment = HomeFragment.newInstance(mTabEntities.get(0).getTabTitle());
        ControlsFragment mControlsFragment = ControlsFragment.newInstance(mTabEntities.get(1).getTabTitle());
        CommonFunFragment mCommonFunFragment = CommonFunFragment.newInstance(mTabEntities.get(2).getTabTitle());
        FrameUseFragment mFrameUseFragment = FrameUseFragment.newInstance(mTabEntities.get(3).getTabTitle());

        mFragments.add(mHomeFragment);
        mFragments.add(mControlsFragment);
        mFragments.add(mCommonFunFragment);
        mFragments.add(mFrameUseFragment);
    }

    private void setTabData(int position) {
        mCommonTabLayout.setCurrentTab(mCurrentTab);
        mCommonTabLayout.getContentView(mCurrentTab).setBackgroundResource(R.color.tab_bottom_bg);
        for (int i = 0; i < mTitles.length; i++) {
            mCommonTabLayout.getContentView(i).setPadding(0, 15, 0, 8);
            mCommonTabLayout.getContentView(i).setBackgroundResource(R.color.tab_bottom_bg);
        }
        mCommonTabLayout.getContentView(position).setBackgroundResource(R.color.tab_bottom_bg);
    }
}
