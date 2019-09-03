package com.cn.uvfortune.infrastructure.repository.entity;

import java.util.Date;

public class Assets {
    private Integer id;

    private Integer deviceId;

    private String deviceName;

    private String assetsStatus;

    //    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String deviceTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getAssetsStatus() {
        return assetsStatus;
    }

    public void setAssetsStatus(String assetsStatus) {
        this.assetsStatus = assetsStatus == null ? null : assetsStatus.trim();
    }

    public String getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(String deviceTime) {
        this.deviceTime = deviceTime;
    }
}