package com.lznby.smartiot.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lznby.smartiot.R;
import com.lznby.smartiot.entity.AllSensorInformationEntity;
import com.lznby.smartiot.fragment.sensor.SettingSensorDialogFragment;

/**
 * @author Lznby
 * @time 2018/6/28 14:25
 * Class Note: 单块板所有传感器信息的适配器
 */
public class AllSensorAdapter extends BaseQuickAdapter<AllSensorInformationEntity.DataBean, BaseViewHolder> {

    private Activity mActivity;

    private static final String SETTING_SENSOR_DIALOG_FRAGMENT = "SettingSensorDialogFragment";


    public AllSensorAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        mActivity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AllSensorInformationEntity.DataBean item) {
        TextView tvState = helper.getView(R.id.tv_state);
        String state = "";
        if (item.getTransducerStatus() == 0) {
            state = "正常";
            tvState.setTextColor(Color.GREEN);
        } else {
            state = "异常";
            tvState.setTextColor(Color.RED);
        }

        helper.setText(R.id.tv_sensor_type, item.getTransducerType())
                .setText(R.id.tv_value, item.getTransducerNowdata() + item.getTransducerUnit())
                .setText(R.id.tv_update_time, item.getTransducerTime())
                .setText(R.id.tv_note, item.getTransducerDescription());

        Button setting = helper.getView(R.id.bt_set);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager manager = mActivity.getFragmentManager();
                SettingSensorDialogFragment dialogFragment = SettingSensorDialogFragment.newInstance(item);
                dialogFragment.show(manager,SETTING_SENSOR_DIALOG_FRAGMENT);
            }
        });


        tvState.setText(state);

    }


}
