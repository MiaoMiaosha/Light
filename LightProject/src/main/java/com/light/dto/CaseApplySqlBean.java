package com.light.dto;
/**
 * 联合case和apply表结果字段
 * @ClassName: CaseApplyOutBean 
 * @author TobyHan
 * @date 2017年2月18日 上午10:17:20 
 *
 */
public class CaseApplySqlBean {

	private Integer caid;//申请id
	private Integer userId;//用户id
	private Integer caseId;//案例id
	private Integer createTime;//申请时间
	private Integer state;//申请状态
	private String caseName;//案例名称
	private String area;//面积
	
	public CaseApplySqlBean(){}

	public Integer getCaid() {
		return caid;
	}

	public void setCaid(Integer caid) {
		this.caid = caid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
