package com.icehax.stitch.models;

import com.google.gson.annotations.SerializedName;

public class NewAppBody {
    @SerializedName("app_id")
    private String appId;
    @SerializedName("app_version")
    private String appVersion;
    @SerializedName("device_codename")
    private String deviceCodeName;
    @SerializedName("os")
    private String os;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }



    public String getDeviceCodeName() {
        return deviceCodeName;
    }

    public void setDeviceCodeName(String deviceCodeName) {
        this.deviceCodeName = deviceCodeName;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
