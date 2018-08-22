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
import com.lznby.smartiot.entity.ChildAccountEntity;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestHandling;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.utils.LogUtil;
import com.lznby.smartiot.view.MyEditText;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author Lznby
 * @time 2018/6/20 21:44
 * Class Note:
 */
public class UpdateChildAccountDialogFragment extends DialogFragment {
    private MyEditText mt_update_child_username;
    private MyEditText mt_update_child_password;
    private Button bt_update_child_account;


    /**
     * 用于取子账号信息的标识
     */
    private static final String ACCOUNT_INFORMATION ="Account_information";

    /**
     * 保存子账号信息
     */
    private ChildAccountEntity.DataBean account;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        /**
         * arguments取值子账户信息
         */
        Bundle bundle = getArguments();
        if (bundle != null) {
            account = (ChildAccountEntity.DataBean) getArguments().getSerializable(ACCOUNT_INFORMATION);
            LogUtil.e(account.getUserUsername());
        }

        //1、创建要在DialogFragment中显示的视图
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.update_child_account_dialog,null);
        mt_update_child_username = (MyEditText) view.findViewById(R.id.mt_update_child_username);
        mt_update_child_password = (MyEditText) view.findViewById(R.id.mt_update_child_password);
        bt_update_child_account = (Button) view.findViewById(R.id.bt_update_child_account);

        mt_update_child_username.getEditText().setText(account.getUserUsername());
        mt_update_child_password.getEditText().setText(account.getUserPassword());

        //设置username的imageView的监听事件
        mt_update_child_username.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mt_update_child_username.getEditText().setText("");
            }
        });

        mt_update_child_password.setImageButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mt_update_child_password.getEditText().setText("");
            }
        });

        bt_update_child_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改子账户信息操作
                //请求体
                RequestBody requestBody = new FormBody.Builder()
                        .add("userId",account.getUserId()+"")
                        .add("username", mt_update_child_username.getEditText().getText().toString())
                        .add("password", mt_update_child_password.getEditText().getText().toString())
                        .build();
                RequestHandling.requestUpdateChildAccount(Constants.UPDATE_CHILD_ACCOUNT_URL, getActivity(), requestBody, RequestType.POST, CacheUtils.getString(getContext(), AppState.LOGIN_COOKIE));

                //关闭之前的dialog
                getDialog().cancel();

            }
        });

        return new AlertDialog.Builder(getActivity())
                //为AlertDialog设置视图
                .setView(view)
                .create();
    }


    /**
     * 传入需要的参数，设置给arguments，用于传值，用于传入子账户信息
     * @param account
     * @return
     */
    public static UpdateChildAccountDialogFragment newInstance(ChildAccountEntity.DataBean account) {
        Bundle args = new Bundle();
        args.putSerializable(ACCOUNT_INFORMATION, account);

        UpdateChildAccountDialogFragment fragment = new UpdateChildAccountDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
