package com.huawei.gxlm.sunday.activities;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.adapter.GridPhotoAdapter;
import com.huawei.gxlm.sunday.api.Api;
import com.huawei.gxlm.sunday.bean.Tweet;
import com.huawei.gxlm.sunday.widget.enter.AutoHeightGridView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class TweetDetailActivity extends AppCompatActivity {

    private CircleImageView mAvatarCircleImageView;
    private TextView mUsernameTextView;
    private TextView mTimeTextView;
    private TextView mContentTextView;
    private AutoHeightGridView mGridViewAutoHeightGridView;
    private CheckBox mBtnLikeCheckBox;
    private TextView mLikeCountTextView;
    private TextSwitcher mTsLikesCounterTextSwitcher;
    private ImageButton mBtnCommentsImageButton;
    private TextView mCommentCountTextView;
    private ListView mCommentsListListView;
    private LinearLayout mInputLayoutinclude;
    private Tweet.PostsBean detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        mAvatarCircleImageView = (CircleImageView) findViewById(R.id.avatar);
        mUsernameTextView = (TextView) findViewById(R.id.username);
        mTimeTextView = (TextView) findViewById(R.id.time);
        mContentTextView = (TextView) findViewById(R.id.content);
        mGridViewAutoHeightGridView = (AutoHeightGridView) findViewById(R.id.gridView);
        mBtnLikeCheckBox = (CheckBox) findViewById(R.id.btnLike);
        mLikeCountTextView = (TextView) findViewById(R.id.like_count);
        mTsLikesCounterTextSwitcher = (TextSwitcher) findViewById(R.id.tsLikesCounter);
        mBtnCommentsImageButton = (ImageButton) findViewById(R.id.btnComments);
        mCommentCountTextView = (TextView) findViewById(R.id.comment_count);
        mCommentsListListView = (ListView) findViewById(R.id.comments_list);
        mInputLayoutinclude = (LinearLayout) findViewById(R.id.inputLayout);

        detail = getIntent().getParcelableExtra("Detail");

        initViews();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
            default:
                this.finish();
                return super.onOptionsItemSelected(item);
        }
    }




    private void initViews() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(detail.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                actionBar.setElevation(0);
            }
        }
        Glide.with(TweetDetailActivity.this).load(detail.getHead()).into(mAvatarCircleImageView);
        mUsernameTextView.setText(detail.getName());
        mTimeTextView.setText(detail.getTime().getDate());
        mContentTextView.setText(detail.getContent());
        mLikeCountTextView.setText(detail.getZan()+"");
        mCommentCountTextView.setText("0");
        List<String> urls=new ArrayList<>();
        String[] imgs = detail.getImgurls().split("---"+"\\*\\*\\*" +"---");
        for (int i = 1; i < imgs.length; i++) {
            urls.add(Api.HOST+"/"+imgs[i]);
        }
        GridPhotoAdapter adapter = new GridPhotoAdapter(this, urls);
        mGridViewAutoHeightGridView.setAdapter(adapter);
        mGridViewAutoHeightGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TweetDetailActivity.this, ImageDetailActivity.class);
                intent.putExtra("IMAGES",detail.getImgurls());
                intent.putExtra("POSTTION",i);
                startActivity(intent);
            }
        });

    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }




}
