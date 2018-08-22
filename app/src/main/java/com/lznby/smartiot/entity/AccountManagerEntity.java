package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/23 13:47
 * Class Note: 个人密码修改结果实体类
 */
public class AccountManagerEntity {

    /**
     * status : 1002
     * msg : 两次密码不一致
     * data : null
     */

    private int status;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
