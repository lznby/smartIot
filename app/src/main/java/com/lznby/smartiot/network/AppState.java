package com.lznby.smartiot.network;

/**
 * @author Lznby
 * @time 2018/6/11 16:44
 * Class Note: 网络请求结果持久化key，便于存取数据
 */
public class AppState {
    /**
     * 保存登录状态,便于识别启动界面
     */
    public static final String LOGIN_STATE = "start_main";

    /**
     * 保存登录cookie信息
     */
    public static final String LOGIN_COOKIE = "login_cookie";


    /**
     * 保存账号基本信息,及权限信息
     */
    public static final String USER_INFORMATION = "user_information";


    /**
     * 保存应用信息
     */
    public static final String APPLICATION_INFORMATION = "application_information";

    /**
     * 保存所有板信息——概要信息
     */
    public static final String ALL_BOARDS_INFORMATION = "all_boards_information";

    /**
     * 保存子账号信息
     */
    public static final String CHILD_ACCOUNT_INFORMATION = "child_account_information";

    /**
     * 保存单块板传感器信息
     */
    public static final String ALL_SENSOR_INFORMATION = "all_child_account_information";

    /**
     * 保存单块板传感器配置信息
     */
    public static final String GET_SENSOR_SETTING = "get_sensor_setting_information";

    /**
     * 存放传感器历史数据信息
     */
    public static final String GET_SNESOR_HISTORY = "get_sensor_history_data";
}
