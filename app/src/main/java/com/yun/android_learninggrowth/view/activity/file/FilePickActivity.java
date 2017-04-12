package com.yun.android_learninggrowth.view.activity.file;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseAppCompatActivity;

import java.util.List;

import butterknife.BindView;

/**
 * 轻量级的文件选择器，可以检索手机目录选择文件
 * https://github.com/leonHua/LFilePicker
 */
public class FilePickActivity extends BaseAppCompatActivity {

    public static int REQUESTCODE_FROM_ACTIVITY = 1000;
    public static int REQUESTCODE_FROM_FRAGMENT = 1001;

    @BindView(R.id.rg_iconstyle)
    RadioGroup mRgIconType;
    @BindView(R.id.rg_backarrawstyle)
    RadioGroup mRgBackArrawType;
    private int mIconType;
    private int mBackArrawType;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_file_pick;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        mRgIconType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_yellow:
                        mIconType = Constant.ICON_STYLE_YELLOW;
                        break;
                    case R.id.radio_green:
                        mIconType = Constant.ICON_STYLE_GREEN;
                        break;
                    case R.id.radio_blue:
                        mIconType = Constant.ICON_STYLE_BLUE;
                        break;
                }
            }
        });
        mRgBackArrawType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.arrawback_styleone:
                        mBackArrawType = Constant.BACKICON_STYLEONE;
                        break;
                    case R.id.arrawback_styletwo:
                        mBackArrawType = Constant.BACKICON_STYLETWO;
                        break;
                    case R.id.arrawback_stylethree:
                        mBackArrawType = Constant.BACKICON_STYLETHREE;
                        break;
                }
            }
        });
    }

    public void openFromActivity(View view) {
        new LFilePicker()
                .withActivity(this)
                .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                .withTitle("文件选择")
                .withIconStyle(mIconType)
                .withBackIcon(mBackArrawType)
                //.withFileFilter(new String[]{"txt", "png", "docx"})
                .start();
    }

    public void openFragmentActivity(View view) {
        startActivity(new Intent(this, FragmentActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                //for (String s : list) {
                //    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                //}
                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
