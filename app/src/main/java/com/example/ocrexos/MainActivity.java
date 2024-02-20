package com.example.ocrexos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Alex", "onCreate() called!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Alex", "onStart() called!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Alex", "onResume() called!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Alex", "onPause() called!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Alex", "onStop() called!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Alex", "onDestroy()  called!");
    }
}