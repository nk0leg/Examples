package com.luxoft.training.dev018.androidexamples.radioexample;


import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.luxoft.training.dev018.androidexamples.R;


public class RadioPlayerFragment extends Fragment
        implements View.OnClickListener {

    private final static String TAG = RadioPlayerFragment.class.getName();

    private ToggleButton buttonPlayPause;
    private SeekBar volumeSeekBar;
    private ProgressBar bufferingIndicator;
    private Animation forwardPlayAnimation;
    private Animation backwardPlayAnimation;
    private Animation bufferingPlayAnimation;
    private AudioManager audioManager;
    private boolean isPlaying;
    private OnFragmentInteractionListener mListener;
    private VolumeObserver settingsContentObserver;
    private IServiceAidlInterface streamingService;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i(TAG, "onServiceConnected");
            //TODO handle it better
            if (buttonPlayPause != null) {
                buttonPlayPause.setEnabled(true);
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

            try {
                streamingService.unregisterCallbacks();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            streamingService = null;
        }
    };


    public static RadioPlayerFragment newInstance() {
        RadioPlayerFragment fragment = new RadioPlayerFragment();
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
                Log.e(TAG, " ",e );
            }
        }
        volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initializeUIElements(View view) {
        buttonPlayPause = (ToggleButton) view.findViewById(R.id.buttonPlayPause);
        buttonPlayPause.setOnClickListener(this);
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

    public void play() {
        Log.i(TAG, "Play from activity");
        if (!isPlaying) {
            buttonPlayPause.startAnimation(bufferingPlayAnimation);
            buttonPlayPause.setEnabled(false);
            try {
                streamingService.play();
            } catch (RemoteException e) {
                Log.e(TAG, " ", e);
            }
        }
    }

    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.buttonPlayPause: {
                    if (!isPlaying) {
                        //bufferingIndicator.setVisibility(View.VISIBLE);
                        buttonPlayPause.startAnimation(bufferingPlayAnimation);
                        buttonPlayPause.setEnabled(false);
                        streamingService.play();
                    } else {
                        streamingService.pause();
                    }
                    break;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public interface OnFragmentInteractionListener {
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
                    buttonPlayPause.startAnimation(bufferingPlayAnimation);
                } else {
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
}
