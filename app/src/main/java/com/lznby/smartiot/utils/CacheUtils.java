package com.lznby.smartiot.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Lznby
 * @time 2018/6/11 12:48
 * Class Note: 缓存数据，数据持久化工具类
 */
public class CacheUtils {
    /**
     * 得到缓存值
     *
     * @param context 上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("dlsm", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);

    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("dlsm", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 缓存文本数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("dlsm", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取缓存的文本信息
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("dlsm", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
