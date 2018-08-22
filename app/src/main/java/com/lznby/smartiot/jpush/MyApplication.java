package com.lznby.smartiot.jpush;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * @author Lznby
 * @time 2018/6/17 16:48
 * Class Note: Application类
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //全局获得context
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}