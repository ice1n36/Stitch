package com.icehax.stitch.models;

import com.google.gson.annotations.SerializedName;

public class NewAppResponse {
    @SerializedName("app_id")
    private String appId;
    @SerializedName("app_version")
    private String appVersion;
    @SerializedName("hash")
    private String hash;
    @SerializedName("k_id")
    private String kId;
    @SerializedName("status")
    private String status;

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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
