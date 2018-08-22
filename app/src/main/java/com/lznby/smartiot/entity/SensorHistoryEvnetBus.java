package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/29 14:17
 * Class Note: 传感器历史信息EventBus
 */
public class SensorHistoryEvnetBus {
    private String sensorHistoryInformation;

    public SensorHistoryEvnetBus(String userInformationEntity) {
        sensorHistoryInformation = userInformationEntity;
    }
    public SensorHistoryEvnetBus() {

    }

    public String getSensorHistoryInformation() {
        return sensorHistoryInformation;
    }
}
