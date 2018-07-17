package com.smartpoint.marquee.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.smartpoint.marquee.R;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * Created by Administrator on 2018/5/3
 * 邮箱 18780569202@163.com
 */
public class JniActivity extends AppCompatActivity {
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, JniActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLibrary.INSTANCE.printf("Hello, World/n");
            }
        });
    }
    public interface CLibrary  extends Library{
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isAndroid()?"msvcrt":"c"),CLibrary.class);
        void printf(String format, Object... args);
    }
}
