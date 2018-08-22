package com.lznby.smartiot.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lznby
 * @time 2018/6/28 14:40
 * Class Note: 单块板所有传感器信息显示
 */
public class AllSensorInformationEntity {


    /**
     * status : 200
     * msg : OK
     * data : [{"id":1,"applicationFlag":"0003","boardId":"1","transducerType":"温度传感器","transducerId":"1","transducerStatus":0,"transducerNowdata":27,"transducerUnit":"℃","transducerTime":"2018-06-14 20:10:04","transducerDescription":null},{"id":2,"applicationFlag":"0003","boardId":"1","transducerType":"温度传感器","transducerId":"2","transducerStatus":0,"transducerNowdata":32,"transducerUnit":"℃","transducerTime":"2018-06-14 20:10:04","transducerDescription":null},{"id":3,"applicationFlag":"0003","boardId":"1","transducerType":"湿度传感器","transducerId":"1","transducerStatus":2,"transducerNowdata":70,"transducerUnit":"%","transducerTime":"2018-06-19 14:31:24","transducerDescription":null}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * applicationFlag : 0003
         * boardId : 1
         * transducerType : 温度传感器
         * transducerId : 1
         * transducerStatus : 0
         * transducerNowdata : 27.0
         * transducerUnit : ℃
         * transducerTime : 2018-06-14 20:10:04
         * transducerDescription : null
         */

        private int id;
        private String applicationFlag;
        private String boardId;
        private String transducerType;
        private String transducerId;
        private int transducerStatus;
        private double transducerNowdata;
        private String transducerUnit;
        private String transducerTime;
        private String transducerDescription;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getApplicationFlag() {
            return applicationFlag;
        }

        public void setApplicationFlag(String applicationFlag) {
            this.applicationFlag = applicationFlag;
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

        public int getTransducerStatus() {
            return transducerStatus;
        }

        public void setTransducerStatus(int transducerStatus) {
            this.transducerStatus = transducerStatus;
        }

        public double getTransducerNowdata() {
            return transducerNowdata;
        }

        public void setTransducerNowdata(double transducerNowdata) {
            this.transducerNowdata = transducerNowdata;
        }

        public String getTransducerUnit() {
            return transducerUnit;
        }

        public void setTransducerUnit(String transducerUnit) {
            this.transducerUnit = transducerUnit;
        }

        public String getTransducerTime() {
            return transducerTime;
        }

        public void setTransducerTime(String transducerTime) {
            this.transducerTime = transducerTime;
        }

        public String getTransducerDescription() {
            return transducerDescription;
        }

        public void setTransducerDescription(String transducerDescription) {
            this.transducerDescription = transducerDescription+"";
        }
    }
}
