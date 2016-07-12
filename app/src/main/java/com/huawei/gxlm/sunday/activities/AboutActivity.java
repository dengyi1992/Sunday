package com.huawei.gxlm.sunday.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.api.Api;

public class AboutActivity extends AppCompatActivity {

    private WebView mAboutWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mAboutWebView = (WebView) findViewById(R.id.wv_about);
        mAboutWebView.loadUrl(Api.ABOUT);

    }
}
