package com.light.pojo;

public class LgRecord {
    private Integer rid;

    private Integer userId;

    private Integer userType;

    private Integer recordTime;

    private String personName;

    private String mobile;

    private String processOne;

    private String processTwo;

    private String processThree;

    private String amount;

    private Integer recordResult;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Integer recordTime) {
        this.recordTime = recordTime;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getProcessOne() {
        return processOne;
    }

    public void setProcessOne(String processOne) {
        this.processOne = processOne == null ? null : processOne.trim();
    }

    public String getProcessTwo() {
        return processTwo;
    }

    public void setProcessTwo(String processTwo) {
        this.processTwo = processTwo == null ? null : processTwo.trim();
    }

    public String getProcessThree() {
        return processThree;
    }

    public void setProcessThree(String processThree) {
        this.processThree = processThree == null ? null : processThree.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public Integer getRecordResult() {
        return recordResult;
    }

    public void setRecordResult(Integer recordResult) {
        this.recordResult = recordResult;
    }
}