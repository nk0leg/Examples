package com.luxoft.training.dev018.androidexamples.services.broadcasts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServiceFeedbackBroadcasts extends Service {
    final String TAG = "ServiceFeedbackBr";
    ExecutorService es;

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService onCreate");
        es = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService onStartCommand");

        int time = intent.getIntExtra(ActivityServiceFeedbackBroadcasts.PARAM_TIME, 1);
        int task = intent.getIntExtra(ActivityServiceFeedbackBroadcasts.PARAM_TASK, 0);

        MyRun mr = new MyRun(startId, time, task);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        int task;

        public MyRun(int startId, int time, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.d(TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Intent intent = new Intent(ActivityServiceFeedbackBroadcasts.BROADCAST_ACTION);
            Log.d(TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                // сообщаем об старте задачи
                intent.putExtra(ActivityServiceFeedbackBroadcasts.PARAM_TASK, task);
                intent.putExtra(ActivityServiceFeedbackBroadcasts.PARAM_STATUS, ActivityServiceFeedbackBroadcasts.STATUS_START);
                sendBroadcast(intent);

                // начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                // сообщаем об окончании задачи
                intent.putExtra(ActivityServiceFeedbackBroadcasts.PARAM_STATUS, ActivityServiceFeedbackBroadcasts.STATUS_FINISH);
                intent.putExtra(ActivityServiceFeedbackBroadcasts.PARAM_RESULT, time * 100);
                sendBroadcast(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}
