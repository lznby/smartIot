package com.lznby.smartiot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.lznby.smartiot.R;
import com.lznby.smartiot.entity.AllSensorInformationEntity;
import com.lznby.smartiot.entity.GetSensorSettingEntity;
import com.lznby.smartiot.entity.Pressure;
import com.lznby.smartiot.entity.SensorHistoryDataEntity;
import com.lznby.smartiot.entity.SensorHistoryEvnetBus;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestHandling;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.network.Utility;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.utils.DrawChart;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;
/**
 * @author Lznby
 * @time 2018/6/20 13:06
 * Class Note: 传感器历史信息的Activity
 */
public class SensorHistoryActivity extends AppCompatActivity {

    private EditText mEtPager;
    private Button mBtPost;
    private LineChart mLineChart;

    /**
     * 折线图中数据
     */
    private List<Pressure> pressureList = new ArrayList<>();
    private SensorHistoryDataEntity mDataEntity = new SensorHistoryDataEntity();
    private AllSensorInformationEntity.DataBean mDataBean;
    private GetSensorSettingEntity mGetSensorSettingEntity;
    private String mSensorHistoryInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_history);

        /**
         * EventBus 1.
         */
        EventBus.getDefault().register(this);

        mEtPager = (EditText) findViewById(R.id.et_pager_number);
        mBtPost = (Button) findViewById(R.id.bt_post);

        mDataBean = (AllSensorInformationEntity.DataBean) getIntent().getSerializableExtra(SensorActivity.EXTRA_DATE);

        mGetSensorSettingEntity = Utility.handleGetSensorSetInformation(CacheUtils.getString(this, AppState.GET_SENSOR_SETTING + mDataBean.getBoardId() + mDataBean.getTransducerType() + mDataBean.getTransducerId()), this);

        if (mDataBean != null) {
            //请求体
            RequestBody requestBody = new FormBody.Builder()
                    .add("boardId", mDataBean.getBoardId() + "")
                    .add("transducerType", mDataBean.getTransducerType() + "")
                    .add("transducerId", mDataBean.getTransducerId() + "")
                    .add("page", "1")
                    .build();

            //获取传感器历史数据
            RequestHandling.requestSensorHistoryData(Constants.SENSOR_HISTORY_DATA, this, requestBody, RequestType.POST, CacheUtils.getString(this, AppState.LOGIN_COOKIE), mDataBean, "1");

            mDataEntity = Utility.handleSensorHistoryData(CacheUtils.getString(this, AppState.GET_SNESOR_HISTORY + mDataBean.getBoardId() + mDataBean.getTransducerType() + mDataBean.getTransducerId() + "1"), this);

            if (mDataEntity != null && mGetSensorSettingEntity != null) {
                if (mDataEntity.getData()!=null) {
                    pressureList.clear();
                    for (int i = 0; i < mDataEntity.getData().getList().size(); i++) {
                        Pressure pressure = new Pressure();
                        pressure.setPressure((float) mDataEntity.getData().getList().get(i).getTransducerData());
                        pressure.setTime(mDataEntity.getData().getList().get(i).getDataTime());
                        pressure.setPressure_maxlevel((float) mGetSensorSettingEntity.getData().getTransducerMax());
                        pressure.setPressure_minlevel((float) mGetSensorSettingEntity.getData().getTransducerMin());
                        pressure.setPressure_standard((float) mGetSensorSettingEntity.getData().getTransducerLevel());
                        pressureList.add(pressure);
                    }
                }
            }

        }


        mBtPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新拉取数据
                if (mDataBean != null) {
                    //请求体
                    RequestBody requestBody = new FormBody.Builder()
                            .add("boardId", mDataBean.getBoardId() + "")
                            .add("transducerType", mDataBean.getTransducerType() + "")
                            .add("transducerId", mDataBean.getTransducerId() + "")
                            .add("page", mEtPager.getText().toString().trim())
                            .build();

                    //获取传感器历史数据
                    RequestHandling.requestSensorHistoryData(Constants.SENSOR_HISTORY_DATA, SensorHistoryActivity.this, requestBody, RequestType.POST, CacheUtils.getString(SensorHistoryActivity.this, AppState.LOGIN_COOKIE), mDataBean, mEtPager.getText().toString().trim());

                }
            }
        });

        //折线图控件
        mLineChart = (LineChart) findViewById(R.id.lineChart);

    }


    /**
     * EventBus 2.
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SensorHistoryEvnetBus event) {
        //订阅
        //更新个人信息
        mSensorHistoryInformation = event.getSensorHistoryInformation();
//        mDataEntity = Utility.handleSensorHistoryData(CacheUtils.getString(this, AppState.GET_SNESOR_HISTORY + mDataBean.getBoardId() + mDataBean.getTransducerType() + mDataBean.getTransducerId() + "1"), this);
        mDataEntity = Utility.handleSensorHistoryData(mSensorHistoryInformation, this);

        if (mDataEntity != null && mGetSensorSettingEntity != null) {
            if (mDataEntity.getData()!=null) {
                pressureList.removeAll(pressureList);
                for (int i = 0; i < mDataEntity.getData().getList().size(); i++) {
                    Pressure pressure = new Pressure();
                    pressure.setPressure((float) mDataEntity.getData().getList().get(i).getTransducerData());
                    pressure.setTime(mDataEntity.getData().getList().get(i).getDataTime());
                    pressure.setPressure_maxlevel((float) mGetSensorSettingEntity.getData().getTransducerMax());
                    pressure.setPressure_minlevel((float) mGetSensorSettingEntity.getData().getTransducerMin());
                    pressure.setPressure_standard((float) mGetSensorSettingEntity.getData().getTransducerLevel());
                    pressureList.add(pressure);
                }
            }
        }

        //转化为数组形式
        Pressure[] pressures = new Pressure[pressureList.size()];
        pressures = pressureList.toArray(pressures);

        //绘制曲线
        if (pressures.length != 0)
            DrawChart.drawChart(mLineChart, pressures,mDataBean.getTransducerType());

        //刷新曲线信息
        mLineChart.invalidate();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 3. EventBus解除
         */
        EventBus.getDefault().unregister(this);
    }

}
