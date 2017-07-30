package com.light.pojo;

public class LgBonusLevel {
    private Integer id;

    private Integer bonusRate;

    private String bonusLevelName;

    private Integer bonusLevelMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Integer bonusRate) {
        this.bonusRate = bonusRate;
    }

    public String getBonusLevelName() {
        return bonusLevelName;
    }

    public void setBonusLevelName(String bonusLevelName) {
        this.bonusLevelName = bonusLevelName == null ? null : bonusLevelName.trim();
    }

    public Integer getBonusLevelMoney() {
        return bonusLevelMoney;
    }

    public void setBonusLevelMoney(Integer bonusLevelMoney) {
        this.bonusLevelMoney = bonusLevelMoney;
    }
}