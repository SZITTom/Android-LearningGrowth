package com.yun.android_learninggrowth.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseAppCompatActivity;
import com.yun.android_learninggrowth.utils.AdvancedCountdownTimer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 手机验证码功能
 */
public class DebounceClickActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_regiter_get_code)
    TextView tvRegiterGetCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    private CountTimers countTimers;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_debounce_click;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.tv_regiter_get_code)
    void onGetCode(View view) {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvRegiterGetCode.setText((millisUntilFinished / 1000) + "秒");
            tvRegiterGetCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tvRegiterGetCode.setEnabled(true);
            tvRegiterGetCode.setText("获取验证码");
        }
    };

    @OnClick(R.id.tv_get_code)
    void onNewGetCode(View view) {
        countTimers = new CountTimers(60 * 1000, 1000);
        countTimers.start();
    }

    @Override
    protected void onResume() {
        if (countTimers != null) {
            countTimers.resume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (countTimers != null) {
            countTimers.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (countTimers != null) {
            countTimers.cancel();
        }
        super.onDestroy();
    }

    class CountTimers extends AdvancedCountdownTimer {

        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         */
        public CountTimers(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished, int percent) {
            tvGetCode.setText((millisUntilFinished / 1000) + "秒");
            tvGetCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tvRegiterGetCode.setEnabled(true);
            tvRegiterGetCode.setText("获取验证码");
        }
    }

}
