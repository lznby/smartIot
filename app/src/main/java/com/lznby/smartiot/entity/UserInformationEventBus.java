package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/14 14:00
 * Class Note: EventBus 订阅类
 */
public class UserInformationEventBus {
    private String mUserInformationEntity;

    public UserInformationEventBus(String userInformationEntity) {
        mUserInformationEntity = userInformationEntity;
    }
    public UserInformationEventBus() {

    }

    public String getUserInformationEntity() {
        return mUserInformationEntity;
    }
}
