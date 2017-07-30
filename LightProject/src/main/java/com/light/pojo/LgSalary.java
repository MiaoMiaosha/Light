package com.light.pojo;

public class LgSalary {
    private Integer sid;

    private Integer personCommitTime;

    private Integer employeeId;

    private Integer projectId;

    private Integer salaryType;

    private Integer designContentType;

    private Integer salaryMoney;

    private String remark;

    private String salaryOther;

    private String designOther;

    private String designModify;

    private Integer status;

    private Integer createTime;

    private Integer finishTime;

    private Integer operateNo;

    private Integer loginUserId;

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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSalaryOther() {
        return salaryOther;
    }

    public void setSalaryOther(String salaryOther) {
        this.salaryOther = salaryOther == null ? null : salaryOther.trim();
    }

    public String getDesignOther() {
        return designOther;
    }

    public void setDesignOther(String designOther) {
        this.designOther = designOther == null ? null : designOther.trim();
    }

    public String getDesignModify() {
        return designModify;
    }

    public void setDesignModify(String designModify) {
        this.designModify = designModify == null ? null : designModify.trim();
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
}