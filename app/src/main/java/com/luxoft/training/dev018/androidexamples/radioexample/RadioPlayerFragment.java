package com.luxoft.training.dev018.androidexamples.radioexample;


import android.app.Fragment;

public class RadioPlayerFragment extends Fragment
        /*implements View.OnClickListener, RadialTimePickerDialog.OnTimeSetListener*/ {

    /*private final static String TAG = RadioPlayerFragment.class.getName();

    private ToggleButton buttonPlayPause;
    private Button buttonStop;
    private ImageButton buttonAlarm;
    private SeekBar volumeSeekBar;
    private ImageButton alarmOnImageButton;
    private TextView alarmTextView;
    private ProgressBar bufferingIndicator;
    private Animation forwardPlayAnimation;
    private Animation backwardPlayAnimation;
    private Animation bufferingPlayAnimation;
    private AudioManager audioManager;
    private boolean isPlaying;
    private OnFragmentInteractionListener mListener;
    private VolumeObserver settingsContentObserver;
    private IServiceAidlInterface streamingService;
    private AlarmSetter alarmSetter;

    *//**
     * Class for interacting with the main interface of the service.
     *//*
    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i(TAG, "onServiceConnected");
            //TODO handle it better
            if (buttonPlayPause != null) {
                buttonPlayPause.setEnabled(true);
            }
            if (buttonStop != null) {
                buttonStop.setEnabled(true);
            }

            streamingService = IServiceAidlInterface.Stub.asInterface(service);
            try {
                streamingService.registerCallbacks(serviceCallbacks);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.i(TAG, "onServiceDisconnected");
            //TODO handle it better
            if (buttonPlayPause != null) {
                buttonPlayPause.setEnabled(false);
            }
            if (buttonStop != null) {
                buttonStop.setEnabled(false);
            }

            try {
                streamingService.unregisterCallbacks();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            streamingService = null;
        }
    };


    *//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RadioPlayerFragment.
     *//*
    // TODO: Rename and change types and number of parameters
    public static RadioPlayerFragment newInstance(*//*String param1, String param2*//*) {
        RadioPlayerFragment fragment = new RadioPlayerFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    public RadioPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        settingsContentObserver = new VolumeObserver(getActivity(), new Handler());
        getActivity().getApplicationContext().getContentResolver().registerContentObserver(
                android.provider.Settings.System.CONTENT_URI, true, settingsContentObserver );

        getActivity().getApplicationContext().bindService(
                new Intent(getActivity(), StreamingService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        forwardPlayAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.forward_play_anim);
        backwardPlayAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.backward_play_anim);
        bufferingPlayAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.buffering_play_anim);

        alarmSetter = new AlarmSetter(getActivity());

        *//*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*//*
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        getActivity().getApplicationContext().unbindService(serviceConnection);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_radio_player, container, false);
        initializeUIElements(v);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        RadialTimePickerDialog radialTimePicker = (RadialTimePickerDialog) getActivity()
                .getSupportFragmentManager().findFragmentByTag(RadialTimePickerDialog.class.getName());
        if (radialTimePicker != null) {
            radialTimePicker.setOnTimeSetListener(this);
        }
        updateUi();
    }

    private void updateUi() {
        if (streamingService != null) {
            try {
                if (streamingService.isPlaying()) {
                    setPlayButtonChecked(true);
                } else {
                    setPlayButtonChecked(false);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.prefs_name), 0);
        long alarmMills = prefs.getLong(getString(R.string.prefs_alarm_tag), 0);
        if (alarmMills != 0) {
            alarmOnImageButton.setVisibility(View.VISIBLE);
            alarmTextView.setVisibility(View.VISIBLE);

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTimeInMillis(alarmMills);
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            alarmTextView.setText(Utils.getFormattedTime(hours, minutes));

            alarmSetter.showNotification(hours, minutes);
        } else {
            alarmSetter.hideNotification();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initializeUIElements(View view) {
        buttonPlayPause = (ToggleButton) view.findViewById(R.id.buttonPlayPause);
        buttonPlayPause.setOnClickListener(this);
        buttonStop = (Button) view.findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(this);
        buttonAlarm = (ImageButton) view.findViewById(R.id.buttonAlarm);
        buttonAlarm.setOnClickListener(this);
        alarmOnImageButton = (ImageButton) view.findViewById(R.id.imageButtonAlarmOn);
        alarmOnImageButton.setOnClickListener(this);
        alarmTextView = (TextView) view.findViewById(R.id.textViewAlarm);
        bufferingIndicator = (ProgressBar) view.findViewById(R.id.bufferingIndicator);

        volumeSeekBar = (SeekBar) view.findViewById(R.id.seekBarVolume);
        volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {}

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {}

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
        });
    }

    public void showAlarmTimePickerDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        DateTime now = DateTime.now();
        RadialTimePickerDialog timePickerDialog = RadialTimePickerDialog
                .newInstance(RadioPlayerFragment.this, now.getHourOfDay(), now.getMinuteOfHour(),
                        DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show(fm, RadialTimePickerDialog.class.getName());
    }

    public void play() {
        Log.i(TAG, "Play from activity");
        if (!isPlaying) {
            //bufferingIndicator.setVisibility(View.VISIBLE);
            buttonPlayPause.startAnimation(bufferingPlayAnimation);
            buttonPlayPause.setEnabled(false);
            new StartPlaybackTask().execute();
        }
    }

    public void onClick(View v) {
        try {
            if (v == buttonPlayPause) {
                if (!isPlaying) {
                    //bufferingIndicator.setVisibility(View.VISIBLE);
                    buttonPlayPause.startAnimation(bufferingPlayAnimation);
                    buttonPlayPause.setEnabled(false);
                    new StartPlaybackTask().execute();
                } else {
                    streamingService.pause();
                }
            } else if (v == buttonStop) {
                streamingService.stop();
            } else if (v == buttonAlarm) {
                showAlarmTimePickerDialog();
            } else if (v == alarmOnImageButton) {
                showAlarmCancelDialog();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void showAlarmCancelDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Alarm Cancellation")
                .setMessage("Are you sure you want to cancel the alarm?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alarmSetter.cancelAlarm();
                        alarmOnImageButton.setVisibility(View.GONE);
                        alarmTextView.setVisibility(View.GONE);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onTimeSet(RadialTimePickerDialog radialTimePickerDialog, int h, int m) {
        alarmOnImageButton.setVisibility(View.VISIBLE);
        alarmTextView.setVisibility(View.VISIBLE);

        NumberFormat format = new DecimalFormat("00");
        alarmTextView.setText(format.format(h) + ":" + format.format(m));
        alarmSetter.setAlarm(h, m);
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    class VolumeObserver extends ContentObserver {
        int previousVolume;
        Context context;

        public VolumeObserver(Context c, Handler handler) {
            super(handler);
            context = c;
            previousVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
            int delta = previousVolume - currentVolume;

            if (!selfChange && previousVolume != currentVolume) {
                volumeSeekBar.setProgress(currentVolume);
            }

            if(delta > 0) {
                previousVolume = currentVolume;
            } else if(delta < 0){
                previousVolume = currentVolume;
            }
        }
    }

    private void setPlayButtonChecked(final boolean checked) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buttonPlayPause.setChecked(checked);
                if (checked) {
                    buttonPlayPause.startAnimation(forwardPlayAnimation);
                } else {
                    buttonPlayPause.startAnimation(backwardPlayAnimation);
                }
            }
        });
    }

    private void setPlayButtonEnabled(final boolean enabled) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buttonPlayPause.setEnabled(enabled);
            }
        });
    }

    private void setBufferingIndicatorVisible(final boolean visible) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (visible) {
                    //bufferingIndicator.setVisibility(View.VISIBLE);
                    buttonPlayPause.startAnimation(bufferingPlayAnimation);
                } else {
                    //bufferingIndicator.setVisibility(View.GONE);
                    buttonPlayPause.clearAnimation();
                }
            }
        });
    }

    private IStreamingServiceCallbacks serviceCallbacks = new IStreamingServiceCallbacks.Stub() {
        public void onPlayDone() {
            Log.d(TAG, "onPlayDone");
            isPlaying = true;
            setBufferingIndicatorVisible(false);
            setPlayButtonEnabled(true);
            setPlayButtonChecked(true);
        }
        public void onPauseDone() {
            Log.d(TAG, "onPauseDone");
            isPlaying = false;
            setPlayButtonChecked(false);
        }

        public void onStopDone() {
            Log.d(TAG, "onStopDone");
            isPlaying = false;
            setPlayButtonChecked(false);
        }
    };

    private class StartPlaybackTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                streamingService.play();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/
}
