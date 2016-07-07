package com.luxoft.training.dev018.androidexamples.browser;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

public class BrowserLauncher extends AppCompatActivity {
    void launch() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.luxoft.com/")));
    }
}
