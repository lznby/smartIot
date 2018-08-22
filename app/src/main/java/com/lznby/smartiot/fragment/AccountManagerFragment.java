package com.lznby.smartiot.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lznby.smartiot.R;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestHandling;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.view.MyEditText;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author Lznby
 * @time 2018/6/12 8:56
 * Class Note: 账号管理界面碎片
 */
public class AccountManagerFragment  extends Fragment {

    private MyEditText mt_old_password;
    private MyEditText mt_new_password;
    private MyEditText mt_renew_password;
    private Button bt_change_password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_manager,container,false);
        mt_old_password = (MyEditText) view.findViewById(R.id.mt_old_password);
        mt_new_password = (MyEditText) view.findViewById(R.id.mt_new_password);
        mt_renew_password = (MyEditText) view.findViewById(R.id.mt_renew_password);
        bt_change_password = (Button) view.findViewById(R.id.bt_change_password);

        //设置password的imageView的监听事件
        mt_old_password.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisiable(mt_old_password);
            }
        });

        //设置password的imageView的监听事件
        mt_new_password.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisiable(mt_new_password);
            }
        });

        //设置password的imageView的监听事件
        mt_renew_password.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisiable(mt_renew_password);
            }
        });

        bt_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改密码
                Toast.makeText(getActivity(),"修改密码", Toast.LENGTH_SHORT).show();

                //请求体
                RequestBody requestBody = new FormBody.Builder()
                        .add("password", mt_old_password.getEditText().getText().toString())
                        .add("newPassword", mt_new_password.getEditText().getText().toString())
                        .add("reNewPassword", mt_renew_password.getEditText().getText().toString())
                        .build();

                /**
                 * 个人账户请求处理
                 */
                RequestHandling.requestChangeMyselfPassword(Constants.CHANGE_MYSELF_PASSWORD_URL, getActivity(), requestBody, RequestType.POST, CacheUtils.getString(getContext(), AppState.LOGIN_COOKIE));
            }
        });



        return view;
    }



    /**
     * 设置密码是否可见
     * @param myEditText
     */
    private void isVisiable(MyEditText myEditText) {
        if (myEditText.getEditTextTypeIsPassword()) {
            myEditText.setEditTextTypeIsPassword(false);
            myEditText.setImageButtonBackground(R.drawable.pwd_invisible);
        } else {
            myEditText.setEditTextTypeIsPassword(true);
            myEditText.setImageButtonBackground(R.drawable.pwd_visible);
        }
    }
}
