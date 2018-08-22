package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/20 9:49
 * Class Note: 应用信息实体类
 */
public class ApplicationInformationEntity {

    /**
     * status : 200
     * msg : OK
     * data : {"applicationId":1,"applicationName":"大棚养殖","applicationCompany":"阿里巴巴","applicationFlag":"0003","applicationTime":"2018-06-15 15:44:20","applicationDescription":null}
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
         * applicationId : 1
         * applicationName : 大棚养殖
         * applicationCompany : 阿里巴巴
         * applicationFlag : 0003
         * applicationTime : 2018-06-15 15:44:20
         * applicationDescription : null
         */

        private int applicationId;
        private String applicationName;
        private String applicationCompany;
        private String applicationFlag;
        private String applicationTime;
        private Object applicationDescription;

        public int getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(int applicationId) {
            this.applicationId = applicationId;
        }

        public String getApplicationName() {
            return applicationName;
        }

        public void setApplicationName(String applicationName) {
            this.applicationName = applicationName;
        }

        public String getApplicationCompany() {
            return applicationCompany;
        }

        public void setApplicationCompany(String applicationCompany) {
            this.applicationCompany = applicationCompany;
        }

        public String getApplicationFlag() {
            return applicationFlag;
        }

        public void setApplicationFlag(String applicationFlag) {
            this.applicationFlag = applicationFlag;
        }

        public String getApplicationTime() {
            return applicationTime;
        }

        public void setApplicationTime(String applicationTime) {
            this.applicationTime = applicationTime;
        }

        public Object getApplicationDescription() {
            return applicationDescription;
        }

        public void setApplicationDescription(Object applicationDescription) {
            this.applicationDescription = applicationDescription;
        }
    }
}
