package com.luxoft.training.dev018.androidexamples.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luxoft.training.dev018.androidexamples.R;
import com.luxoft.training.dev018.androidexamples.services.broadcasts.ActivityServiceFeedbackBroadcasts;
import com.luxoft.training.dev018.androidexamples.services.localbinding.ActivityServiceLBinding;


public class ServicesExampleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_example);
        findViewById(R.id.startIntentService).setOnClickListener(this);
        findViewById(R.id.startService_broadcasts).setOnClickListener(this);
        findViewById(R.id.startService_binding).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startIntentService: {
                for(int i = 1; i < 4; i++ ) {
                    startIntentService(new Intent(this, SimpleIntentService.class), i, i);
                }
                break;
            }
            case R.id.startService_broadcasts: {
                startActivity(new Intent(this, ActivityServiceFeedbackBroadcasts.class));
                break;
            }
            case R.id.startService_binding: {
                startActivity(new Intent(this, ActivityServiceLBinding.class));
                break;
            }
            default: {
                break;
            }
        }
    }

    private void startIntentService(Intent intent, int time, int id) {
        startService(intent.putExtra("time", 3).putExtra("label", "Call " + id) );
    }
}
