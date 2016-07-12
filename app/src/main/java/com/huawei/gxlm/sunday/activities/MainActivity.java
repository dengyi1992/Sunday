package com.huawei.gxlm.sunday.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import android.widget.ListView;
import android.widget.Spinner;

import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.adapter.TweetsListItemAdapter;
import com.huawei.gxlm.sunday.api.Api;
import com.huawei.gxlm.sunday.utils.HttpUtils;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BoomMenuButton.OnSubButtonClickListener, BoomMenuButton.AnimatorListener {

    private ListView mainList;
    private TweetsListItemAdapter tweetsListItemAdapter;
    private BoomMenuButton boomMenuButton;
    private Spinner spinner;
    private String[] allName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainList = (ListView) findViewById(R.id.main_list);
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initData();
    }

    private void initData() {
        allName = getResources().getStringArray(R.array.all_type);
        tweetsListItemAdapter = new TweetsListItemAdapter(this);
        mainList.setAdapter(tweetsListItemAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
                R.drawable.mark,
                R.drawable.refresh,
                R.drawable.copy,
                R.drawable.heart,
                R.drawable.info,
                R.drawable.like,
                R.drawable.record,
                R.drawable.search,
                R.drawable.settings
        };
        for (int i = 0; i < number; i++)
            drawables[i] = ContextCompat.getDrawable(MainActivity.this, drawablesResource[i]);

        String[] STRINGS = new String[]{
                "Mark",
                "Refresh",
                "Copy",
                "Heart",
                "Info",
                "Like",
                "Record",
                "Search",
                "Settings"
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
     * @param i
     */
    private void changeContent(int i) {
        //网络请求
        String type = allName[i];
        HttpUtils.doGetAsyn(Api.ALL_ARTICAL + "?" + type, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                //处理页面更新
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
        if (id == R.id.action_settings) {
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
            startAct(LoginActivity.class);
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
    private void startAct(Class<?> activity){
        startActivity(new Intent(this,activity));
    }
    @Override
    public void onClick(int buttonIndex) {

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
