package com.luxoft.training.dev018.androidexamples.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivitiesFragment extends Fragment {

    /**
     * NEVER PASS ARGS TO FRAGMENTS CONSTRUCTOR
     * @param args NEVER DO IT
     */
    public LaunchActivitiesFragment(Object... args) {
        // TODO: NEVER DO IT
        // TODO: NEVER PASS ARGS TO FRAGMENTS CONSTRUCTOR

    }

    public LaunchActivitiesFragment() {
    }

    public static final String KEY_ACTIVITIES_CLASS = "Activities";

    public static Bundle createArgs(List<Class<? extends Activity>> activitiesClasses) {
        Bundle args = new Bundle();

        return args;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Intent();
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle arguments = getArguments();
        ArrayList<String> stringArrayList = arguments.getStringArrayList(KEY_ACTIVITIES_CLASS);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private static final class ActivityWrapper {
        private final Class clazz;
        private final String label;

        public ActivityWrapper(String className) throws ClassNotFoundException {
            this.clazz = Class.forName(className);
            this.label = this.clazz.getSimpleName();
        }

        public Class getClazz() {
            return this.clazz;
        }
    }
}
