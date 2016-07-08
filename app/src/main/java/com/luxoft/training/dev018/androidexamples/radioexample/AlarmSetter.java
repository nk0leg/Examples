package com.luxoft.training.dev018.androidexamples.radioexample;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmSetter {

//    private static final String TAG = AlarmSetter.class.getName();
//    private static final int ALARM_ID = 1;
//    private static final int ALARM_NOTIFICATION_ID = 98774;
//    private Context context;
//    private ISetAlarmStrategy alarmStrategy;
//    private AlarmManager alarmManager;
//
//    private interface ISetAlarmStrategy {
//        void setRTCAlarm(long alarmTimeMills, PendingIntent sender);
//    }
//
//    private ISetAlarmStrategy initSetStrategyForVersion() {
//        Log.d(TAG, "SDK is " + Build.VERSION.SDK_INT);
//        if (Build.VERSION.SDK_INT >= 19) return new KitKatSetter();
//        else return new IceCreamSetter();
//    }
//
//    private final class IceCreamSetter implements ISetAlarmStrategy {
//        @Override
//        public void setRTCAlarm(long alarmTimeMills, PendingIntent sender) {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeMills, sender);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    private final class KitKatSetter implements ISetAlarmStrategy {
//        @Override
//        public void setRTCAlarm(long alarmTimeMills, PendingIntent sender) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeMills, sender);
//        }
//    }
//
//    AlarmSetter(Context ctx) {
//        context = ctx;
//        alarmStrategy = initSetStrategyForVersion();
//        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//    }
//
//    public void setAlarm(int alarmHours, int alarmMinutes) {
//        //to reenable alarm after reboot
//        enableBootCompleteReceiver();
//        showNotification(alarmHours, alarmMinutes);
//
//        long alarmTimeMills;
//        GregorianCalendar calendar = new GregorianCalendar();
//        int currentHours = calendar.get(Calendar.HOUR_OF_DAY);
//        int currentMinutes = calendar.get(Calendar.MINUTE);
//        Log.i(TAG, "cur H: " + currentHours + " cur M: " + currentMinutes);
//
//        calendar.set(Calendar.HOUR_OF_DAY, alarmHours);
//        calendar.set(Calendar.MINUTE, alarmMinutes);
//
//        if (currentHours < alarmHours) {
//            alarmTimeMills = calendar.getTimeInMillis();
//            Log.i(TAG, "Alarm today in future (H)");
//        } else if (currentHours > alarmHours) {
//            alarmTimeMills = calendar.getTimeInMillis() + 24 * 60 * 60 * 1000;
//            Log.i(TAG, "Alarm tomorrow (H)");
//        } else {
//            if (currentMinutes < alarmMinutes) {
//                alarmTimeMills = calendar.getTimeInMillis();
//                Log.i(TAG, "Alarm today in future (M)");
//            } else {
//                alarmTimeMills = calendar.getTimeInMillis() + 24 * 60 * 60 * 1000;
//                Log.i(TAG, "Alarm tomorrow (M)");
//            }
//        }
//        Log.i(TAG, "Alarm H : " + calendar.get(Calendar.HOUR_OF_DAY) + " M: " + calendar.get(Calendar.MINUTE));
//
//        Intent intentAlarm = new Intent(context, AlarmReceiver.class);
//
//        //TODO TMP! >
//        //GregorianCalendar cal = new GregorianCalendar();
//        //alarmTimeMills = calendar.getTimeInMillis() + 10000;
//        //TODO RM this <
//
//        alarmStrategy.setRTCAlarm(alarmTimeMills, PendingIntent.getBroadcast(
//                  context, ALARM_ID, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
//
//        SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.prefs_name), 0);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putLong(context.getString(R.string.prefs_alarm_tag), alarmTimeMills);
//        editor.apply();
//
//        Toast.makeText(context, "Alarm Scheduled", Toast.LENGTH_LONG).show();
//    }
//
//    public void setAlarm(long mills) {
//        Calendar calendar = GregorianCalendar.getInstance();
//        calendar.setTimeInMillis(mills);
//        setAlarm(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
//    }
//
//    public void cancelAlarm() {
//        disableBootCompleteReceiver();
//        hideNotification();
//        PendingIntent sender = PendingIntent.getBroadcast(
//                context, ALARM_ID, new Intent(context, AlarmReceiver.class), 0);
//        alarmManager.cancel(sender);
//
//        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.prefs_name), 0);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.remove(context.getString(R.string.prefs_alarm_tag));
//        editor.apply();
//
//        Toast.makeText(context, "Alarm Cancelled!", Toast.LENGTH_LONG).show();
//    }
//
//    public void enableBootCompleteReceiver() {
//        ComponentName receiver = new ComponentName(context, BootCompleteReceiver.class);
//        PackageManager pm = context.getPackageManager();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);
//    }
//
//    public void disableBootCompleteReceiver() {
//        ComponentName receiver = new ComponentName(context, BootCompleteReceiver.class);
//        PackageManager pm = context.getPackageManager();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                PackageManager.DONT_KILL_APP);
//    }
//
//    public void showNotification(int hours, int minutes) {
//        Intent intentForActivity = new Intent(context.getApplicationContext(), HelloRadioMainActivity.class);
//        PendingIntent pendingIntentForActivity = PendingIntent.getActivity(context, 0, intentForActivity, 0);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.status_bar_alarm)
//                .setContentTitle(context.getResources().getString(R.string.alarm_is_set)
//                        + " " + Utils.getFormattedTime(hours, minutes))
//                .setContentIntent(pendingIntentForActivity);
//
//        Notification noti = builder.build();
//        noti.flags |= Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;
//
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(ALARM_NOTIFICATION_ID, noti);
//    }
//
//    public void hideNotification() {
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancel(ALARM_NOTIFICATION_ID);
//    }
}
