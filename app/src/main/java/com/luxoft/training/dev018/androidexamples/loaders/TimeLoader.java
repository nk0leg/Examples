package com.luxoft.training.dev018.androidexamples.loaders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

public class TimeLoader extends Loader<String> {

    final String TAG = "TimeLoader";
    final int PAUSE = 10;

    public final static String ARGS_TIME_FORMAT = "time_format";
    public final static String TIME_FORMAT_SHORT = "h:mm:ss a";
    public final static String TIME_FORMAT_LONG = "yyyy.MM.dd G 'at' HH:mm:ss";

    GetTimeTask getTimeTask;
    String format;

    public TimeLoader(Context context, Bundle args) {
        super(context);
        Log.d(TAG, hashCode() + " create TimeLoader");
        if (args != null)
            format = args.getString(ARGS_TIME_FORMAT);
        if (TextUtils.isEmpty(format))
            format = TIME_FORMAT_SHORT;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(TAG, hashCode() + " onStartLoading");
    }

//    @Override
//    protected void onStartLoading() {
//        super.onStartLoading();
//        Log.d(TAG, hashCode() + " onStartLoading");
//        if (takeContentChanged())
//            forceLoad();
//    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(TAG, hashCode() + " onStopLoading");
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d(TAG, hashCode() + " onForceLoad");
        if (getTimeTask != null)
            getTimeTask.cancel(true);
        getTimeTask = new GetTimeTask();
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.d(TAG, hashCode() + " onAbandon");
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.d(TAG, hashCode() + " onReset");
    }

    void getResultFromTask(String result) {
        deliverResult(result);
    }

    class GetTimeTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, TimeLoader.this.hashCode() + " doInBackground");
            try {
                TimeUnit.SECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                return null;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(params[0],
                    Locale.getDefault());
            return sdf.format(new Date());
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, TimeLoader.this.hashCode() + " onPostExecute "
                    + result);
            getResultFromTask(result);
        }

    }
}