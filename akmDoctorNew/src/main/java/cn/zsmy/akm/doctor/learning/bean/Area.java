package cn.zsmy.akm.doctor.learning.bean;

import java.io.Serializable;

/**
 * Created by sanz on 2016/1/26 14:23
 */
public class Area implements Serializable{
    private String id;
    private String areaName;
    private int status;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
