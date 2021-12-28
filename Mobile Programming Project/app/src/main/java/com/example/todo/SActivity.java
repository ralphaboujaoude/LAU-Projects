package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {

                startActivity(new Intent(SActivity.this, MainActivity.class));
                finish();
            }

        }, 5000);
    }
}