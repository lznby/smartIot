package com.lznby.smartiot.network;

import android.app.Activity;
import android.widget.Toast;

import com.lznby.smartiot.entity.AllBoardsInformationEventBus;
import com.lznby.smartiot.entity.AllSensorInformationEntity;
import com.lznby.smartiot.entity.AllSensorInformationEventBus;
import com.lznby.smartiot.entity.ChildAccountInformationEventBus;
import com.lznby.smartiot.entity.GetSensorSettingEvnetBus;
import com.lznby.smartiot.entity.SensorHistoryEvnetBus;
import com.lznby.smartiot.entity.UserInformationEventBus;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/6/11 13:21
 * Class Note: 网络请求处理类
 */
public class RequestHandling {

    /**
     * 用户基本信息处理
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestUserInformation(final String address, final Activity activity, final RequestBody requestBody, final String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "个人信息获取失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                CacheUtils.putString(activity,AppState.USER_INFORMATION,responseText);
                LogUtil.e("用户信息--"+responseText);
                //用户基本信息处理
                Utility.handleUserInformationResponse(responseText, activity);

                /**
                 * EventBus 1.初始化 EventBus订阅值
                 */
                EventBus.getDefault().post(new UserInformationEventBus(CacheUtils.getString(activity,AppState.USER_INFORMATION)));

            }
        },requestBody,requestType,cookie);
    }

    /**
     * 请求子账号信息列表
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestUserAccountList(String address, final Activity activity, final RequestBody requestBody,final String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "账号列表信息获取失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                LogUtil.e("子账户信息--"+responseText);
                //处理请求到到账户信息
                Utility.handleChildAccountInformation(responseText, activity);


                /**
                 * EventBus 1.初始化 EventBus订阅值
                 */
                EventBus.getDefault().post(new ChildAccountInformationEventBus(CacheUtils.getString(activity,AppState.CHILD_ACCOUNT_INFORMATION)));
            }
        },requestBody,requestType,cookie);

    }

    /**
     * 请求应用（服务）基本信息
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestApplicationInformation(String address,final Activity activity, final RequestBody requestBody,final String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "应用（服务）信息获取失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                CacheUtils.putString(activity,AppState.APPLICATION_INFORMATION,responseText);
                LogUtil.e("服务信息--"+responseText);
                Utility.handleApplicationInformation(responseText, activity);
            }
        },requestBody,requestType,cookie);
    }


    /**
     * 请求所有板信息
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestBoardsInformation(String address, final Activity activity, RequestBody requestBody, final String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "所有板信息获取失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                LogUtil.e("所有板信息--"+responseText);
                CacheUtils.putString(activity, AppState.ALL_BOARDS_INFORMATION, requestType);
                //处理所有板数据
                Utility.handleAllBoardsInformation(responseText, activity);


                /**
                 * EventBus 1.初始化 EventBus订阅值
                 */
                EventBus.getDefault().post(new AllBoardsInformationEventBus(CacheUtils.getString(activity,AppState.ALL_BOARDS_INFORMATION)));

            }
        },requestBody,requestType,cookie);
    }

    public static void requestChangeMyselfPassword(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "密码修改失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                LogUtil.e("修改密码后信息--"+responseText);
                //修改提示
                Utility.handleChangeMyselfPassword(responseText, activity);
            }
        },requestBody,requestType,cookie);
    }

    /**
     * 添加子账号
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestAddChildAccount(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "创建子账号失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("创建子账号--"+responseText);
                Utility.handleAddChildAccount(responseText, activity);
                //请求子账号信息
                RequestHandling.requestUserAccountList(Constants.USER_LIST_URL, activity, null, RequestType.GET,CacheUtils.getString(activity,AppState.LOGIN_COOKIE));

            }
        },requestBody,requestType,cookie);
    }

    /**
     * 子账号信息修改
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestUpdateChildAccount(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "修改子账号失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("修改子账号结果--"+responseText);
                Utility.handleUpdateChildAccount(responseText, activity);
                //请求子账号信息
                RequestHandling.requestUserAccountList(Constants.USER_LIST_URL, activity, null, RequestType.GET,CacheUtils.getString(activity,AppState.LOGIN_COOKIE));

            }
        },requestBody,requestType,cookie);
    }

    /**
     * 删除子账号
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestDeleteChildAccount(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "删除子账号失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("删除子账号结果--"+responseText);
                Utility.handleDeleteChildAccount(responseText, activity);
                //请求子账号信息
                RequestHandling.requestUserAccountList(Constants.USER_LIST_URL, activity, null, RequestType.GET,CacheUtils.getString(activity,AppState.LOGIN_COOKIE));

            }
        },requestBody,requestType,cookie);
    }

    /**
     * 请求单块板所有传感器信息
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestSensorInformation(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie, final String boardId) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "获取传感器信息失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("传感器信息请求结果--"+responseText);
                Utility.handleAllSensorInformation(responseText, activity);
                CacheUtils.putString(activity, AppState.ALL_SENSOR_INFORMATION+boardId, responseText);

                /**
                 * EventBus 1.初始化 EventBus订阅值
                 */
                EventBus.getDefault().post(new AllSensorInformationEventBus(CacheUtils.getString(activity,AppState.ALL_SENSOR_INFORMATION+boardId)));


            }
        },requestBody,requestType,cookie);
    }

    /**
     * 请求获取传感器配置信息
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestGetSensorSetting(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie, final AllSensorInformationEntity.DataBean dataBean) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "获取层传感器配置信息失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("传感器配置信息请求结果--"+responseText);
                Utility.handleGetSensorSetInformation(responseText, activity);
                CacheUtils.putString(activity, AppState.GET_SENSOR_SETTING+dataBean.getBoardId()+dataBean.getTransducerType()+dataBean.getTransducerId(), responseText);

                /**
                 * EventBus 1.初始化 EventBus订阅值
                 */
                EventBus.getDefault().post(new GetSensorSettingEvnetBus(CacheUtils.getString(activity,AppState.GET_SENSOR_SETTING+dataBean.getBoardId()+dataBean.getTransducerType()+dataBean.getTransducerId())));


            }
        },requestBody,requestType,cookie);
    }

    /**
     * POST
     * 更新传感器配置信息
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestSettingSensorInformation(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie, final String getBoardId) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "配置传感器信息失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("配置传感器结果--"+responseText);
                Utility.handleSensorInformationSeting(responseText, activity);

                RequestBody mRequestBody = new FormBody.Builder()
                        .add("boardId", getBoardId)
                        .build();
                RequestHandling.requestSensorInformation(Constants.ALL_SENSOR_INFORMATION_URL, activity, mRequestBody, RequestType.POST, CacheUtils.getString(activity, AppState.LOGIN_COOKIE), getBoardId);

            }
        },requestBody,requestType,cookie);
    }

    /**
     * 获取传感器历史数据
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     * @param dataBean
     */
    public static void requestSensorHistoryData(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie, final AllSensorInformationEntity.DataBean dataBean, final String pager) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "获取传感器历史数据失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("获取传感器历史数据结果--"+responseText);
                CacheUtils.putString(activity,AppState.GET_SNESOR_HISTORY+dataBean.getBoardId()+dataBean.getTransducerType()+dataBean.getTransducerId() + pager,responseText);
                Utility.handleSensorHistoryData(responseText, activity);
                /**
                 * EventBus 1.初始化 EventBus订阅值
                 */
                EventBus.getDefault().post(new SensorHistoryEvnetBus(CacheUtils.getString(activity,AppState.GET_SNESOR_HISTORY+dataBean.getBoardId()+dataBean.getTransducerType()+dataBean.getTransducerId() + pager)));

            }
        },requestBody,requestType,cookie);
    }

    /**
     * 用户注销请求
     * @param address
     * @param activity
     * @param requestBody
     * @param requestType
     * @param cookie
     */
    public static void requestLogeOut(String address, final Activity activity, RequestBody requestBody, String requestType, String cookie) {
        HttpUtilCookie.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,"注销失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                //处理返回结果
                LogUtil.e("获取传感器历史数据结果--"+responseText);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,"注销成功！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        },requestBody,requestType,cookie);
    }
}
