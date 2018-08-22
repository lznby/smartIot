package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/24 11:15
 * Class Note: 删除子账号结果实体类
 */
public class DeleteChildAccountEntity {

    /**
     * status : 200
     * msg : OK
     * data : null
     */

    private int status;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
