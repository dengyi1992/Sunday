package com.huawei.gxlm.sunday.activities;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.api.Api;

public class AboutActivity extends AppCompatActivity {

    private WebView mAboutWebView;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setMax(100);
        mAboutWebView = (WebView) findViewById(R.id.wv_about);
        mAboutWebView.getSettings().setJavaScriptEnabled(true);
        mAboutWebView.getSettings().setSupportZoom(true);
        mAboutWebView.getSettings().setBuiltInZoomControls(true);
        mAboutWebView.setWebChromeClient(new WebViewClient());
        mAboutWebView.loadUrl(Api.ABOUT);

//        /**
//         * 透明状态栏
//         */
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // Translucent navigation bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                actionBar.setElevation(0);
            }

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
//                this.finish();
            default:
                this.finish();
                return super.onOptionsItemSelected(item);
        }
    }
    private class WebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            pb.setProgress(newProgress);
            if (newProgress == 100) {
                pb.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}