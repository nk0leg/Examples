package com.luxoft.training.dev018.androidexamples;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class Dev018App extends Application {

    private static final String TAG = "Dev018App";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }
}
