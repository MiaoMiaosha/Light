package com.light.dto;

import com.light.pojo.LgLoginCuslist;
import com.light.pojo.LgLoginUser;

public class StaffAndCusInfoBean {

	private Integer loginUserId;
	private String nickname;
	private String headimgurl;
	private String roleIds;
	
	public StaffAndCusInfoBean(){}
	public StaffAndCusInfoBean(LgLoginUser user){
		this.loginUserId = user.getId();
		this.nickname = user.getNickName();
		this.headimgurl = user.getHeadImg();
	}
	public StaffAndCusInfoBean(LgLoginCuslist user){
		this.loginUserId = user.getId();
		this.nickname = user.getNickname();
		this.headimgurl = user.getHeadimgurl();
	}
	
	
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public Integer getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	
}
