package com.luxoft.training.dev018.androidexamples.radioexample;


public class StreamingService /*extends Service */{

//    private static final String RADIO_URL = "http://radio.master.dj:8000/Neva.fm";
//    public static final String ACTION_PLAY = "action_play";
//    public static final String ACTION_PAUSE = "action_pause";
//    public static final String ACTION_STOP = "action_stop";
//    private static final String TAG = StreamingService.class.getName();
//    private static final int NOTIFICATION_ID = 96423; //just an id
//    private static final double VOLUME_LOW_BORDER = 0.5;
//    private static final double ALARM_VOLUME = 0.8;
//
//    private MediaPlayer mediaPlayer;
//    private Context context;
//    private boolean isPaused;
//    private NotificationManager notificationManager;
//    private WifiManager.WifiLock wifiLock;
//    private IStreamingServiceCallbacks serviceCallbacks = null;
//
//
//    private final IServiceAidlInterface.Stub mBinder = new IServiceAidlInterface.Stub() {
//        @Override
//        public void play() throws RemoteException {
//            StreamingService.this.play();
//            if (serviceCallbacks != null) {
//                serviceCallbacks.onPlayDone();
//            }
//        }
//
//        @Override
//        public void pause() throws RemoteException {
//            StreamingService.this.pause();
//            if (serviceCallbacks != null) {
//                serviceCallbacks.onPauseDone();
//            }
//        }
//
//        @Override
//        public void stop() throws RemoteException {
//            StreamingService.this.stop();
//            if (serviceCallbacks != null) {
//                serviceCallbacks.onStopDone();
//            }
//        }
//
//        @Override
//        public boolean isPlaying() throws RemoteException {
//            if (mediaPlayer != null) {
//                return mediaPlayer.isPlaying();
//            }
//            return false;
//        }
//
//        @Override
//        public void registerCallbacks(IStreamingServiceCallbacks callbacks) {
//            serviceCallbacks = callbacks;
//        }
//
//        @Override
//        public void unregisterCallbacks() {
//            serviceCallbacks = null;
//        }
//    };
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return mBinder;
//    }
//
//    @SuppressWarnings("deprecation")
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.d(TAG, "onCreate");
//
//        context = getApplicationContext();
//        wifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE)).createWifiLock(WifiManager.WIFI_MODE_FULL,
//                context.getResources().getString(R.string.app_name));
//
//        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        initMediaPlayer();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(TAG, "onStartCommand");
//        handleIntent(intent);
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    private void handleIntent(Intent intent) {
//        Log.d(TAG, "handleIntent");
//        if(intent == null || intent.getAction() == null) {
//            return;
//        }
//        String action = intent.getAction();
//
//        try {
//            if (action.equalsIgnoreCase(ACTION_PLAY) ) {
//                if (intent.getBooleanExtra(context.getString(R.string.intent_alarm_tag), false)) {
//                    checkAndAdjustVolume();
//                }
//                play();
//                if (serviceCallbacks != null) {
//                    serviceCallbacks.onPlayDone();
//                }
//            } else if (action.equalsIgnoreCase(ACTION_PAUSE) ) {
//                pause();
//                if (serviceCallbacks != null) {
//                    serviceCallbacks.onPauseDone();
//                }
//            } else if (action.equalsIgnoreCase(ACTION_STOP) ) {
//                stop();
//                if (serviceCallbacks != null) {
//                    serviceCallbacks.onStopDone();
//                }
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void checkAndAdjustVolume() {
//        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        double currentVol = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
//        double maxVol = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        if (currentVol < maxVol * VOLUME_LOW_BORDER) {
//            audio.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (maxVol * ALARM_VOLUME), 0);
//        }
//    }
//
//    private NotificationCompat.Action generateAction(int icon, String title, String intentAction) {
//        Log.d(TAG, "generateAction");
//        Intent intent = new Intent(getApplicationContext(), StreamingService.class);
//        intent.setAction(intentAction);
//        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, 0);
//        return new NotificationCompat.Action.Builder(icon, title, pendingIntent).build();
//    }
//
//    private void buildNotification(NotificationCompat.Action action, String contentText) {
//        Log.d(TAG, "buildNotification");
//
//        Intent intentForActivity = new Intent(getApplicationContext(), HelloRadioMainActivity.class);
//        PendingIntent pendingIntentForActivity = PendingIntent.getActivity(context, 0, intentForActivity, 0);
//
//        Intent deleteIntent = new Intent(getApplicationContext(), StreamingService.class);
//        deleteIntent.setAction(ACTION_STOP);
//        PendingIntent pendingDeleteIntent = PendingIntent.getService(getApplicationContext(), 1, deleteIntent, 0);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentTitle(context.getResources().getString(R.string.app_name) )
//                .setDeleteIntent(pendingDeleteIntent)
//                .setContentIntent(pendingIntentForActivity);
//
//        if (contentText != null) {
//            builder.setContentText(contentText);
//        }
//        if (action != null) {
//            builder.addAction(action);
//        }
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(NOTIFICATION_ID, builder.build() );
//    }
//
//    private void initMediaPlayer() {
//        Log.d(TAG, "initMediaPlayer");
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
//        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                Log.i("Buffering", "" + percent);
//            }
//        });
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            mediaPlayer.setDataSource(RADIO_URL);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.d(TAG, "onDestroy");
//        if ( (mediaPlayer != null) && mediaPlayer.isPlaying()) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//        notificationManager.cancel(NOTIFICATION_ID);
//        if (wifiLock.isHeld() ) {
//            wifiLock.release();
//        }
//    }
//
//    private void play() {
//        Log.d(TAG, "play");
//
//        buildNotification(generateAction(R.drawable.pause_96, "Pause", ACTION_PAUSE), "Buffering");
//
//        if (mediaPlayer == null) {
//            initMediaPlayer();
//        }
//        if (!mediaPlayer.isPlaying()) {
//            if (!isPaused) {
//                try {
//                    mediaPlayer.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            mediaPlayer.start();
//            isPaused = false;
//        }
//
//        buildNotification(generateAction(R.drawable.pause_96, "Pause", ACTION_PAUSE), "Playing");
//
//        if (!wifiLock.isHeld() ) {
//            wifiLock.acquire();
//        }
//    }
//
//    private void pause() {
//        Log.d(TAG, "pause");
//
//        if (wifiLock.isHeld()) {
//            wifiLock.release();
//        }
//
//        if ( (mediaPlayer != null) && mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//            isPaused = true;
//        }
//
//        buildNotification(generateAction(R.drawable.play_96, "Play", ACTION_PLAY), "Paused");
//    }
//
//    private void stop() {
//        Log.d(TAG, "stop");
//
//        if ( (mediaPlayer != null) && mediaPlayer.isPlaying() ) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//            isPaused = false;
//        }
//
//        notificationManager.cancel(NOTIFICATION_ID);
//    }
}
