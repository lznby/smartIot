package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/23 20:56
 * Class Note: 添加子账号结果类
 */
public class AddChildAccountEntity {

    /**
     * status : 200
     * msg : OK
     * data : 0003hi
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
