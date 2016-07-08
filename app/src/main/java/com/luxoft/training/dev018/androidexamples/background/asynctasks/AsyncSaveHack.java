package com.luxoft.training.dev018.androidexamples.background.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.luxoft.training.dev018.androidexamples.R;

import java.util.concurrent.TimeUnit;

public class AsyncSaveHack extends Activity {
    MyTask mt;
    TextView tv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_save_hack);
        Log.d("qwe", "create MainActivity: " + this.hashCode());

        tv = (TextView) findViewById(R.id.tv);

        mt = (MyTask) getLastNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        // передаем в MyTask ссылку на текущее MainActivity
        mt.link(this);

        Log.d("qwe", "create MyTask: " + mt.hashCode());
    }

    public Object onRetainNonConfigurationInstance() {
        // удаляем из MyTask ссылку на старое MainActivity
        mt.unLink();
        return mt;
    }


    static class MyTask extends AsyncTask<String, Integer, Void> {

        AsyncSaveHack activity;

        // получаем ссылку на MainActivity
        void link(AsyncSaveHack act) {
            activity = act;
        }

        // обнуляем ссылку
        void unLink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(2);
                    publishProgress(i);
                    Log.d("qwe", "i = " + i + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + activity.hashCode());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.tv.setText("i = " + values[0]);
        }
    }
}
