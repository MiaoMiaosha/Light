package com.light.pojo;

public class LgMarketFiles {
    private Integer fid;

    private Integer marketId;

    private String fileUrl;

    private String minetype;

    private Integer createTime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getMinetype() {
        return minetype;
    }

    public void setMinetype(String minetype) {
        this.minetype = minetype == null ? null : minetype.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
}