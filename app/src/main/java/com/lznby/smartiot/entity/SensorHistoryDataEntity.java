package com.lznby.smartiot.entity;

import java.util.List;

/**
 * @author Lznby
 * @time 2018/6/29 12:47
 * Class Note: 传感器历史数据实体类
 */
public class SensorHistoryDataEntity {
    /**
     * status : 200
     * msg : OK
     * data : {"list":[{"dataId":7,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":1080,"dataTime":"2018-06-14 13:49:07"},{"dataId":9,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":1080,"dataTime":"2018-06-14 16:38:08"},{"dataId":11,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-15 13:50:22"},{"dataId":12,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:30:07"},{"dataId":13,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:31:37"},{"dataId":14,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:35:36"},{"dataId":15,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:42:46"},{"dataId":16,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:46:23"},{"dataId":17,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:46:54"},{"dataId":18,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:07:31"},{"dataId":19,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:10:27"},{"dataId":20,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:12:37"},{"dataId":21,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:17:14"},{"dataId":27,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-21 21:11:28"},{"dataId":28,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-21 21:11:31"},{"dataId":29,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-23 17:10:47"},{"dataId":43,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:09"},{"dataId":44,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:24"},{"dataId":45,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:39"},{"dataId":46,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:54"}],"total":327,"rows":20,"page":1}
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
         * list : [{"dataId":7,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":1080,"dataTime":"2018-06-14 13:49:07"},{"dataId":9,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":1080,"dataTime":"2018-06-14 16:38:08"},{"dataId":11,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-15 13:50:22"},{"dataId":12,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:30:07"},{"dataId":13,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:31:37"},{"dataId":14,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:35:36"},{"dataId":15,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:42:46"},{"dataId":16,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:46:23"},{"dataId":17,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 14:46:54"},{"dataId":18,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:07:31"},{"dataId":19,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:10:27"},{"dataId":20,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:12:37"},{"dataId":21,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-19 15:17:14"},{"dataId":27,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-21 21:11:28"},{"dataId":28,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-21 21:11:31"},{"dataId":29,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":54,"dataTime":"2018-06-23 17:10:47"},{"dataId":43,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:09"},{"dataId":44,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:24"},{"dataId":45,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:39"},{"dataId":46,"boardId":"1","transducerId":"1","transducerType":"湿度传感器","transducerData":68,"dataTime":"2018-06-26 15:10:54"}]
         * total : 327
         * rows : 20
         * page : 1
         */

        private int total;
        private int rows;
        private int page;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * dataId : 7
             * boardId : 1
             * transducerId : 1
             * transducerType : 湿度传感器
             * transducerData : 1080.0
             * dataTime : 2018-06-14 13:49:07
             */

            private int dataId;
            private String boardId;
            private String transducerId;
            private String transducerType;
            private double transducerData;
            private String dataTime;

            public int getDataId() {
                return dataId;
            }

            public void setDataId(int dataId) {
                this.dataId = dataId;
            }

            public String getBoardId() {
                return boardId;
            }

            public void setBoardId(String boardId) {
                this.boardId = boardId;
            }

            public String getTransducerId() {
                return transducerId;
            }

            public void setTransducerId(String transducerId) {
                this.transducerId = transducerId;
            }

            public String getTransducerType() {
                return transducerType;
            }

            public void setTransducerType(String transducerType) {
                this.transducerType = transducerType;
            }

            public double getTransducerData() {
                return transducerData;
            }

            public void setTransducerData(double transducerData) {
                this.transducerData = transducerData;
            }

            public String getDataTime() {
                return dataTime;
            }

            public void setDataTime(String dataTime) {
                this.dataTime = dataTime;
            }
        }
    }

    //    /**
//     * status : 200
//     * msg : OK
//     * data : [{"dataId":1,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-14 09:36:28"},{"dataId":2,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-14 09:41:20"},{"dataId":3,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-14 13:33:01"},{"dataId":4,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-14 13:45:26"},{"dataId":5,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-14 13:45:29"},{"dataId":6,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-14 13:49:02"},{"dataId":8,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-14 16:37:47"},{"dataId":10,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-15 13:50:08"},{"dataId":22,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-19 16:25:02"},{"dataId":23,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-21 20:39:52"},{"dataId":24,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-21 20:45:55"},{"dataId":25,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-21 20:53:41"},{"dataId":26,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-21 21:11:25"},{"dataId":30,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-23 20:19:11"},{"dataId":31,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-23 20:19:41"},{"dataId":32,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-24 11:11:58"},{"dataId":33,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-24 11:12:47"},{"dataId":34,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-24 14:20:32"},{"dataId":35,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-24 14:23:01"},{"dataId":37,"boardId":"1","transducerId":"1","transducerType":"温度传感器","transducerData":29,"dataTime":"2018-06-24 14:25:13"}]
//     */
//
//    private int status;
//    private String msg;
//    private List<DataBean> data;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * dataId : 1
//         * boardId : 1
//         * transducerId : 1
//         * transducerType : 温度传感器
//         * transducerData : 29.0
//         * dataTime : 2018-06-14 09:36:28
//         */
//
//        private int dataId;
//        private String boardId;
//        private String transducerId;
//        private String transducerType;
//        private double transducerData;
//        private String dataTime;
//
//        public int getDataId() {
//            return dataId;
//        }
//
//        public void setDataId(int dataId) {
//            this.dataId = dataId;
//        }
//
//        public String getBoardId() {
//            return boardId;
//        }
//
//        public void setBoardId(String boardId) {
//            this.boardId = boardId;
//        }
//
//        public String getTransducerId() {
//            return transducerId;
//        }
//
//        public void setTransducerId(String transducerId) {
//            this.transducerId = transducerId;
//        }
//
//        public String getTransducerType() {
//            return transducerType;
//        }
//
//        public void setTransducerType(String transducerType) {
//            this.transducerType = transducerType;
//        }
//
//        public double getTransducerData() {
//            return transducerData;
//        }
//
//        public void setTransducerData(double transducerData) {
//            this.transducerData = transducerData;
//        }
//
//        public String getDataTime() {
//            return dataTime;
//        }
//
//        public void setDataTime(String dataTime) {
//            this.dataTime = dataTime;
//        }
//    }

}
