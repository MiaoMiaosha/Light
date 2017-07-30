package com.light.pojo;

public class LgTrainSoftware {
    private Integer sid;

    private String name;

    private String imgUrl;

    private String content;

    private Integer createTime;

    private String softwareIntro;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getSoftwareIntro() {
        return softwareIntro;
    }

    public void setSoftwareIntro(String softwareIntro) {
        this.softwareIntro = softwareIntro == null ? null : softwareIntro.trim();
    }
}