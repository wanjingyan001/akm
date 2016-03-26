package cn.zsmy.akm.log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * 崩溃日志实体类
 * @author  yutaotao
 *
 * @time 2016/2/29.
 */
@DatabaseTable(tableName = "akm_log_patient")
public class LogError implements Serializable{

    @DatabaseField(generatedId = true)
    private int id;//ID

    @DatabaseField
    private String client_os;//客户端系统

    @DatabaseField
    private String client_ver;//客户端版本

    @DatabaseField
    private String app_type;//应用端类型:1掌尚名医工作室 2阿克曼皮肤医生

    @DatabaseField
    private String app_ver;//客户端版本

    @DatabaseField
    private String model;//机型

    @DatabaseField
    private String screen_resolution;//屏幕分辨率

    @DatabaseField(defaultValue = "")
    private String user_id;//用户id

    @DatabaseField
    private Date log_time;//日志时间

    @DatabaseField
    private String log_info;//日志内容

    private String index;//标记：0未上传 1已上传

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient_os() {
        return client_os;
    }

    public void setClient_os(String client_os) {
        this.client_os = client_os;
    }

    public String getClient_ver() {
        return client_ver;
    }

    public void setClient_ver(String client_ver) {
        this.client_ver = client_ver;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getScreen_resolution() {
        return screen_resolution;
    }

    public void setScreen_resolution(String screen_resolution) {
        this.screen_resolution = screen_resolution;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getLog_time() {
        return log_time;
    }

    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }

    public String getLog_info() {
        return log_info;
    }

    public void setLog_info(String log_info) {
        this.log_info = log_info;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "LogError{" +
                "id='" + id + '\'' +
                ", client_os='" + client_os + '\'' +
                ", client_ver='" + client_ver + '\'' +
                ", app_type='" + app_type + '\'' +
                ", app_ver='" + app_ver + '\'' +
                ", model='" + model + '\'' +
                ", screen_resolution='" + screen_resolution + '\'' +
                ", user_id='" + user_id + '\'' +
                ", log_time=" + log_time +
                ", log_info='" + log_info + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
