package com.bootcamp.com.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
        WebView wb;
        Button btn;
        TextView adid;
        String appId;
        String deviceId;
        String adId;
        String url;

        private class HelloWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wb=(WebView)findViewById(R.id.webView);

        appId="ad31";
        deviceId="31";
        adId="3";
        //for device mac address
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        deviceId = info.getMacAddress();
        TextView mac=(TextView)findViewById(R.id.mac);
        mac.setText(deviceId);

        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
        //wb.getSettings().setPluginsEnabled(true);
        wb.setWebViewClient(new HelloWebViewClient());
        wb.loadUrl("http://192.168.1.2/NepaliAdsense/public/View/" + appId + "/" + deviceId);

        wb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //wb.loadUrl("http://192.168.1.5/NepaliAdsense/public/Click?appId=1&deviceId=2&adId=3");
//                String url = "http://192.168.1.5/NepaliAdsense/public/Click/" + appId + "/" + deviceId + "/" + adId;
                String url = "http://192.168.1.2/NepaliAdsense/public/Click/" + appId + "/" + deviceId;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }
        });
    }

}
