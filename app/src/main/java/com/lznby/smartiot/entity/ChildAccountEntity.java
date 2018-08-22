package com.lznby.smartiot.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lznby
 * @time 2018/6/18 15:09
 * Class Note: 公司子账户实体类
 */
public class ChildAccountEntity {

    /**
     * data : [{"applicationFlag":"0003","fatherId":1111111129,"userId":1111111130,"userPassword":"111111","userStatus":0,"userUsername":"0003haha"}]
     * msg : OK
     * status : 200
     */

    private String msg;
    private int status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * applicationFlag : 0003
         * fatherId : 1111111129
         * userId : 1111111130
         * userPassword : 111111
         * userStatus : 0
         * userUsername : 0003haha
         */

        private String applicationFlag;
        private int fatherId;
        private int userId;
        private String userPassword;
        private int userStatus;
        private String userUsername;

        public String getApplicationFlag() {
            return applicationFlag;
        }

        public void setApplicationFlag(String applicationFlag) {
            this.applicationFlag = applicationFlag;
        }

        public int getFatherId() {
            return fatherId;
        }

        public void setFatherId(int fatherId) {
            this.fatherId = fatherId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public String getUserUsername() {
            return userUsername;
        }

        public void setUserUsername(String userUsername) {
            this.userUsername = userUsername;
        }
    }
}
