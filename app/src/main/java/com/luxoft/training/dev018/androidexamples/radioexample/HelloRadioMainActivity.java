package com.luxoft.training.dev018.androidexamples.radioexample;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.luxoft.training.dev018.androidexamples.R;


public class HelloRadioMainActivity extends ActionBarActivity implements RadioPlayerFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_radio_main);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, RadioPlayerFragment.newInstance(), RadioPlayerFragment.class.getName())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
