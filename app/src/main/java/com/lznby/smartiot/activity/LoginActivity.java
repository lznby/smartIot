package com.lznby.smartiot.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lznby.smartiot.R;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.network.Utility;
import com.lznby.smartiot.utils.Accessibility;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.utils.GlideApp;
import com.lznby.smartiot.view.MyEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Lznby
 * @time 2018/6/7 13:32
 * Class Note: 登录界面Activity
 */
public class LoginActivity extends AppCompatActivity {

    public static int USER_TYPE;

    private CircleImageView iv_logo;
    private MyEditText met_username;
    private MyEditText met_password;
    private RadioGroup rg_power;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv_logo = findViewById(R.id.login_logo_image);
        met_username = findViewById(R.id.username);
        met_password = findViewById(R.id.password);
        rg_power = findViewById(R.id.rg_power);
        bt_login = findViewById(R.id.bt_login);

        //使用Glide加载Logo图片
        GlideApp.with(this)
                .load(Constants.LOGO_PAGER_URL)
                .placeholder(R.drawable.iot)
                .into((ImageView) iv_logo);


        //设置username的imageView的监听事件
        met_username.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                met_username.getEditText().setText("");
            }
        });

        //设置password的imageView的监听事件
        met_password.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (met_password.getEditTextTypeIsPassword()) {
                    met_password.setEditTextTypeIsPassword(false);
                    met_password.setImageButtonBackground(R.drawable.pwd_invisible);
                } else {
                    met_password.setEditTextTypeIsPassword(true);
                    met_password.setImageButtonBackground(R.drawable.pwd_visible);
                }
            }
        });

        //权限申请
        Accessibility.getPermission(this, this);

        //设置登录按钮点击事件
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg_power.getCheckedRadioButtonId() == R.id.rb_manager) {
                    USER_TYPE = RequestType.USER_TYPE_MANAGER;
                } else {
                    USER_TYPE = RequestType.USER_TYPE_NORMAL;
                }

                //请求体
                RequestBody requestBody = new FormBody.Builder()
                        .add("username", met_username.getEditText().getText().toString())
                        .add("password", met_password.getEditText().getText().toString())
                        .add("type", USER_TYPE +"")
                        .add("flag",RequestType.REQUEST_DEVICE_TYPE+"")
                        .build();


                Request request = new Request.Builder()
                        .url(Constants.USER_LOGIN_URL)
                        .post(requestBody)
                        .build();


                //用户登录以及存储cookie
                saveLoginCookie(request,LoginActivity.this);


            }

            /**
             * 用户登录以及存储cookie
             */
            private void saveLoginCookie(Request request, final Activity context) {
                OkHttpClient mHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
                    //Tip：這裡key必須是String
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                        for (int i = 0; i < cookies.size(); i++) {
                            //存放Cookie
                            CacheUtils.putString(LoginActivity.this, AppState.LOGIN_COOKIE,cookies.get(i).toString());
                        }
                    }
                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        if(cookies!=null){
                            for (int i = 0; i < cookies.size(); i++) {
                                //打印
                            }
                        } else {
                            //为空
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();

                mHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(context,"登陆请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                        //处理请求数据,并执行对应操作——转跳到主页面或提示登录失败
                        String responseText = response.body().string();
                        Utility.handleLoginResponse(responseText,context);
                    }
                });
            }
        });

    }

    /**
     * 请求权限结果反馈，及错误提示
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //请求结果反馈
        Accessibility.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
