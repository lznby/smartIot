package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/29 11:35
 * Class Note: 请求返回结果实体类
 */
public class RequestResponseEntity {

    /**
     * status : 200
     * msg : OK
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
