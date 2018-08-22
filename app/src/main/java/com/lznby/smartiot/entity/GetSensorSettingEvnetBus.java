package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/29 10:57
 * Class Note: 获取传感器配置信息EventBus类
 */
public class GetSensorSettingEvnetBus {
    private String getSensorSettingInformation;

    public GetSensorSettingEvnetBus(String userInformationEntity) {
        getSensorSettingInformation = userInformationEntity;
    }
    public GetSensorSettingEvnetBus() {

    }

    public String getGetSensorSettingInformation() {
        return getSensorSettingInformation;
    }
}
