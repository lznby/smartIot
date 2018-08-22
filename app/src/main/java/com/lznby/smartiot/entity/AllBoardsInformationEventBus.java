package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/20 11:16
 * Class Note:所有板信息的EventBus类
 */
public class AllBoardsInformationEventBus {
    private String mUserInformationEntity;

    public AllBoardsInformationEventBus(String userInformationEntity) {
        mUserInformationEntity = userInformationEntity;
    }
    public AllBoardsInformationEventBus() {

    }

    public String getUserInformationEntity() {
        return mUserInformationEntity;
    }
}
