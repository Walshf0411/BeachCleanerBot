package com.beachcleanerbot;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    WifiHelper wifiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://google.com");

        switchOnHotspot();
    }

    private void switchOnHotspot() {
        wifiHelper = new WifiHelper(this);
        wifiHelper.enableWifiHotspot();
    }
}
