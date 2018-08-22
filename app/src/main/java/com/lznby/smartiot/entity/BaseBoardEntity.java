package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/20 18:34
 * Class Note: 采集板信息实体类
 */
public class BaseBoardEntity {

    /**
     * boardId : 2
     * applicationFlag : 0003
     * boardName : 02板
     * boardStatus : 1
     * boardTime : 2018-06-16 14:43:28
     * boardDescription : null
     */

    private String boardId;
    private String applicationFlag;
    private String boardName;
    private int boardStatus;
    private String boardTime;
    private String boardDescription;

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

    public String getBoardDescription() {
        return boardDescription;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }
}
