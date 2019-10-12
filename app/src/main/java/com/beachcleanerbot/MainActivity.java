package com.beachcleanerbot;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("https://google.com");

        Log.i("TAG", "WIFI: " + WifiHelper.isWifiEnabled(this));
        WifiHelper.enableWifiHotspot(this);
    }
}
