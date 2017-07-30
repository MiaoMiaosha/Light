package com.light.pojo;

public class LgAccountingExtra {
    private Integer id;

    private String typeName;

    private Integer typeContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(Integer typeContent) {
        this.typeContent = typeContent;
    }
}