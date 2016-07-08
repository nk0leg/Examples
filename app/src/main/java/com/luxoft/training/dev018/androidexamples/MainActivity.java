package com.luxoft.training.dev018.androidexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.luxoft.training.dev018.androidexamples.background.AsyncTasksExample;
import com.luxoft.training.dev018.androidexamples.calculator.Calculator;
import com.luxoft.training.dev018.androidexamples.intents.RootIntentsActivity;
import com.luxoft.training.dev018.androidexamples.layouts.LayoutsExample;
import com.luxoft.training.dev018.androidexamples.lifecycle.LifecycleExample;
import com.luxoft.training.dev018.androidexamples.loaders.LoaderExampleActivity;
import com.luxoft.training.dev018.androidexamples.services.ServicesExampleActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_open_calculator).setOnClickListener(this);
        findViewById(R.id.main_open_intents).setOnClickListener(this);
        findViewById(R.id.main_open_layouts).setOnClickListener(this);
        findViewById(R.id.main_open_lifecycle).setOnClickListener(this);
        findViewById(R.id.main_open_asynctask).setOnClickListener(this);
        findViewById(R.id.main_open_services).setOnClickListener(this);
        findViewById(R.id.main_open_loaders).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.main_open_calculator: {
                intent = new Intent(this, Calculator.class);
                break;
            }
            case R.id.main_open_intents: {
                intent = new Intent(this, RootIntentsActivity.class);
                break;
            }
            case R.id.main_open_layouts: {
                intent = new Intent(this, LayoutsExample.class);
                break;
            }
            case R.id.main_open_lifecycle: {
                intent = new Intent(this, LifecycleExample.class);
                break;
            }
            case R.id.main_open_services: {
                intent = new Intent(this, ServicesExampleActivity.class);
                break;
            }
            case R.id.main_open_asynctask: {
                intent = new Intent(this, AsyncTasksExample.class);
                break;
            }
            case R.id.main_open_loaders: {
                intent = new Intent(this, LoaderExampleActivity.class);
                break;
            }
        }
        startActivity(intent);
    }
}
