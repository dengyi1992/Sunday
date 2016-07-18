package com.huawei.gxlm.sunday.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by deng on 16-7-18.
 */
public class BaseActivity extends AppCompatActivity {
    public void toast(String s){
        Toast.makeText(getApplication(),s,Toast.LENGTH_SHORT).show();;
    }
}
