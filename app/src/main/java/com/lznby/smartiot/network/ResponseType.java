package com.lznby.smartiot.network;

/**
 * @author Lznby
 * @time 2018/6/11 15:41
 * Class Note: 请求结果返回值类
 */
public class ResponseType {
    /**
     * 登录成功
     */
    public static final int LOGIN_SUCCESS = 200;


    /**
     * 用户基本信息请求成功
     */
    public static final int USER_INFORMATION_REQUEST_SUCCESS = 200;

    /**
     * 用户基本信息请求失败——未登录
     */
    public static final int USER_UN_LOGIN = 1000;

    /**
     * 用户名或密码不能为空
     */
    public static final int LOGIN_INPUT_NULL = 1001;

    /**
     * 密码不能为空
     */
    public static final int PASSWORD_CAN_NOT_NULL = 1001;

    /**
     * 子用户id不能为空
     */
    public static final int CHILD_ACCOUNT_CAN_NOT_NULL = 1001;

    /**
     * 板id不能为空
     */
    public static final int BOARD_ID_NULL = 1001;

    /**
     * 两次密码不一致
     */
    public static final int TOW_PASSWORD_NOT_SAME = 1002;

    /**
     * 用户名或密码不正确
     */
    public static final int LOGIN_INPUT_ERROR = 1002;

    /**
     * 用户类型错误
     */
    public static final int LOGIN_USER_TYPE_ERROR = 1003;

    /**
     * 用户权限不足(status为0)
     */
    public static final int DONT_HAVE_POWER = 1006;

    /**
     * 删除的用户不存在
     */
    public static final int DELET_ACCOUNT_NOT_HAVE = 1007;

    /**
     * 请求所有板信息——板不存在
     */
    public static final int DOT_HAVE_BOARD = 1008;

    /**
     * 应用不存在
     */
    public static final int APPLICATION_NOT_FIND = 1008;


    /**
     * 传感器不存在
     */
    public static final int SENSOR_NOT_FIND = 1008;
}
