package com.luxoft.training.dev018.androidexamples.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SharedPrefsActivity extends AppCompatActivity {
    private static final String MY_SETTINGS = "com.luxoft.training.dev018.androidexamples.sharedpreferences.SharedPrefsActivity.MYSETTING";
    private static final String KEY_ST_PETERSBURG_FOUND_DATE = "KEY_ST_PETERSBURG_FOUND_DATE";
    private static final String TAG = "SharedPrefsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE);
        Log.e(TAG, " " + sharedPreferences.getInt(KEY_ST_PETERSBURG_FOUND_DATE, 1812));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ST_PETERSBURG_FOUND_DATE, 1703);

        editor.commit();
        editor.apply();

        Log.e(TAG, " " + sharedPreferences.getInt(KEY_ST_PETERSBURG_FOUND_DATE, 1812));
    }
}
