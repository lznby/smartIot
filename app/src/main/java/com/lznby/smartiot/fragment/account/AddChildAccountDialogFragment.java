package com.lznby.smartiot.fragment.account;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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
 * @time 2018/6/20 21:06
 * Class Note: 添加子账号Dialog
 */
public class AddChildAccountDialogFragment extends DialogFragment {

    private MyEditText mt_child_username;
    private MyEditText mt_child_password;
    private Button bt_add_child_account;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //1、创建要在DialogFragment中显示的视图
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_child_account_dialog,null);
        mt_child_username = (MyEditText) view.findViewById(R.id.mt_child_username);
        mt_child_password = (MyEditText) view.findViewById(R.id.mt_child_password);
        bt_add_child_account = (Button) view.findViewById(R.id.bt_add_child_account);

        //设置username的imageView的监听事件
        mt_child_username.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mt_child_username.getEditText().setText("");
            }
        });

        mt_child_password.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mt_child_password.getEditText().setText("");
            }
        });

        bt_add_child_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加子账户的网络请求
                //请求体
                RequestBody requestBody = new FormBody.Builder()
                        .add("username", mt_child_username.getEditText().getText().toString())
                        .add("password", mt_child_password.getEditText().getText().toString())
                        .add("flag",RequestType.REQUEST_DEVICE_TYPE+"")
                        .build();
                RequestHandling.requestAddChildAccount(Constants.ADD_CHILD_ACCOUNT_URL, getActivity(), requestBody, RequestType.POST, CacheUtils.getString(getContext(), AppState.LOGIN_COOKIE));

                //关闭之前的dialog
                getDialog().cancel();

            }
        });
        return new AlertDialog.Builder(getActivity())
                //为AlertDialog设置视图
                .setView(view)
                .create();
    }
}
