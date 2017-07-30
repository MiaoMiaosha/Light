package com.light.dto;

import com.light.pojo.LgMarket;

public class MarketOutputBean {

	private Integer mid;
	private Integer userId;
	private String  marketName;
	private Integer country;
	private Integer province;
	private String provinceName;
	private Integer city;
	private String cityName;
	private Integer district;
	private String districtName;
	private Integer town;
	private String townName;
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
	
	public MarketOutputBean(){}
	public MarketOutputBean(LgMarket market,String mprovinceName,String mcityName,
			String mdistrictName,String mtownName){
		mid = market.getMid();
		userId = market.getUserId();
		marketName = market.getMarketName();
		country = market.getCountry();
		province = market.getProvince();
		provinceName = mprovinceName;
		city = market.getCity();
		cityName =mcityName;
		district = market.getDistrict();
		districtName = mdistrictName;
		town = market.getTown();
		townName = mtownName;
		address = market.getAddress();
		area = market.getArea();
		marketCompany = market.getMarketCompany();
		marketIntro = market.getMarketIntro();
		marketActivity = market.getMarketActivity();
		createTime = market.getCreateTime();
		contactName = market.getContactName();
		contactMobile = market.getContactMobile();
		meetAddress = market.getMeetAddress();
		imgUrl = market.getImgUrl();
		status = market.getStatus();
		
	}
	
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
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
		this.marketName = marketName;
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
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getMarketCompany() {
		return marketCompany;
	}
	public void setMarketCompany(String marketCompany) {
		this.marketCompany = marketCompany;
	}
	public String getMarketIntro() {
		return marketIntro;
	}
	public void setMarketIntro(String marketIntro) {
		this.marketIntro = marketIntro;
	}
	public String getMarketActivity() {
		return marketActivity;
	}
	public void setMarketActivity(String marketActivity) {
		this.marketActivity = marketActivity;
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
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getMeetAddress() {
		return meetAddress;
	}
	public void setMeetAddress(String meetAddress) {
		this.meetAddress = meetAddress;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	
}
