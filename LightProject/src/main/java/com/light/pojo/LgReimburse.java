package com.light.pojo;

public class LgReimburse {
    private Integer rid;

    private Integer reimburseUserId;

    private Integer reimburseUserType;

    private Integer money;

    private String marketName;

    private String event;

    private Integer typeId;

    private Integer invoiceStatus;

    private String remark;

    private Integer createTime;

    private Integer status;

    private Integer operateNo;

    private Integer updateTime;

    private Integer loginUserId;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getReimburseUserId() {
        return reimburseUserId;
    }

    public void setReimburseUserId(Integer reimburseUserId) {
        this.reimburseUserId = reimburseUserId;
    }

    public Integer getReimburseUserType() {
        return reimburseUserType;
    }

    public void setReimburseUserType(Integer reimburseUserType) {
        this.reimburseUserType = reimburseUserType;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName == null ? null : marketName.trim();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
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

    public Integer getOperateNo() {
        return operateNo;
    }

    public void setOperateNo(Integer operateNo) {
        this.operateNo = operateNo;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }
}