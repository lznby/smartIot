package com.lznby.smartiot.entity;

import java.io.Serializable;
/**
 * @author Lznby
 * @time 2018/6/29 15:30
 * Class Note: 折线图数据类
 */
public class Pressure implements Serializable {
    private float pressure;
    private float pressure_minlevel;
    private float pressure_standard;
    private float pressure_maxlevel;
    private String time;

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getPressure_minlevel() {
        return pressure_minlevel;
    }

    public void setPressure_minlevel(float pressure_minlevel) {
        this.pressure_minlevel = pressure_minlevel;
    }

    public float getPressure_standard() {
        return pressure_standard;
    }

    public void setPressure_standard(float pressure_standard) {
        this.pressure_standard = pressure_standard;
    }

    public float getPressure_maxlevel() {
        return pressure_maxlevel;
    }

    public void setPressure_maxlevel(float pressure_maxlevel) {
        this.pressure_maxlevel = pressure_maxlevel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
