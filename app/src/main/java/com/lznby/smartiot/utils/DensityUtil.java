package com.lznby.smartiot.utils;

import android.content.Context;

/**
 * @author Lznby
 * @time 2018/5/29 13:25
 * Class Note: 单位转化工具，px和dp互相转换工具
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从dip的单位转化为px（像素）
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px（像素）的单位转化为dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
