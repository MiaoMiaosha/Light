package com.light.pojo;

public class LgMainBusiness {
    private Integer mbid;

    private String mainBusinessName;

    public Integer getMbid() {
        return mbid;
    }

    public void setMbid(Integer mbid) {
        this.mbid = mbid;
    }

    public String getMainBusinessName() {
        return mainBusinessName;
    }

    public void setMainBusinessName(String mainBusinessName) {
        this.mainBusinessName = mainBusinessName == null ? null : mainBusinessName.trim();
    }
}