package com.smartpoint.marquee.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.skyfishjy.library.RippleBackground;
import com.smartpoint.marquee.R;
import com.smartpoint.marquee.base.BaseActivity;

/**
 * Created by Administrator on 2018/7/17
 * 邮箱 18780569202@163.com
 */
public class LeonidsActivity extends BaseActivity{
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, LeonidsActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_leonids;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {
        RippleBackground rippleBackground= findViewByIdNoCast(R.id.content);
        rippleBackground.startRippleAnimation();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
