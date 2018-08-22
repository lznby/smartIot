package com.lznby.smartiot.network;


/**
 * @author Lznby
 * @time 2018/6/1 12:48
 * Class Note:网络请求类型类
 * POST/GET 管理员/普通用户
 */

public class RequestType {
    /**
     * GET请求标记
     */
    public static final String GET = "GET";

    /**
     * POST请求标记
     */
    public static final String POST = "POST";

    /**
     * 普通用户标记
     */
    public static final int USER_TYPE_NORMAL = 0;

    /**
     * 管理员标记
     */
    public static final int USER_TYPE_MANAGER = 1;

    /**
     * 请求客户端标识,0---代表手机
     */
    public static final int REQUEST_DEVICE_TYPE = 0;
}
