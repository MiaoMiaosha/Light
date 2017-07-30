package com.light.pojo;

public class LgMarket {
    private Integer mid;

    private Integer userId;

    private String marketName;

    private Integer country;

    private Integer province;

    private Integer city;

    private Integer district;

    private Integer town;

    private String address;

    private String area;

    private String marketCompany;

    private String marketIntro;

    private String marketActivity;

    private Integer createTime;

    private String contactName;

    private String contactMobile;

    private String meetAddress;

    private String imgUrl;

    private Integer status;

    private String contractUrl;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName == null ? null : marketName.trim();
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getTown() {
        return town;
    }

    public void setTown(Integer town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getMarketCompany() {
        return marketCompany;
    }

    public void setMarketCompany(String marketCompany) {
        this.marketCompany = marketCompany == null ? null : marketCompany.trim();
    }

    public String getMarketIntro() {
        return marketIntro;
    }

    public void setMarketIntro(String marketIntro) {
        this.marketIntro = marketIntro == null ? null : marketIntro.trim();
    }

    public String getMarketActivity() {
        return marketActivity;
    }

    public void setMarketActivity(String marketActivity) {
        this.marketActivity = marketActivity == null ? null : marketActivity.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public String getMeetAddress() {
        return meetAddress;
    }

    public void setMeetAddress(String meetAddress) {
        this.meetAddress = meetAddress == null ? null : meetAddress.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl == null ? null : contractUrl.trim();
    }
}