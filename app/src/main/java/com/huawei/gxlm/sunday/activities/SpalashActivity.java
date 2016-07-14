package com.huawei.gxlm.sunday.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.api.Api;
import com.huawei.gxlm.sunday.serivice.UpdateService;
import com.huawei.gxlm.sunday.utils.HttpUtils;
import com.huawei.gxlm.sunday.utils.SysUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;


public class SpalashActivity extends AppCompatActivity {

    private Context mContext;
    private Animation animation;
    private ImageView mSdSplashImageView;
    private TextView mAuthorTextView;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        mSdSplashImageView = (ImageView) findViewById(R.id.sd_splash);
        mAuthorTextView = (TextView) findViewById(R.id.tv_author);


        mContext = SpalashActivity.this;
        /**
         * 透明状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        init();

//        updateCheck();
    }



    private void init() {
        initAnimation();
    }

    private void initAnimation() {
        animation= AnimationUtils.loadAnimation(this,R.anim.anim_splash);
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final String dimen;
        int width = SysUtil.getScreenWidth(this);
        if (width >= 900) {
            dimen = "1080*1776";
        } else if (width >= 600 && width < 900) {
            dimen = "720*1184";
        } else if (width >= 400 && width < 600) {
            dimen = "480*728";
        } else {
            dimen = "320*432";
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getImage(Api.URL_SPLASH_IMG + dimen);
            }
        },2000);

    }

    private void getImage(String s) {
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(mContext,s,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);

                bindView(content);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }

    private void bindView(String content) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            String img = jsonObject.optString("img");
            String text = jsonObject.optString("text");
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));

            ImageLoader.getInstance().displayImage(img,mSdSplashImageView);
            mAuthorTextView.setText(text);
            mSdSplashImageView.startAnimation(animation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
