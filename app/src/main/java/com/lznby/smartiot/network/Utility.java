package com.lznby.smartiot.network;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lznby.smartiot.activity.MainActivity;
import com.lznby.smartiot.entity.AccountManagerEntity;
import com.lznby.smartiot.entity.AddChildAccountEntity;
import com.lznby.smartiot.entity.AllBoardsInformationEntity;
import com.lznby.smartiot.entity.AllSensorInformationEntity;
import com.lznby.smartiot.entity.ApplicationInformationEntity;
import com.lznby.smartiot.entity.ChildAccountEntity;
import com.lznby.smartiot.entity.DeleteChildAccountEntity;
import com.lznby.smartiot.entity.GetSensorSettingEntity;
import com.lznby.smartiot.entity.LoginEntity;
import com.lznby.smartiot.entity.SensorHistoryDataEntity;
import com.lznby.smartiot.entity.UpdateChildAccountEntity;
import com.lznby.smartiot.entity.UserInformationEntity;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.LogUtil;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * @author Lznby
 * @time 2018/6/1 12:48
 * Class Note: 处理网络请求下来的数据
 */

public class Utility {
    /**
     * 登录请求结果处理
     *
     * @param response 请求结果
     * @param activity 当前activity
     * @return
     */
    public static boolean handleLoginResponse(final String response, final Activity activity) {

        if (!TextUtils.isEmpty(response)) {

            try {
                //Gson解析json对象
                Gson gson = new Gson();
                final LoginEntity loginEntity = gson.fromJson(response, LoginEntity.class);

                if (loginEntity.getStatus() == ResponseType.LOGIN_SUCCESS) {
                    LogUtil.e("请求读取:coolie--" + CacheUtils.getString(activity, AppState.LOGIN_COOKIE));
                    //设置登录状态
                    CacheUtils.putBoolean(activity, AppState.LOGIN_STATE, true);
                    activity.finish();
                    //登录成功,跳转到主页面
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    //登录时进行绑定
                    /**
                     * 极光推送绑定
                     */
//                    JPushInterface.setAlias(activity, 0, "0003");
                    /**
                     * JPush 设置用户标签
                     */
//                    Set<String> tags = new HashSet<String>();
//                    tags.add("0003");
//                    JPushInterface.setTags(activity, 1, tags);

                } else if (loginEntity.getStatus() == ResponseType.PASSWORD_CAN_NOT_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户名密码不能为空
                            Toast.makeText(activity, loginEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (loginEntity.getStatus() == ResponseType.LOGIN_INPUT_ERROR) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户名或错误
                            Toast.makeText(activity, loginEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, loginEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 用户个人信息结果处理
     *
     * @param response
     * @param activity
     * @return
     */
    public static UserInformationEntity handleUserInformationResponse(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {

            try {
                Gson gson = new Gson();
                final UserInformationEntity userInformation = gson.fromJson(response, UserInformationEntity.class);

                if (userInformation.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //请求成功,处理数据
                    CacheUtils.putString(activity,AppState.USER_INFORMATION,response);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "个人信息请求成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                    /**
                     * 极光推送个人推送——绑定用户名
                     */
                    JPushInterface.setAlias(activity, 1, userInformation.getData().getUserUsername()+"");

                    /**
                     * 极光推送分组推送——绑定应用编号
                     */
                    Set<String> tags = new HashSet<String>();
                    tags.add(userInformation.getData().getApplicationFlag()+"");
                    JPushInterface.setTags(activity, 2, tags);

                } else if (userInformation.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //请求失败——未登录或cookie过时
                            Toast.makeText(activity, userInformation.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //请求失败————遇到未知错误
                            Toast.makeText(activity, userInformation.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return userInformation;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    /**
     * 所有板信息结果处理
     *
     * @param response
     * @param activity
     * @return
     */
    public static AllBoardsInformationEntity handleAllBoardsInformation(String response, final Activity activity) {

        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final AllBoardsInformationEntity allBoardsInformation = gson.fromJson(response, AllBoardsInformationEntity.class);

                if (allBoardsInformation.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //数据请求成功
                    CacheUtils.putString(activity, AppState.ALL_BOARDS_INFORMATION, response);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "板信息获取成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (allBoardsInformation.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, allBoardsInformation.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (allBoardsInformation.getStatus() == ResponseType.DOT_HAVE_BOARD) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //板不存在
                            Toast.makeText(activity, allBoardsInformation.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return allBoardsInformation;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 子账号查询结果处理
     *
     * @param response
     * @param activity
     * @return
     */
    public static ChildAccountEntity handleChildAccountInformation(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final ChildAccountEntity childAccountEntity = gson.fromJson(response, ChildAccountEntity.class);

                if (childAccountEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //数据请求成功
                    CacheUtils.putString(activity, AppState.CHILD_ACCOUNT_INFORMATION, response);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //缓存子账户数据
                            Toast.makeText(activity, "子账号数据请求成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (childAccountEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, childAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (childAccountEntity.getStatus() == ResponseType.DONT_HAVE_POWER) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //权限不足
                            Toast.makeText(activity, childAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, childAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return childAccountEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 个人账号密码修改结果处理
     *
     * @param response
     * @param activity
     */
    public static AccountManagerEntity handleChangeMyselfPassword(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final AccountManagerEntity accountManagerEntity = gson.fromJson(response, AccountManagerEntity.class);

                if (accountManagerEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //密码修改成功
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "密码修改成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (accountManagerEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, accountManagerEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (accountManagerEntity.getStatus() == ResponseType.PASSWORD_CAN_NOT_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //密码能为空
                            Toast.makeText(activity, accountManagerEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (accountManagerEntity.getStatus() == ResponseType.TOW_PASSWORD_NOT_SAME) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //两次密码不相同
                            Toast.makeText(activity, accountManagerEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, accountManagerEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return accountManagerEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 服务信息结果处理
     *
     * @param response
     * @param activity
     */
    public static ApplicationInformationEntity handleApplicationInformation(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final ApplicationInformationEntity applicationInformationEntity = gson.fromJson(response, ApplicationInformationEntity.class);

                if (applicationInformationEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //应用信息请求成功
                            Toast.makeText(activity, "应用信息请求成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (applicationInformationEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, applicationInformationEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (applicationInformationEntity.getStatus() == ResponseType.APPLICATION_NOT_FIND) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //应用不存在
                            Toast.makeText(activity, applicationInformationEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, "未知错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return applicationInformationEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 创建子账号结果处理
     *
     * @param response
     * @param activity
     */
    public static AddChildAccountEntity handleAddChildAccount(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final AddChildAccountEntity addChildAccountEntity = gson.fromJson(response, AddChildAccountEntity.class);

                if (addChildAccountEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //添加子账号成功成功
                            Toast.makeText(activity, "账号添加成功！账号为:" + addChildAccountEntity.getData(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (addChildAccountEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, addChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (addChildAccountEntity.getStatus() == ResponseType.PASSWORD_CAN_NOT_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户名密码不能为空
                            Toast.makeText(activity, addChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, addChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return addChildAccountEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除子账号结果处理
     *
     * @param response
     * @param activity
     */
    public static DeleteChildAccountEntity handleDeleteChildAccount(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final DeleteChildAccountEntity deleteChildAccountEntity = gson.fromJson(response, DeleteChildAccountEntity.class);

                if (deleteChildAccountEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //删除账号成功
                            Toast.makeText(activity, "子账号删除成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (deleteChildAccountEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, deleteChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (deleteChildAccountEntity.getStatus() == ResponseType.CHILD_ACCOUNT_CAN_NOT_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //子账号id不能为空
                            Toast.makeText(activity, deleteChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (deleteChildAccountEntity.getStatus() == ResponseType.DONT_HAVE_POWER) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户权限不足
                            Toast.makeText(activity, deleteChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (deleteChildAccountEntity.getStatus() == ResponseType.DELET_ACCOUNT_NOT_HAVE) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //删除的用户不存在
                            Toast.makeText(activity, deleteChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, deleteChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return deleteChildAccountEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 修改子账号信息结果处理
     *
     * @param response
     * @param activity
     */
    public static UpdateChildAccountEntity handleUpdateChildAccount(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final UpdateChildAccountEntity updateChildAccountEntity = gson.fromJson(response, UpdateChildAccountEntity.class);

                if (updateChildAccountEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //修改账号成功
                            Toast.makeText(activity, "子账号修改成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (updateChildAccountEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, updateChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (updateChildAccountEntity.getStatus() == ResponseType.CHILD_ACCOUNT_CAN_NOT_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //子账号id不能为空
                            Toast.makeText(activity, updateChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (updateChildAccountEntity.getStatus() == ResponseType.DONT_HAVE_POWER) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户权限不足
                            Toast.makeText(activity, updateChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (updateChildAccountEntity.getStatus() == ResponseType.DELET_ACCOUNT_NOT_HAVE) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //修改的用户不存在
                            Toast.makeText(activity, updateChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, updateChildAccountEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return updateChildAccountEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 单块板所有传感器数据处理
     * @param response
     * @param activity
     * @return
     */
    public static AllSensorInformationEntity handleAllSensorInformation(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final AllSensorInformationEntity allSensorInformationEntity = gson.fromJson(response, AllSensorInformationEntity.class);

                if (allSensorInformationEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //数据请求成功
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //缓存传感器数据
                            Toast.makeText(activity, "传感器数据请求成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (allSensorInformationEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, allSensorInformationEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (allSensorInformationEntity.getStatus() == ResponseType.BOARD_ID_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //板id不能为空
                            Toast.makeText(activity, allSensorInformationEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, allSensorInformationEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return allSensorInformationEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 传感器配置信息处理
     * @param response
     * @param activity
     * @return
     */
    public static GetSensorSettingEntity handleGetSensorSetInformation(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final GetSensorSettingEntity getSensorSettingEntity = gson.fromJson(response, GetSensorSettingEntity.class);

                if (getSensorSettingEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //数据请求成功
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //缓存传感器配置信息数据
                            Toast.makeText(activity, "传感器配置信息数据请求成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (getSensorSettingEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, getSensorSettingEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (getSensorSettingEntity.getStatus() == ResponseType.BOARD_ID_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //板id不能为空
                            Toast.makeText(activity, getSensorSettingEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, getSensorSettingEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return getSensorSettingEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 更新传感器配置信息请求结果处理
     * @param response
     * @param activity
     */
    public static void handleSensorInformationSeting(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final GetSensorSettingEntity getSensorSettingEntity = gson.fromJson(response, GetSensorSettingEntity.class);

                if (getSensorSettingEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //数据请求成功
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //缓存传感器配置信息数据
                            Toast.makeText(activity, "修改传感器配置成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (getSensorSettingEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, getSensorSettingEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (getSensorSettingEntity.getStatus() == ResponseType.BOARD_ID_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //板id不能为空
                            Toast.makeText(activity, getSensorSettingEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, getSensorSettingEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取传感器历史信息
     * @param response
     * @param activity
     * @return
     */
    public static SensorHistoryDataEntity handleSensorHistoryData(String response, final Activity activity) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                final SensorHistoryDataEntity sensorHistoryDataEntity = gson.fromJson(response, SensorHistoryDataEntity.class);

                if (sensorHistoryDataEntity.getStatus() == ResponseType.USER_INFORMATION_REQUEST_SUCCESS) {
                    //数据请求成功
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //缓存传感器配置信息数据
                            Toast.makeText(activity, "修改传感器配置成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (sensorHistoryDataEntity.getStatus() == ResponseType.USER_UN_LOGIN) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //用户未登陆
                            Toast.makeText(activity, sensorHistoryDataEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (sensorHistoryDataEntity.getStatus() == ResponseType.BOARD_ID_NULL) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //板id不能为空
                            Toast.makeText(activity, sensorHistoryDataEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //其他错误
                            Toast.makeText(activity, sensorHistoryDataEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return sensorHistoryDataEntity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
