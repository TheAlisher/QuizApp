package com.example.quiz.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quiz.ui.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.start(this);
        finish();
    }
}