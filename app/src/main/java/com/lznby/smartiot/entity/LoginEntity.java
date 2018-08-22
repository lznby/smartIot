package com.lznby.smartiot.entity;

/**
 * @author Lznby
 * @time 2018/6/11 15:30
 * Class Note: 登录信息结果实体类
 */
public class LoginEntity {

    /**
     * status : 200     1001                    1002                    1003
     * msg :    OK      "用户名或密码不能为空"    "用户名或密码不正确"     "用户类型错误"
     * data :   cookies c236133e-565d-4a4b-8057-d37c46d78b32
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
