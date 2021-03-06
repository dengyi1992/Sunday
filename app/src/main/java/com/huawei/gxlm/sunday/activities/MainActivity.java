package com.huawei.gxlm.sunday.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.adapter.TweetsListItemAdapter;
import com.huawei.gxlm.sunday.api.Api;
import com.huawei.gxlm.sunday.bean.Tweet;
import com.huawei.gxlm.sunday.serivice.UpdateService;
import com.huawei.gxlm.sunday.utils.HttpUtils;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.nereo.multi_image_selector.bean.Image;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BoomMenuButton.OnSubButtonClickListener, BoomMenuButton.AnimatorListener {
    private static final int NEW_VERISON_APP = 3;

    private static final int GET_DATA_SUCCESS = 1;
    private static final int GET_DATA_FAIL = 2;
    private ListView mainList;
    private TweetsListItemAdapter tweetsListItemAdapter;
    private BoomMenuButton boomMenuButton;
    private Spinner spinner;
    private String[] allName;
    private int Loaded = 1;
    private List<Tweet.PostsBean> MainData;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_DATA_SUCCESS:
                    pbMain.setVisibility(View.GONE);
                    tweetsListItemAdapter.notifyDataSetChanged();
                    mainList.setSelection(0);
                    break;
                case GET_DATA_FAIL:
                    toast("获取数据失败");
                    break;
                default:
                    break;
            }
        }
    };
    private JSONObject jsonObject;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEW_VERISON_APP:
//                    弹出对话框,是否更新
//                    startService(new Intent(SpalashActivity.this,UpdateService.class));
                    try {
                        String versionName = jsonObject.getString("versionName");
                        String updateinfo = jsonObject.getString("updateinfo");
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setIcon(R.mipmap.logo1).
                                setTitle("有检测到一个新的版本,是否需要更新?").setMessage("版本号:" + versionName + "\n更新内容:" + updateinfo)
                                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startService(new Intent(MainActivity.this, UpdateService.class));
                                    }
                                }).setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).create();
                        alertDialog.show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };
    private SharedPreferences sharedPreferences;
    private boolean isLogin;
    private LinearLayout mllLogined;
    private LinearLayout mllunLogined;
    private ImageView headerImage;
    private TextView nickName;
    private TextView mState;
    private TextView register;
    private TextView login;
    private SharedPreferences cookie;
    private String my_cookie;
    private ProgressBar pbMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainList = (ListView) findViewById(R.id.main_list);
        MainData = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        boomMenuButton = (BoomMenuButton) findViewById(R.id.boom);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        initView();
        initData();
        updateCheck();
    }

    private void initView() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        mllLogined = (LinearLayout) headerView.findViewById(R.id.logined_view);
        headerImage = (ImageView) headerView.findViewById(R.id.imageView);
        nickName = (TextView) headerView.findViewById(R.id.nickname);
        mState = (TextView) headerView.findViewById(R.id.tv_state);
        mllunLogined = (LinearLayout) headerView.findViewById(R.id.unlogin_view);
        register = (TextView) headerView.findViewById(R.id.bt_nav_register);
        login = (TextView) headerView.findViewById(R.id.bt_nav_login);
        pbMain = (ProgressBar) findViewById(R.id.pb_main);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct(RegisterActivity.class);
            }
        });
        headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct(ActivityPersonalActivity.class);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct(LoginActivity.class);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(i+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Tweet.PostsBean postsBean = MainData.get(i);
                Intent intent = new Intent(MainActivity.this, TweetDetailActivity.class);
                intent.putExtra("Detail",postsBean);
                startActivity(intent);
            }
        });
    }

    private void resumeView() {
        sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        String name = sharedPreferences.getString("name", null);
        int account = sharedPreferences.getInt("account", 0);
        if (isLogin) {
            mllLogined.setVisibility(View.VISIBLE);
            mllunLogined.setVisibility(View.GONE);
            nickName.setText(name);
            mState.setText(account + "");

        } else {
            mllunLogined.setVisibility(View.VISIBLE);
            mllLogined.setVisibility(View.GONE);

        }
        changeContent(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cookie = getSharedPreferences("cookie", Context.MODE_PRIVATE);
        my_cookie = cookie.getString("my_cookie", null);
        resumeView();
    }

    private void updateCheck() {
        HttpUtils.doGetAsyn(Api.UPDATE_INFO, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                try {
                    jsonObject = new JSONObject(result);
                    int versionCode = jsonObject.getInt("versionCode");
                    PackageManager pm = getPackageManager();
                    PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
                    /**
                     * 根据版本号更新
                     */
                    if (packageInfo.versionCode < versionCode) {
                        mHandler.sendEmptyMessage(NEW_VERISON_APP);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        allName = getResources().getStringArray(R.array.all_type);
        MainData = new ArrayList<>();
        tweetsListItemAdapter = new TweetsListItemAdapter(this, MainData);
        mainList.setAdapter(tweetsListItemAdapter);
//        getDataFromWeb();
    }

    private void getDataFromWeb() {
        pbMain.setVisibility(View.VISIBLE);
        HttpUtils.doGetAsyn(Api.HOST + "?p=" + Loaded, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                Gson gson = new Gson();
                Tweet tweet = gson.fromJson(result, Tweet.class);
                if (Loaded == 1) {
                    MainData.clear();

                }
                MainData.addAll(tweet.getPosts());
                handler.sendEmptyMessage(GET_DATA_SUCCESS);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(boomMenuButton.isOpen()){
            boomMenuButton.dismiss();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initBoom();
    }

    private void initBoom() {
        int number = 4;

        Drawable[] drawables = new Drawable[number];
        int[] drawablesResource = new int[]{
                R.drawable.add,
                R.drawable.settings,
                R.drawable.refresh,
                R.drawable.share1,
                R.drawable.mark,
                R.drawable.copy,
                R.drawable.heart,
                R.drawable.like,
                R.drawable.record,
                R.drawable.search
        };
        for (int i = 0; i < number; i++)
            drawables[i] = ContextCompat.getDrawable(MainActivity.this, drawablesResource[i]);

        String[] STRINGS = new String[]{
                "发布",
                "设置",
                "刷新",
                "分享",
                "Mark",
                "Copy",
                "Heart",
                "Like",
                "Record",
                "Search"
        };
        String[] strings = new String[number];
        for (int i = 0; i < number; i++)
            strings[i] = STRINGS[i];

        int[][] colors = new int[number][2];
        for (int i = 0; i < number; i++) {
            colors[i][1] = GetRandomColor();
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

        ButtonType buttonType = ButtonType.CIRCLE;
//        switch (buttonTypeGroup.getCheckedRadioButtonId()) {
//            case R.id.circle_button:
//                break;
//            case R.id.hamburger_button:
//                buttonType = ButtonType.HAM;
//                break;
//        }

        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .subButtons(drawables, colors, strings)
                .button(buttonType)
                .boom(BoomType.PARABOLA)
                .place(PlaceType.SHARE_4_1)
                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .onSubButtonClick(this)
                .animator(this)
                .init(boomMenuButton);

//        // Now with Builder, you can init BMB more convenient
//        new BoomMenuButton.Builder()
//                .subButtons(drawables, colors, strings)
//                .button(buttonType)
//                .boom(BoomType.PARABOLA)
//                .place(PlaceType.HAM_4_1)
//                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
//                .onSubButtonClick(this)
//                .animator(this)
//                .dim(DimType.DIM_0)
//                .init(boomMenuButtonInActionBar);
    }

    public int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }

    private String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B"};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        spinner = (Spinner) findViewById(R.id.sp_type);
        initMySpinner();
        return true;
    }

    private void initMySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.all_type,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("test");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeContent(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 改变网络参数
     *
     * @param i
     */
    private void changeContent(int i) {
        //网络请求
        pbMain.setVisibility(View.VISIBLE);
        String type = allName[i];
        String url = Api.HOST;
        switch (type) {
            case "全部":
                url = Api.HOST;
                break;
            case "最火":
                url = Api.HOST;
                break;
            default:
                try {
                    url = Api.TAGS + URLEncoder.encode(type, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
        }
        HttpUtils.doGetAsyn(url, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                if (result.contains("success")) {
                    handler.sendEmptyMessage(GET_DATA_FAIL);
                } else {
                    //处理页面更新
                    Gson gson = new Gson();
                    Tweet tweet = gson.fromJson(result, Tweet.class);
                    //                if (Loaded == 1) {
                    MainData.clear();
                    //                }
                    MainData.addAll(tweet.getPosts());
                    handler.sendEmptyMessage(GET_DATA_SUCCESS);
                }

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share_me) {
            startAct(ShareActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            // 全部
        } else if (id == R.id.nav_zan) {

        } else if (id == R.id.nav_person) {
            startAct(ActivityPersonalActivity.class);
        } else if (id == R.id.nav_setting) {
            startAct(SettingsActivity.class);
        } else if (id == R.id.nav_share) {
            startAct(ShareActivity.class);

        } else if (id == R.id.nav_about) {
            startAct(AboutActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startAct(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }

    @Override
    public void onClick(int buttonIndex) {
        switch (buttonIndex) {
            case 0:
                if (my_cookie == null) {
                    toast("未登录");
                    startAct(LoginActivity.class);
                    return;
                }
                startAct(PublishActivity.class);
                break;
            case 1:
                startAct(SettingsActivity.class);
                break;
            case 2:
//                startAct(SettingsActivity.class);
                break;
            case 3:
                startAct(ShareActivity.class);

                break;
            default:
                break;
        }

    }

    @Override
    public void toShow() {

    }

    @Override
    public void showing(float fraction) {

    }

    @Override
    public void showed() {

    }

    @Override
    public void toHide() {

    }

    @Override
    public void hiding(float fraction) {

    }

    @Override
    public void hided() {

    }
}
