package com.simon.imagemonitor;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by simon on 2021/3/9.
 */

public class MonitorApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
