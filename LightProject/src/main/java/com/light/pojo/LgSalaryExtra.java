package com.light.pojo;

public class LgSalaryExtra {
    private Integer tid;

    private String typeName;

    private Integer type;

    private Integer typeMoney;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeMoney() {
        return typeMoney;
    }

    public void setTypeMoney(Integer typeMoney) {
        this.typeMoney = typeMoney;
    }
}