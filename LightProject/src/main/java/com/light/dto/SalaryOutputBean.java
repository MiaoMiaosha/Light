package com.light.dto;

import java.util.Map;

import com.light.pojo.LgSalary;

/**
 * 收入输出bean
 * @ClassName: SalaryOutputBean 
 * @author TobyHan
 * @date 2017年2月27日 上午9:48:01 
 *
 */
public class SalaryOutputBean {

	private Integer sid;
	private Integer personCommitTime;
	private Integer employeeId;
	private Integer projectId;
	private Integer salaryType;//收入类型，对应类型表id
	private Integer designContentType;//设计内容，对应表id
	private Integer salaryMoney;
	private String remark;
	private Integer status;
	private Integer createTime;
	private Integer finishTime;
	private Integer operateNo;
	private Integer loginUserId;
	
	private String employeeName;//员工名称
	private String projectMarketName;//工程名称
	private String salaryTypeName;//不为其他时，收入类型名称
	private String designContentTypeName;//不为其他时，设计内容名称
	private String username;
	
	public SalaryOutputBean(){}
	public SalaryOutputBean(LgSalary salary, String eployeeName, 
			String projectMarketName,Map<Integer,String> salaryMap,Map<Integer,String> designContentMap){
		
		 sid = salary.getSid();
		 personCommitTime = salary.getPersonCommitTime();
		 employeeId = salary.getEmployeeId();
		 projectId = salary.getProjectId();
		 salaryType = salary.getSalaryType();
		 designContentType = salary.getDesignContentType();
		 salaryMoney = salary.getSalaryMoney();
		remark = salary.getRemark();
		 status = salary.getStatus();
		 createTime = salary.getCreateTime();
		 finishTime = salary.getFinishTime();
		 operateNo = salary.getOperateNo();
		 loginUserId = salary.getLoginUserId();

		this.employeeName = eployeeName;
		this.projectMarketName = projectMarketName;
		if(salaryType == 12)//收入其他
			salaryTypeName = salary.getSalaryOther();
		else
			salaryTypeName = salaryMap.get(salaryType); 
		if(designContentType == 24)
			designContentTypeName = salary.getDesignModify();
		else if(designContentType == 25)
			designContentTypeName = salary.getDesignOther();
		else 
			designContentTypeName = designContentMap.get(designContentType);
		
	}
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPersonCommitTime() {
		return personCommitTime;
	}
	public void setPersonCommitTime(Integer personCommitTime) {
		this.personCommitTime = personCommitTime;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getSalaryType() {
		return salaryType;
	}
	public void setSalaryType(Integer salaryType) {
		this.salaryType = salaryType;
	}
	public Integer getDesignContentType() {
		return designContentType;
	}
	public void setDesignContentType(Integer designContentType) {
		this.designContentType = designContentType;
	}
	public Integer getSalaryMoney() {
		return salaryMoney;
	}
	public void setSalaryMoney(Integer salaryMoney) {
		this.salaryMoney = salaryMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	public Integer getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Integer finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getOperateNo() {
		return operateNo;
	}
	public void setOperateNo(Integer operateNo) {
		this.operateNo = operateNo;
	}
	public Integer getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getProjectMarketName() {
		return projectMarketName;
	}
	public void setProjectMarketName(String projectMarketName) {
		this.projectMarketName = projectMarketName;
	}
	public String getSalaryTypeName() {
		return salaryTypeName;
	}
	public void setSalaryTypeName(String salaryTypeName) {
		this.salaryTypeName = salaryTypeName;
	}
	public String getDesignContentTypeName() {
		return designContentTypeName;
	}
	public void setDesignContentTypeName(String designContentTypeName) {
		this.designContentTypeName = designContentTypeName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	



}
