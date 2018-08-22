package com.lznby.smartiot.fragment.account;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.lznby.smartiot.R;
import com.lznby.smartiot.entity.ChildAccountEntity;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestHandling;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.utils.LogUtil;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author Lznby
 * @time 2018/6/20 19:48
 * Class Note:
 */
public class ChoseEventDialogFragment extends DialogFragment {

    private static final String ADD_CHILD_ACCOUNT_DIALOG = "AddChildAccountDialog";
    private static final String UPDATE_CHILD_ACCOUNT_DIALOG = "UpdateChildAccountDialog";


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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.chose_event_dialog,null);

        view.findViewById(R.id.add_child_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加子账户

                //创建并显示DialogFragment视图
                FragmentManager manager = getFragmentManager();
                AddChildAccountDialogFragment dialogFragment = new AddChildAccountDialogFragment();
                //这句干嘛用的不知道
                dialogFragment.show(manager, ADD_CHILD_ACCOUNT_DIALOG);
                //关闭之前的dialog
                getDialog().cancel();

            }
        });

        view.findViewById(R.id.delete_child_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //删除子账号

                //请求体
                RequestBody requestBody = new FormBody.Builder()
                        .add("userId", account.getUserId()+"")
                        .build();
                RequestHandling.requestDeleteChildAccount(Constants.DELETE_CHILD_ACCOUNT_URL, getActivity(), requestBody, RequestType.POST, CacheUtils.getString(getContext(), AppState.LOGIN_COOKIE));

                //关闭之前的dialog
                getDialog().cancel();
            }
        });

        view.findViewById(R.id.update_child_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //创建并显示DialogFragment视图
                FragmentManager manager = getFragmentManager();
                UpdateChildAccountDialogFragment dialogFragment = UpdateChildAccountDialogFragment.newInstance(account);
                //这句干嘛用的不知道
                dialogFragment.show(manager, UPDATE_CHILD_ACCOUNT_DIALOG);
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
    public static ChoseEventDialogFragment newInstance(ChildAccountEntity.DataBean account) {
        Bundle args = new Bundle();
        args.putSerializable(ACCOUNT_INFORMATION, account);

        ChoseEventDialogFragment fragment = new ChoseEventDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
