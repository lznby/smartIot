package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/14 10:54
 * Class Note: 用户信息Gson解析类
 */
public class UserInformationEntity {

    /**
     * status : 200
     * msg : OK
     * data : {"userId":1111111115,"fatherId":1111111116,"userUsername":"0002jack","userPassword":null,"userStatus":1,"applicationFlag":"0002","flag":0}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 1111111115
         * fatherId : 1111111116
         * userUsername : 0002jack
         * userPassword : null
         * userStatus : 1
         * applicationFlag : 0002
         * flag : 0
         */

        private int userId;
        private int fatherId;
        private String userUsername;
        private Object userPassword;
        private int userStatus;
        private String applicationFlag;
        private int flag;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getFatherId() {
            return fatherId;
        }

        public void setFatherId(int fatherId) {
            this.fatherId = fatherId;
        }

        public String getUserUsername() {
            return userUsername;
        }

        public void setUserUsername(String userUsername) {
            this.userUsername = userUsername;
        }

        public Object getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(Object userPassword) {
            this.userPassword = userPassword;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public String getApplicationFlag() {
            return applicationFlag;
        }

        public void setApplicationFlag(String applicationFlag) {
            this.applicationFlag = applicationFlag;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
