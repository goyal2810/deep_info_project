package com.example.deepinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_animation);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent mainActivity = new Intent(LogoutAnimation.this, MainActivity.class);
//                startActivity(mainActivity);
//                finish();
//            }
//        }, 3000);
    }
}
