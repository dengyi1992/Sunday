package com.huawei.gxlm.sunday.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.huawei.gxlm.sunday.R;
import com.squareup.picasso.Picasso;

//import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

//import butterknife.Bind;
//import butterknife.ButterKnife;
//import me.nereo.multi_image_selector.MultiImageSelectorActivity;
//import me.zq.youjoin.DataPresenter;
//import me.zq.youjoin.R;
//import me.zq.youjoin.YouJoinApplication;
//import me.zq.youjoin.event.SendTweetEvent;
//import me.zq.youjoin.model.ImageInfo;
//import me.zq.youjoin.model.ResultInfo;
//import me.zq.youjoin.network.NetworkManager;
//import me.zq.youjoin.utils.GlobalUtils;
//import me.zq.youjoin.widget.enter.EmojiFragment;
//import me.zq.youjoin.widget.enter.EnterEmojiLayout;
//import me.zq.youjoin.widget.enter.EnterLayout;

public class PublishActivity extends AppCompatActivity {

    LinearLayout layPhotoContainer;
    ImageButton btnPopPhoto;
    ImageButton btnSend;
    ProgressBar progressBar;
    LinearLayout inputLayout;
    LinearLayout bottomLayout;

    ArrayList<String> mSelectPath;
//    ArrayList<ImageInfo> mData = new ArrayList<>();
    EditText msgEdit;


    private boolean mFirstFocus = true;
    private LinearLayout mLinearLayoutTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        msgEdit = (EditText) findViewById(R.id.comment);
//        mSendImageButton = (ImageButton) findViewById(R.id.send);
//        mSendTextTextView = (TextView) findViewById(R.id.sendText);
        inputLayout = (LinearLayout) findViewById(R.id.inputLayout);
        layPhotoContainer = (LinearLayout) findViewById(R.id.lay_photo_container);
//        mPhotoContainerHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.scroll_photo_container);
        btnPopPhoto = (ImageButton) findViewById(R.id.popPhoto);
        btnSend = (ImageButton) findViewById(R.id.btn_send);
        bottomLayout = (LinearLayout) findViewById(R.id.bottom_layout);
        mLinearLayoutTags = (LinearLayout) findViewById(R.id.ll_tags);


        btnPopPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelectorActivity.startSelect(PublishActivity.this, 2, 9,
                        MultiImageSelectorActivity.MODE_MULTI);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msgEdit.getText().toString().equals("")) {
                    Toast.makeText(PublishActivity.this, "请说点什么吧~", Toast.LENGTH_SHORT).show();
                    return;
                }
                showProgress(true);
//                DataPresenter.sendTweet(YouJoinApplication.getCurrUser().getId(),
//                        msgEdit.getText().toString(), mData, PublishActivity.this);
            }
        });

        initEnter();

//        ThreeBounce threeBounce = new ThreeBounce();
//        threeBounce.setColor(getResources().getColor(R.color.colorPrimary));
//        progressBar.setIndeterminateDrawable(threeBounce);

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



    private void initEnter() {


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
//                StringBuilder sb = new StringBuilder();
                layPhotoContainer.removeAllViews();
                for (String p : mSelectPath) {
//                    sb.append(p);
//                    sb.append("\n");

                    View itemView = View.inflate(PublishActivity.this, R.layout.item_publish_photo, null);
                    ImageView img = (ImageView) itemView.findViewById(R.id.img);
                    itemView.setTag(p);

                    Picasso.with(PublishActivity.this)
                            .load(new File(p))
                            .resize(200, 200)
                            .centerCrop()
                            .into(img);
                    if (layPhotoContainer != null) {
                        layPhotoContainer.addView(itemView,
                                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT));
                    }

//                    mData.add(new ImageInfo(p));
                }
                //yjPublishEdit.setText(sb.toString());
            }
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            inputLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            bottomLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mLinearLayoutTags.setVisibility(show ? View.GONE : View.VISIBLE);
        } else {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            inputLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            bottomLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mLinearLayoutTags.setVisibility(show ? View.GONE : View.VISIBLE);

        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PublishActivity.class);
        context.startActivity(intent);
    }
}
