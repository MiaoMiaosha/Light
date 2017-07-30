package com.light.pojo;

public class LgAccounting {
    private Integer aid;

    private Integer receiveDate;

    private Integer marketId;

    private String marketName;

    private Integer typeId;

    private Integer receiveBank;

    private Integer receiveMoney;

    private String remark;

    private Integer invoiceStatus;

    private Integer invoiceTime;

    private String invoicePic;

    private Integer updateTime;

    private Integer operateNo;

    private Integer loginUserId;

    private Integer status;

    private Integer projectId;
    
    //市场名称
    private String projectMarketName;
    //款项类型
    private String typeName;
    
    
    public String getProjectMarketName() {
		return projectMarketName;
	}

	public void setProjectMarketName(String projectMarketName) {
		this.projectMarketName = projectMarketName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Integer receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName == null ? null : marketName.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(Integer receiveBank) {
        this.receiveBank = receiveBank;
    }

    public Integer getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Integer receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Integer invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getInvoicePic() {
        return invoicePic;
    }

    public void setInvoicePic(String invoicePic) {
        this.invoicePic = invoicePic == null ? null : invoicePic.trim();
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}