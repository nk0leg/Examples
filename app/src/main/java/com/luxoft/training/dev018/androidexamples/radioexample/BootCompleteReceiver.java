package com.luxoft.training.dev018.androidexamples.radioexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//            SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.prefs_name), 0);
//            long alarmMills = settings.getLong(context.getString(R.string.prefs_alarm_tag), 0);
//            AlarmSetter alarmSetter = new AlarmSetter(context);
//            alarmSetter.setAlarm(alarmMills);
//        }
    }
}
