package com.beachcleanerbot;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.LocalOnlyHotspotCallback;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Method;

public class WifiHelper {

    private Context context;
    private WifiManager wifiManager;
    private WifiManager.LocalOnlyHotspotReservation reservation;

    public WifiHelper(Context context) {
        this.context = context;
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    private boolean enableWifiHotspotBelowAPI8() {
        WifiConfiguration wificonfiguration = new WifiConfiguration();
        wificonfiguration.SSID = "walsh";
        wificonfiguration.preSharedKey = "gitbtitw";
        try {
            // if WiFi is on, turn it off
            if(isWifiOn()) {
                wifiManager.setWifiEnabled(false);
            }
            Method method = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method.invoke(wifiManager, wificonfiguration, !isWifiOn());
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void enableWifiHotspotAboveAPI8() {
        wifiManager.startLocalOnlyHotspot(new LocalOnlyHotspotCallback(){
            @Override
            public void onStarted(WifiManager.LocalOnlyHotspotReservation mReservation) {
                super.onStarted(mReservation);
                reservation = mReservation;
            }

            @Override
            public void onStopped() {
                super.onStopped();
            }

            @Override
            public void onFailed(int reason) {
                super.onFailed(reason);
            }
        }, new Handler());
    }

    public void enableWifiHotspot() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // android api version greater than marshmallow
            enableWifiHotspotAboveAPI8();
        } else {
            // android api version lower than marshmallow
            enableWifiHotspotBelowAPI8();
        }
    }

    public boolean isWifiOn() {
        return wifiManager.isWifiEnabled();
    }

}
