package com.light.pojo;

public class LgPartner {
    private Integer pid;

    private String cooperateContent;

    private String chiefPerson;

    private String mobile;

    private String address;

    private String bankAccount;

    private String bankAccountNo;

    private String bankBranch;

    private String remark;

    private Integer createTime;

    private Integer status;

    private String cooperateName;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCooperateContent() {
        return cooperateContent;
    }

    public void setCooperateContent(String cooperateContent) {
        this.cooperateContent = cooperateContent == null ? null : cooperateContent.trim();
    }

    public String getChiefPerson() {
        return chiefPerson;
    }

    public void setChiefPerson(String chiefPerson) {
        this.chiefPerson = chiefPerson == null ? null : chiefPerson.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo == null ? null : bankAccountNo.trim();
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch == null ? null : bankBranch.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCooperateName() {
        return cooperateName;
    }

    public void setCooperateName(String cooperateName) {
        this.cooperateName = cooperateName == null ? null : cooperateName.trim();
    }
}