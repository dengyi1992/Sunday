package com.huawei.gxlm.sunday.api;

import com.squareup.okhttp.HttpUrl;

/**
 * Created by xiangzhihong on 2016/3/17 on 18:34.
 */
public class Api {
   //启动动画url地址
    public static final String URL_SPLASH_IMG = "http://news-at.zhihu.com/api/4/start-image/";
  //测试的详情地址
    public static final String DETAIL_URL = "http://news-at.zhihu.com/api/4/news/3892357";
    public static final String HOST = "http://115.159.0.155:2888";
    public static final String LOGINURL = HOST + "/login";
    public static final String REGURL = HOST + "/reg";
    public static final java.lang.String ABOUT = HOST + "/haibao";
    public static final String UPDATE_INFO = HOST+"/verison.json";
    public static final String UPLOADURL = HOST + "/upload";
    public static final String POSTURL = HOST + "/post";
  public static final String LOGOUT_URL = HOST + "/logout";
  public static final String CHANGEPASSURL = HOST + "/changepass";
  public static final String TAGS = "http://115.159.0.155:2888/tags/";
}
