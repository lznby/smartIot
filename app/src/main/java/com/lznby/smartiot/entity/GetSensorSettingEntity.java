package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/29 10:56
 * Class Note: 获取传感器配置信息实体类
 */
public class GetSensorSettingEntity {

    /**
     * status : 200
     * msg : OK
     * data : {"id":2,"boardId":"1","transducerType":"温度传感器","transducerId":"2","applicationFlag":"0003","transducerMax":28,"transducerMin":15,"transducerErrornum":5,"transducerLevel":25,"transducerUnit":"℃","transducerWarntime":2,"updateTime":"2018-06-21 20:58:20","confDescription":null}
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
         * id : 2
         * boardId : 1
         * transducerType : 温度传感器
         * transducerId : 2
         * applicationFlag : 0003
         * transducerMax : 28.0
         * transducerMin : 15.0
         * transducerErrornum : 5.0
         * transducerLevel : 25.0
         * transducerUnit : ℃
         * transducerWarntime : 2
         * updateTime : 2018-06-21 20:58:20
         * confDescription : null
         */

        private int id;
        private String boardId;
        private String transducerType;
        private String transducerId;
        private String applicationFlag;
        private double transducerMax;
        private double transducerMin;
        private double transducerErrornum;
        private double transducerLevel;
        private String transducerUnit;
        private int transducerWarntime;
        private String updateTime;
        private String confDescription;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBoardId() {
            return boardId;
        }

        public void setBoardId(String boardId) {
            this.boardId = boardId;
        }

        public String getTransducerType() {
            return transducerType;
        }

        public void setTransducerType(String transducerType) {
            this.transducerType = transducerType;
        }

        public String getTransducerId() {
            return transducerId;
        }

        public void setTransducerId(String transducerId) {
            this.transducerId = transducerId;
        }

        public String getApplicationFlag() {
            return applicationFlag;
        }

        public void setApplicationFlag(String applicationFlag) {
            this.applicationFlag = applicationFlag;
        }

        public double getTransducerMax() {
            return transducerMax;
        }

        public void setTransducerMax(double transducerMax) {
            this.transducerMax = transducerMax;
        }

        public double getTransducerMin() {
            return transducerMin;
        }

        public void setTransducerMin(double transducerMin) {
            this.transducerMin = transducerMin;
        }

        public double getTransducerErrornum() {
            return transducerErrornum;
        }

        public void setTransducerErrornum(double transducerErrornum) {
            this.transducerErrornum = transducerErrornum;
        }

        public double getTransducerLevel() {
            return transducerLevel;
        }

        public void setTransducerLevel(double transducerLevel) {
            this.transducerLevel = transducerLevel;
        }

        public String getTransducerUnit() {
            return transducerUnit;
        }

        public void setTransducerUnit(String transducerUnit) {
            this.transducerUnit = transducerUnit;
        }

        public int getTransducerWarntime() {
            return transducerWarntime;
        }

        public void setTransducerWarntime(int transducerWarntime) {
            this.transducerWarntime = transducerWarntime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getConfDescription() {
            return confDescription;
        }

        public void setConfDescription(String confDescription) {
            this.confDescription = confDescription;
        }
    }
}
