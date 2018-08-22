package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/20 15:15
 * Class Note:子账号EventBus实体类
 */
public class ChildAccountInformationEventBus {
    private String mUserInformationEntity;

    public ChildAccountInformationEventBus(String userInformationEntity) {
        mUserInformationEntity = userInformationEntity;
    }
    public ChildAccountInformationEventBus() {

    }

    public String getUserInformationEntity() {
        return mUserInformationEntity;
    }
}
