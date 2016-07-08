package com.luxoft.training.dev018.androidexamples.background;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luxoft.training.dev018.androidexamples.R;
import com.luxoft.training.dev018.androidexamples.background.asynctasks.AsyncByTheWay;
import com.luxoft.training.dev018.androidexamples.background.asynctasks.AsyncSaveHack;
import com.luxoft.training.dev018.androidexamples.background.asynctasks.AsyncSimpleExample;


public class AsyncTasksExample extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        findViewById(R.id.btn_asynctask_example).setOnClickListener(this);
        findViewById(R.id.btn_asynctask_btw).setOnClickListener(this);
        findViewById(R.id.btn_asynctask_screen_rotation_hack).setOnClickListener(this);
        findViewById(R.id.btn_handler_example).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_asynctask_example: {
                startActivity(new Intent(this, AsyncSimpleExample.class));
                break;
            }
            case R.id.btn_asynctask_btw: {
                startActivity(new Intent(this, AsyncByTheWay.class));
                break;
            }
            case R.id.btn_asynctask_screen_rotation_hack: {
                startActivity(new Intent(this, AsyncSaveHack.class));
                break;
            }
            case R.id.btn_handler_example: {

                break;
            }
        }
    }
}
