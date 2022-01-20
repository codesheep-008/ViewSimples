package com.codesheep008.viewsimples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.codesheep008.viewsimples.class01.FlowLayoutActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void class04(View view) {
//        Intent intent = new Intent(this, ClickEventActivity.class);
//        startActivity(intent);
//        Intent intent = new Intent(this, DispatchActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(this, FlowLayoutActivity.class);
        startActivity(intent);
    }
}