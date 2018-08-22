package com.lznby.smartiot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lznby.smartiot.R;
import com.lznby.smartiot.adapter.AllSensorAdapter;
import com.lznby.smartiot.entity.AllSensorInformationEntity;
import com.lznby.smartiot.entity.AllSensorInformationEventBus;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;
/**
 * @author Lznby
 * @time 2018/6/20 13:06
 * Class Note: 传感器的Activity
 */
public class SensorActivity extends AppCompatActivity {

    private String mAllSensorInformation;

    private AllSensorAdapter mAllSensorAdapter;
    private RecyclerView mRecyclerView;
    private List<AllSensorInformationEntity.DataBean> sensorsList = new ArrayList<>();
    private String mBoardID;
    private RequestBody mRequestBody;
    public static final String EXTRA_DATE="sensor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mBoardID = getIntent().getStringExtra("boardId");
        LogUtil.e("Intent传值测试"+ mBoardID);

        mRequestBody = new FormBody.Builder()
                .add("boardId", mBoardID)
                .build();
        RequestHandling.requestSensorInformation(Constants.ALL_SENSOR_INFORMATION_URL, this, mRequestBody, RequestType.POST, CacheUtils.getString(this, AppState.LOGIN_COOKIE), mBoardID);

        //设置adapter
        mAllSensorAdapter = new AllSensorAdapter(R.layout.cv_sensor_item,this);
        mRecyclerView = findViewById(R.id.rv_sensor_override);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAllSensorAdapter);
        mAllSensorAdapter.setNewData(sensorsList);

        mAllSensorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //进入折线图界面
                Intent intent = new Intent(SensorActivity.this,SensorHistoryActivity.class);
                //将传感器基本信息传递给下一个界面
                intent.putExtra(EXTRA_DATE,sensorsList.get(position));
                startActivity(intent);
            }
        });

        /**
         * EventBus 1.
         */
        EventBus.getDefault().register(this);

    }

    /**
     * EventBus 2.
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AllSensorInformationEventBus event) {
        //订阅
        //更新个人信息
        mAllSensorInformation = event.getAllSensorInformationEntity();
        AllSensorInformationEntity entity = Utility.handleAllSensorInformation(mAllSensorInformation, this);
        sensorsList = null;
        if (entity != null) {
            sensorsList = entity.getData();
            mAllSensorAdapter.setNewData(sensorsList);
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
}
