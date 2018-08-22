package com.lznby.smartiot.fragment.sensor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lznby.smartiot.R;
import com.lznby.smartiot.entity.AllSensorInformationEntity;
import com.lznby.smartiot.entity.GetSensorSettingEntity;
import com.lznby.smartiot.entity.GetSensorSettingEvnetBus;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestHandling;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.network.Utility;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author Lznby
 * @time 2018/6/29 9:09
 * Class Note: 更新传感器配置信息DialogFragment
 */
public class SettingSensorDialogFragment extends DialogFragment {

    private EditText mMaxValue;
    private EditText mMinValue;
    private EditText mErrorValue;
    private EditText mAverageValue;
    private EditText mTimeValue;
    private EditText mNoteValue;
    private Button mUpdateConfirm;

    private static final String SENSOR_INFORMATION ="sensor_information";

    private GetSensorSettingEntity.DataBean mDataBeans = new GetSensorSettingEntity.DataBean();

    private String sensorSettingInformation;

    /**
     * 保存传感器信息信息
     */
    private AllSensorInformationEntity.DataBean account;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /**
         * EventBus 1.
         */
        EventBus.getDefault().register(this);

        /**
         * arguments取值传感器信息
         */
        Bundle bundle = getArguments();
        if (bundle != null) {
            account = (AllSensorInformationEntity.DataBean) getArguments().getSerializable(SENSOR_INFORMATION);
            LogUtil.e(account.getBoardId());

            //请求体
            RequestBody requestBody = new FormBody.Builder()
                    .add("boardId",account.getBoardId())
                    .add("transducerType", account.getTransducerType())
                    .add("transducerId",account.getTransducerId())
                    .build();
            //请求传感器配置信息
            RequestHandling.requestGetSensorSetting(Constants.GET_SENSOR_SETTING_URL, getActivity(), requestBody, RequestType.POST, CacheUtils.getString(getActivity(), AppState.LOGIN_COOKIE),account);
        }

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.update_sensor_dialog,null);
        mMaxValue = (EditText) view.findViewById(R.id.et_max_value);
        mMinValue = (EditText) view.findViewById(R.id.et_min_value);
        mErrorValue = (EditText) view.findViewById(R.id.et_error_value);
        mAverageValue = (EditText) view.findViewById(R.id.et_average_value);
        mTimeValue = (EditText) view.findViewById(R.id.et_time_value);
        mNoteValue = (EditText) view.findViewById(R.id.et_note_value);
        mUpdateConfirm = (Button) view.findViewById(R.id.bt_update_sensor);
        
        mUpdateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //请求体
                RequestBody requestBody = new FormBody.Builder()
                        .add("boardId",account.getBoardId())
                        .add("transducerNowdata",account.getTransducerNowdata()+"")
                        .add("transducerType", account.getTransducerType())
                        .add("transducerId",account.getTransducerId())
                        .add("transducerMax",mMaxValue.getText().toString().trim())
                        .add("transducerMin",mMinValue.getText().toString().trim())
                        .add("transducerErrornum",mErrorValue.getText().toString().trim())
                        .add("transducerLevel",mAverageValue.getText().toString().trim())
                        .add("transducerWarntime",mTimeValue.getText().toString().trim())
                        .add("confDescription",mNoteValue.getText().toString().trim())
                        .build();

                //修改传感器设置请求
                RequestHandling.requestSettingSensorInformation(Constants.UPDATE_SENSOR_INFORMATION, getActivity(), requestBody, RequestType.POST, CacheUtils.getString(getActivity(), AppState.LOGIN_COOKIE),account.getBoardId());
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
    public static SettingSensorDialogFragment newInstance(AllSensorInformationEntity.DataBean account) {
        Bundle args = new Bundle();
        args.putSerializable(SENSOR_INFORMATION, account);

        SettingSensorDialogFragment fragment = new SettingSensorDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * EventBus 2.
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GetSensorSettingEvnetBus event) {
        //订阅
        //更新设置信息
        sensorSettingInformation = event.getGetSensorSettingInformation();
        GetSensorSettingEntity entity = Utility.handleGetSensorSetInformation(sensorSettingInformation, getActivity());
        mDataBeans = null;
        if (entity != null) {
            mDataBeans = entity.getData();
            mMaxValue.setText(mDataBeans.getTransducerMax()+"");
            mMinValue.setText(mDataBeans.getTransducerMin()+"");
            mErrorValue.setText(mDataBeans.getTransducerErrornum()+"");
            mAverageValue.setText(mDataBeans.getTransducerLevel()+"");
            mTimeValue.setText(mDataBeans.getTransducerWarntime()+"");
            mNoteValue.setText(mDataBeans.getConfDescription());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 3. EventBus解除
         */
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
