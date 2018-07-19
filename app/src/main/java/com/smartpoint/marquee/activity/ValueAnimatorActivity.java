package com.smartpoint.marquee.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.smartpoint.marquee.R;
import com.smartpoint.marquee.base.BaseActivity;

/**
 * Created by Administrator on 2018/7/18
 * 邮箱 18780569202@163.com
 */
public class ValueAnimatorActivity extends BaseActivity {
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ValueAnimatorActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_value_animator;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
