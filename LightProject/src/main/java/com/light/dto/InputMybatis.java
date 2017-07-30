package com.light.dto;

public class InputMybatis {

	private String keyword;
	private Integer userId;
	private Integer dateNo;
	
	public InputMybatis(){}
	
	public InputMybatis(String keyword){
		this.keyword = keyword;
	}
	public InputMybatis(String keyword,Integer userId,Integer dateNo){
		this.keyword = keyword;
		this.userId = userId;
		this.dateNo = dateNo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDateNo() {
		return dateNo;
	}

	public void setDateNo(Integer dateNo) {
		this.dateNo = dateNo;
	}
	
}
