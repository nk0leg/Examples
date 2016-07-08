package com.luxoft.training.dev018.androidexamples.radioexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
//        Log.i(AlarmReceiver.class.getName(), "onReceive");
//        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_LONG).show();
//
//        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.prefs_name), 0);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.remove(context.getString(R.string.prefs_alarm_tag));
//        editor.apply();
//
//        Intent startActivityIntent = new Intent(context, HelloRadioMainActivity.class);
//        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(startActivityIntent);
//
//        Intent startServiceIntent = new Intent(context, StreamingService.class);
//        startServiceIntent.setAction(StreamingService.ACTION_PLAY);
//        startServiceIntent.putExtra(context.getString(R.string.intent_alarm_tag), true);
//        context.startService(startServiceIntent);
    }

}