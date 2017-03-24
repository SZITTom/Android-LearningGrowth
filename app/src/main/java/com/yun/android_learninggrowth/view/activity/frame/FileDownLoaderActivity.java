package com.yun.android_learninggrowth.view.activity.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yun.android_learninggrowth.R;

/**
 * 安卓系统多任务文件下载引擎。Android 文件下载引擎，稳定、高效、灵活、简单易用
 * https://github.com/lingochamp/FileDownloader
 * 多任务、断点继续高并发性,使用简单,单/ NotSingle-process
 *
 */
public class FileDownLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_down_loader);
    }
}
