package com.smartpoint.marquee.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.smartpoint.marquee.R;
import com.smartpoint.marquee.base.BaseActivity;
import com.smartpoint.view.MyDrawView;
import com.smartpoint.view.TuyaView;

/**
 * Created by Administrator on 2018/7/18
 * 邮箱 18780569202@163.com
 */
public class ValueAnimatorActivity extends BaseActivity {
    private RelativeLayout ll;
    private MyDrawView myDrawView;
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
        ll = findViewByIdNoCast(R.id.ll);
        myDrawView = findViewByIdNoCast(R.id.myDrawView);
        findViewByIdNoCast(R.id.btn).setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                myDrawView.clear();
                break;
        }
    }
    private void initTuYaView(){
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int screenWidth = defaultDisplay.getWidth();
        int screenHeight = defaultDisplay.getHeight();
        TuyaView tuyaView = new TuyaView(this,screenWidth,screenHeight);
        ll.addView(tuyaView);
        tuyaView.requestFocus();
        tuyaView.selectPaintSize(5);
    }
    private void initMyDrawView(){
        MyDrawView myDrawView = new MyDrawView(this);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int screenWidth = defaultDisplay.getWidth();
        int screenHeight = defaultDisplay.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth,screenHeight);
        myDrawView.setLayoutParams(params);
        ll.addView(myDrawView);
        myDrawView.requestFocus();
    }
}
