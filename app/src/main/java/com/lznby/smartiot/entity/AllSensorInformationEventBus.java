package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/28 15:43
 * Class Note: 单块板所有传感器的EventBus类
 */
public class AllSensorInformationEventBus {
    private String mAllSensorInformationEntity;

    public AllSensorInformationEventBus(String allSensorInformationEntity){
        mAllSensorInformationEntity = allSensorInformationEntity;
    }

    public AllSensorInformationEventBus() {

    }

    public String getAllSensorInformationEntity() {
        return mAllSensorInformationEntity;
    }
}
