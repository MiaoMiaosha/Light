package com.light.dto;
/**
 * 员工收入Outbutbean
 * @ClassName: StaffSalaryCountBean 
 * @author TobyHan
 * @date 2017年3月18日 下午5:27:22 
 *
 */
public class StaffSalaryCountBean {

	private Integer status;
	private Integer bonusMoney;
	private Integer sumSalaryMoney;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSumSalaryMoney() {
		return sumSalaryMoney;
	}
	public void setSumSalaryMoney(Integer sumSalaryMoney) {
		this.sumSalaryMoney = sumSalaryMoney;
	}
	public Integer getBonusMoney() {
		return bonusMoney;
	}
	public void setBonusMoney(Integer bonusMoney) {
		this.bonusMoney = bonusMoney;
	}
	
	
}
