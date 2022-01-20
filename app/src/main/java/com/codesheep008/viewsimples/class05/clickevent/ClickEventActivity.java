package com.codesheep008.viewsimples.class05.clickevent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codesheep008.viewsimples.R;

/**
 * View的响应事件  onClick与onTouch事件的关系
 * onTouch返回false的情况,会执行onClick
 * onTouch返回true的情况,不会执行onClick
 * 源码主要看View.java的dispatchtouchevent
 */
public class ClickEventActivity extends AppCompatActivity {
    private static final String TAG = ClickEventActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickevent);
        Button btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(v -> {
            Log.e(TAG, "btn_click onclick");
        });
        btn_click.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "btn_click onTouch");
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
