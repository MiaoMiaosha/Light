package com.light.pojo;

public class LgNewsImage {
    private Integer niId;

    private Integer newsId;

    private String imgUrl;

    public Integer getNiId() {
        return niId;
    }

    public void setNiId(Integer niId) {
        this.niId = niId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}