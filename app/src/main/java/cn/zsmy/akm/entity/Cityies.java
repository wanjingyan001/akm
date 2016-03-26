package cn.zsmy.akm.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class Cityies {

    /**
     * code : SUCC
     * message : 返回城市信息成功
     * data : [{"id":"0f23e3d1-f52c-4539-ba22-b1ac14089b04","name":"泰州","code":"TZ","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"2d7634fd-cbf1-4d0f-9759-2a7cb48174bf","name":"﻿南京","code":"NJ","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"45fdd8fc-efda-420e-affe-9c6ec1390c14","name":"南通","code":"NT","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"4a1f9c97-3dc1-43be-93ee-0cde01c71a9a","name":"常州","code":"CZ","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"5a22e7a3-15ea-427d-95a1-958aabf88280","name":"镇江","code":"ZJ","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"5ad64f9e-1384-4474-bb2e-5c0309e2566c","name":"宿迁","code":"SC","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"78a08cf4-2471-485f-b126-4e5983dc3b42","name":"扬州","code":"YZ","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"79428d3a-7dd2-4c5a-ad4c-e8cf7bd7a025","name":"盐城","code":"YC","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"d7453a79-b41c-4b2e-a175-bff898b82fec","name":"徐州","code":"XZ","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"dba92091-2992-4c8d-9056-3cd029c5561b","name":"淮阴","code":"HY","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"eafa7da1-eed6-4f90-b187-33a262864494","name":"无锡","code":"WX","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"f240311c-499f-4182-b7f1-6f8b431d3e4f","name":"苏州","code":"SZ","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"},{"id":"fe02b085-7adb-4f83-aaa4-1c193d3b6beb","name":"连云港","code":"LYG","level":2,"parentId":"98cbbec7-0343-4433-b0de-ad04322aa7ag"}]
     */

    private String code;
    private String message;
    /**
     * id : 0f23e3d1-f52c-4539-ba22-b1ac14089b04
     * name : 泰州
     * code : TZ
     * level : 2
     * parentId : 98cbbec7-0343-4433-b0de-ad04322aa7ag
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String name;
        private String code;
        private int level;
        private String parentId;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public int getLevel() {
            return level;
        }

        public String getParentId() {
            return parentId;
        }
    }
}
