package com.lznby.smartiot.entity;

import java.util.List;

/**
 * @author Lznby
 * @time 2018/6/20 10:08
 * Class Note: 所有板信息实体类
 */
public class AllBoardsInformationEntity {

    /**
     * status : 200
     * msg : OK
     * data : {"normalBoards":[{"boardId":"3","applicationFlag":"0003","boardName":"03板","boardStatus":0,"boardTime":"2018-06-20 16:33:08","boardDescription":null}],"abnormalBoards":[{"boardId":"1","applicationFlag":"0003","boardName":"01板","boardStatus":2,"boardTime":"2018-06-14 20:10:04","boardDescription":null},{"boardId":"2","applicationFlag":"0003","boardName":"02板","boardStatus":1,"boardTime":"2018-06-16 14:43:28","boardDescription":null}]}
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
        private List<NormalBoardsBean> normalBoards;
        private List<AbnormalBoardsBean> abnormalBoards;

        public List<NormalBoardsBean> getNormalBoards() {
            return normalBoards;
        }

        public void setNormalBoards(List<NormalBoardsBean> normalBoards) {
            this.normalBoards = normalBoards;
        }

        public List<AbnormalBoardsBean> getAbnormalBoards() {
            return abnormalBoards;
        }

        public void setAbnormalBoards(List<AbnormalBoardsBean> abnormalBoards) {
            this.abnormalBoards = abnormalBoards;
        }

        public static class NormalBoardsBean {
            /**
             * boardId : 3
             * applicationFlag : 0003
             * boardName : 03板
             * boardStatus : 0
             * boardTime : 2018-06-20 16:33:08
             * boardDescription : null
             */

            private String boardId;
            private String applicationFlag;
            private String boardName;
            private int boardStatus;
            private String boardTime;
            private Object boardDescription;

            public String getBoardId() {
                return boardId;
            }

            public void setBoardId(String boardId) {
                this.boardId = boardId;
            }

            public String getApplicationFlag() {
                return applicationFlag;
            }

            public void setApplicationFlag(String applicationFlag) {
                this.applicationFlag = applicationFlag;
            }

            public String getBoardName() {
                return boardName;
            }

            public void setBoardName(String boardName) {
                this.boardName = boardName;
            }

            public int getBoardStatus() {
                return boardStatus;
            }

            public void setBoardStatus(int boardStatus) {
                this.boardStatus = boardStatus;
            }

            public String getBoardTime() {
                return boardTime;
            }

            public void setBoardTime(String boardTime) {
                this.boardTime = boardTime;
            }

            public Object getBoardDescription() {
                return boardDescription;
            }

            public void setBoardDescription(Object boardDescription) {
                this.boardDescription = boardDescription;
            }
        }

        public static class AbnormalBoardsBean {
            /**
             * boardId : 1
             * applicationFlag : 0003
             * boardName : 01板
             * boardStatus : 2
             * boardTime : 2018-06-14 20:10:04
             * boardDescription : null
             */

            private String boardId;
            private String applicationFlag;
            private String boardName;
            private int boardStatus;
            private String boardTime;
            private Object boardDescription;

            public String getBoardId() {
                return boardId;
            }

            public void setBoardId(String boardId) {
                this.boardId = boardId;
            }

            public String getApplicationFlag() {
                return applicationFlag;
            }

            public void setApplicationFlag(String applicationFlag) {
                this.applicationFlag = applicationFlag;
            }

            public String getBoardName() {
                return boardName;
            }

            public void setBoardName(String boardName) {
                this.boardName = boardName;
            }

            public int getBoardStatus() {
                return boardStatus;
            }

            public void setBoardStatus(int boardStatus) {
                this.boardStatus = boardStatus;
            }

            public String getBoardTime() {
                return boardTime;
            }

            public void setBoardTime(String boardTime) {
                this.boardTime = boardTime;
            }

            public Object getBoardDescription() {
                return boardDescription;
            }

            public void setBoardDescription(Object boardDescription) {
                this.boardDescription = boardDescription;
            }
        }
    }
}
