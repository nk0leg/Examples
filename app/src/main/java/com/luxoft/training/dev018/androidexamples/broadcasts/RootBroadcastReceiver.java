package com.luxoft.training.dev018.androidexamples.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RootBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "RootBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive " + intent.getAction());
    }
}
