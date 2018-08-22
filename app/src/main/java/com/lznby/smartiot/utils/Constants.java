package com.lznby.smartiot.utils;

/**
 * @author Lznby
 * @time 2018/6/7 17:34
 * Class Note: API类，网络请求地址
 */
public class Constants {
    /**
     * 个人服务器域名
     */
    public static final String IMAGE_BASE_URL = "http://39.108.138.218";

    /**
     * 登录界面Logo的网络地址
     */
    public static final String LOGO_PAGER_URL = IMAGE_BASE_URL + "/IoT.jpg";


    /**
     * 服务器域名
     */
    public static final String BASE_URL = "http://www.chuncongcong.com";

    /**
     * POST
     * 用户登录URL
     * username--String
     * password--String
     * type--int:0——普通用户,1——管理员
     * flag--int:0——app端,1——pc端
     * http://www.chuncongcong.com/collectinfo/login.actio
     */
    public static final String USER_LOGIN_URL = BASE_URL + "/collectinfo/login.action";

    /**
     * GET
     * 用户注销
     * http://www.chuncongcong.com/collectinfo/logout.action
     */
    public static final String USER_LOGE_OUT_URL = BASE_URL + "/collectinfo/logout.action";


    /**
     * POST/GET
     * 获取用户基本信息
     * cookie
     * http://www.chuncongcong.com/collectinfo/get/user.action
     */
    public static final String USER_INFORMATION_URL = BASE_URL + "/collectinfo/get/user.action";

    /**
     *  获取公司子账号信息
     *  http://www.chuncongcong.com/collectinfo/get/user/list.action
     */
    public static final String USER_LIST_URL = BASE_URL + "/collectinfo/get/user/list.action";


    /**
     * GET/POST
     * 获取应用信息
     * http://www.chuncongcong.com/collectinfo/get/application.action
     */
    public static final String APPLICATION_INFORMATION_URL = BASE_URL + "/collectinfo/get/application.action";

    /**
     * GET/POST
     * 获取所有板信息
     * http://www.chuncongcong.com/collectinfo/get/boards.action
     */
    public static final String BOARDS_INFORMATION_URL = BASE_URL + "/collectinfo/get/boards.action";

    /**
     * POST(username,password)
     * 修改自己的密码
     * http://www.chuncongcong.com/collectinfo/update/password.action
     */
    public static final String CHANGE_MYSELF_PASSWORD_URL = BASE_URL + "/collectinfo/update/password.action";

    /**
     * POST
     * 获取一块板的传感器列表
     * http://www.chuncongcong.com/collectinfo/get/transducers.action
     */
    public static final String BOARD_TRANSDUCERS_INFORMATION_URL = BASE_URL+"/collectinfo/get/transducers.action";


    /**
     * POST
     * 添加子账户（管理员）
     * http://www.chuncongcong.com/collectinfo/add/user.action
     */
    public static final String ADD_CHILD_ACCOUNT_URL = BASE_URL + "/collectinfo/add/user.action";


    /**
     * POST
     * 删除子账号信息
     * http://www.chuncongcong.com/collectinfo/delete/user.action
     */
    public static final String DELETE_CHILD_ACCOUNT_URL = BASE_URL + "/collectinfo/delete/user.action";

    /**
     * POST
     * 修改子账号信息
     * http://www.chuncongcong.com/collectinfo/update/user.action
     */
    public static final String UPDATE_CHILD_ACCOUNT_URL = BASE_URL + "/collectinfo/update/user.action";

    /**
     * POST/GET
     * 获取一块板的传感器列表
     * http://www.chuncongcong.com/collectinfo/get/transducers.action
     */
    public static final String ALL_SENSOR_INFORMATION_URL = BASE_URL + "/collectinfo/get/transducers.action";

    /**
     * POST/GET
     * 更新传感器配置信息
     * http://www.chuncongcong.com/collectinfo/update/data/conf.action
     */
    public static final String UPDATE_SENSOR_INFORMATION = BASE_URL + "/collectinfo/update/data/conf.action";


    /**
     * POST/GET
     * 获取传感器配置信息
     * http://www.chuncongcong.com/collectinfo/get/data/conf.action
     */
    public static final String GET_SENSOR_SETTING_URL = BASE_URL + "/collectinfo/get/data/conf.action";

    /**
     * POST
     * 获取传感器历史数据
     * http://www.chuncongcong.com/collectinfo/get/datas.action
     */
    public static final String SENSOR_HISTORY_DATA = BASE_URL + "/collectinfo/get/datas.action";
}
