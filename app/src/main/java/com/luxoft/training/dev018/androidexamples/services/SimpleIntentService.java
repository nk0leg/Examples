package com.luxoft.training.dev018.androidexamples.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class SimpleIntentService extends IntentService {

    public SimpleIntentService() {
        super(SimpleIntentService.class.getName());
    }

    final String TAG = "SimpleIntentService";

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int tm = intent.getIntExtra("time", 2);
        String label = intent.getStringExtra("label");
        Log.d(TAG, "onHandleIntent start " + label);
        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onHandleIntent end " + label);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
