package com.smartpoint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2018/7/18
 * 邮箱 18780569202@163.com
 */
public class MyDrawView extends View implements View.OnTouchListener{
    float curX,curY;
    float startX,startY;
    public MyDrawView(Context context) {
        super(context);
        init();
    }
    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
    }
    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
        paint.setColor(Color.RED);
    }
    private Paint paint;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            startX = event.getX();
            startY = event.getY();
        }else if (event.getAction()==MotionEvent.ACTION_UP){

        }else if (event.getAction()==MotionEvent.ACTION_MOVE){
            curX = event.getX();
            curY = event.getY();
        }
        return true;
    }
}
