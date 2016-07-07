package com.luxoft.training.dev018.androidexamples.services;

import android.app.IntentService;
import android.content.Intent;

public class SimpleIntentService extends IntentService {

    public SimpleIntentService() {
        super(SimpleIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
