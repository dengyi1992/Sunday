package com.huawei.gxlm.sunday.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.gxlm.sunday.R;
import com.huawei.gxlm.sunday.utils.ToolFor9Ge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityPersonalActivity extends AppCompatActivity {

    private LinearLayout layoutContent;

    private ListView listView;
    private ArrayList<String> strings;
    private LinearLayout mLayoutEmptyinclude;
    private LinearLayout mLayoutUserinclude;
    private SharedPreferences sharedPreferences;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();

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

    @Override
    protected void onResume() {
        initData();
        super.onResume();
    }

    private void initData() {
        strings = new ArrayList<String>();
        strings.add("账户与安全");
        strings.add("我的收藏");
        strings.add("我的动态");
        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < strings.size(); i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("name", strings.get(i));
            listems.add(listem);
        }
        sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        String name = sharedPreferences.getString("name", null);
        int account = sharedPreferences.getInt("account", 0);
        listView.setAdapter(new SimpleAdapter(this, listems, R.layout.settingitem, new String[]{"name"}, new int[]{R.id.item_name}));
        if (isLogin) {
            mLayoutEmptyinclude.setVisibility(View.GONE);
            mLayoutUserinclude.setVisibility(View.VISIBLE);
            initLoginData(name, account);
        } else {
            mLayoutEmptyinclude.setVisibility(View.VISIBLE);
            mLayoutUserinclude.setVisibility(View.GONE);
            initUnLoginData();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        iftoDetail(AccountActivity.class);
                        break;
                    case 1:
                        if (ToolFor9Ge.checkNetworkInfo(ActivityPersonalActivity.this)) {
//                            iftoDetail(MyAssets.class);
                            Toast.makeText(ActivityPersonalActivity.this, "暂未开发", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivityPersonalActivity.this, "没有网络!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (ToolFor9Ge.checkNetworkInfo(ActivityPersonalActivity.this)) {
//                            iftoDetail(MyCollection.class);
                            Toast.makeText(ActivityPersonalActivity.this, "暂未开发", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivityPersonalActivity.this, "没有网络!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }
    private void iftoDetail(Class c) {
        Intent intent = new Intent();
        if (isLogin) {
            intent.setClass(this, c);
        } else {
            intent.setClass(this, LoginActivity.class);
        }
        startActivity(intent);
    }
    /**
     * 已经登录的操作
     *
     * @param name
     * @param account
     */
    private void initLoginData(String name, int account) {
        TextView txtName = (TextView) mLayoutUserinclude.findViewById(R.id.txtName);
        TextView txtJifen = (TextView) mLayoutUserinclude.findViewById(R.id.txtJifen);
        txtName.setText(name);
        txtJifen.setText("积分：" + account);
//        mLayoutUserinclude.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(this, AccountActivity.class));
//            }
//        });

    }

    /**
     * 未登录操作
     */
    private void initUnLoginData() {
        TextView bt_setting_login = (TextView) mLayoutEmptyinclude.findViewById(R.id.bt_setting_login);
        TextView bt_setting_register = (TextView) mLayoutEmptyinclude.findViewById(R.id.bt_setting_register);
        //登录
        bt_setting_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityPersonalActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //注册
        bt_setting_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityPersonalActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initView() {
        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);
        listView = (ListView) findViewById(R.id.listView);
        mLayoutEmptyinclude = (LinearLayout) findViewById(R.id.layoutEmpty);
        mLayoutUserinclude = (LinearLayout) findViewById(R.id.layoutUser);

    }

}
