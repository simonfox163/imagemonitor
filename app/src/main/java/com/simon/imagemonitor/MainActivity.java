package com.simon.imagemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.simon.monitor_api.MonitorManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MonitorManager().init();
    }
}