package com.huawei.gxlm.sunday.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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
import com.huawei.gxlm.sunday.api.Api;
import com.huawei.gxlm.sunday.bean.ImageInfo;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

//import org.greenrobot.eventbus.EventBus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    private static final int IMAGE_PICKER = 1;
    private static final int IMGPOSTED = 2;
    private static final int NETWORK_EORR = 3;
    private static final int POSTSUCCESS = 4;
    ArrayList<String> mSelectPath;
    ArrayList<ImageInfo> mData = new ArrayList<>();
    EditText msgEdit;


    private boolean mFirstFocus = true;
    private LinearLayout mLinearLayoutTags;
    private String imgResponse;
    private String imgurl;
    private String success;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMGPOSTED:
                    uploadInfo();
                    break;
                case NETWORK_EORR:
                    Toast.makeText(PublishActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case POSTSUCCESS:
                    if (success.contains("success")) {
                        Toast.makeText(PublishActivity.this, "发布成功", Toast.LENGTH_LONG).show();
                        finish();
//                        resetData();

                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(imgResponse);
                            String error = jsonObject.getString("error");
                            Toast.makeText(PublishActivity.this, error, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                default:
                    break;
            }
        }
    };
    private String images;
    private SharedPreferences cookie;
    private String my_cookie;
    private CheckBox cbArtical;
    private CheckBox cbActivity;
    private CheckBox cbTrade;
    private CheckBox cbLearning;
    private CheckBox cbGroup;
    private CheckBox cbPic;
    private CheckBox cbSport;
    private String[] tags;

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
        cbArtical = (CheckBox) mLinearLayoutTags.findViewById(R.id.cb_artical);
        cbActivity = (CheckBox) mLinearLayoutTags.findViewById(R.id.cb_activity);
        cbTrade = (CheckBox) mLinearLayoutTags.findViewById(R.id.cb_trade);
        cbLearning = (CheckBox) mLinearLayoutTags.findViewById(R.id.cb_learning);
        cbGroup = (CheckBox) mLinearLayoutTags.findViewById(R.id.cb_group);
        cbPic = (CheckBox) mLinearLayoutTags.findViewById(R.id.cb_pic);
        cbSport = (CheckBox) mLinearLayoutTags.findViewById(R.id.cb_sport);


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
                sendTweet();
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
            actionBar.setTitle("发动态");
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                actionBar.setElevation(0);
            }

        }
    }

    private void sendTweet() {
        showProgress(false);
        int count = 0;
        tags = new String[7];
        if (cbActivity.isChecked())
            tags[count++] = "活动";
        if (cbArtical.isChecked())
            tags[count++] = "文章";
        if (cbGroup.isChecked())
            tags[count++] = "组团";
        if (cbLearning.isChecked())
            tags[count++] = "学习";
        if (cbPic.isChecked())
            tags[count++] = "摄影";
        if (cbTrade.isChecked())
            tags[count++] = "校园交易";
        if (cbSport.isChecked())
            tags[count++] = "运动";
        if (count == 0) {
            Toast.makeText(this, "请至少选择一个标签", Toast.LENGTH_SHORT).show();
            return;
        }
        if (count > 3) {
            Toast.makeText(this, "请最多不超过3个标签", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mData.size()==0){
            uploadInfo();
            return;
        }
//        for (int i = 0; i < mData.size(); i++) {
            uploadImage(mData, 0, this);
//        }
        //获取上传图片的信息
//        构建json


//        if (info.getResult() != null && info.getResult().equals(NetworkManager.SUCCESS)) {
//            msgEdit.setText("");
//            mData.clear();
//            layPhotoContainer.removeAllViews();
//            GlobalUtils.popSoftkeyboard(PublishActivity.this, msgEdit, false);
//            Toast.makeText(PublishActivity.this, getString(R.string.send_tweet_success)
//                    , Toast.LENGTH_SHORT).show();
//            EventBus.getDefault().post(new SendTweetEvent());
//            PublishActivity.this.finish();
//        } else {
//            Toast.makeText(PublishActivity.this, getString(R.string.error_network)
//                    , Toast.LENGTH_SHORT).show();
//        }

    }

    private void uploadInfo() {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", "这是标题");
            jsonObject.put("post", msgEdit.getText().toString());
            jsonObject.put("imgurls", images);
            jsonObject.put("tag1", tags[0]);
            jsonObject.put("tag2", tags[1]);
            jsonObject.put("tag3", tags[2]);
//            jsonObject.put("icons", 2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(JSON, json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(Api.POSTURL)
                .addHeader("Cookie", my_cookie)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                handler.sendEmptyMessage(NETWORK_EORR);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                success = response.body().string();
                handler.sendEmptyMessage(POSTSUCCESS);
            }
        });
//

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
        images = "";


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
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

                    mData.add(new ImageInfo(p));
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


    private boolean isFinished;

    /**
     * 图片上传
     *
     * @param imageInfos
     */
    private synchronized void uploadImage(final ArrayList<ImageInfo> imageInfos, final int index, final Context context) {
        if (index > imageInfos.size() - 1) {
            //完成
            handler.sendEmptyMessage(IMGPOSTED);
            return;
        }
        isFinished = false;

        //多个图片文件列表
        List<File> list = new ArrayList<File>();
        String imagePath = imageInfos.get(index).getImagePath();
        File file = new File(imagePath);
        list.add(file);
        //多文件表单上传构造器
        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        //添加一个文本表单参数
        multipartBuilder.addFormDataPart("name", "uploadimg");
        for (File file1 : list) {
            if (file1.exists()) {
                multipartBuilder.addFormDataPart("filename", file1.getName(), RequestBody.create(MediaType.parse("image/png"), file1));
            }
        }
        //构造文件上传时的请求对象Request
        Request request = new Request.Builder().addHeader("Cookie", my_cookie).url(Api.UPLOADURL).post(multipartBuilder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                handler.sendEmptyMessage(NETWORK_EORR);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                imgResponse = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(imgResponse);
                    String imgurl = jsonObject.getString("imgurl");
                    images=images+"---***---"+imgurl;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                handler.sendEmptyMessage(IMGPOSTED);
                uploadImage(imageInfos, index + 1, context);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        cookie = getSharedPreferences("cookie", Context.MODE_PRIVATE);
        my_cookie = cookie.getString("my_cookie", null);
    }
}
