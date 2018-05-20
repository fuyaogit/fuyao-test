package com.linktrust.fuyao.bean;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class Privilege implements Serializable {
    private static final long serialVersionUID = 2L;

    private String priId;
    private String priName;
    private int type;
    private int status;
    private String priUrl;
    private String name;
    private String relationId;
    private String detailsId;
    private String fatherMenuId;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public String getPriId() {
        return priId;
    }

    public void setPriId(String priId) {
        this.priId = priId == null ? null : priId.trim();
    }

    public String getPriName() {
        return priName;
    }

    public void setPriName(String priName) {
        this.priName = priName == null ? null : priName.trim();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPriUrl() {
        return priUrl;
    }

    public void setPriUrl(String priUrl) {
        this.priUrl = priUrl == null ? null : priUrl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherMenuId() {
        return fatherMenuId;
    }

    public void setFatherMenuId(String fatherMenuId) {
        this.fatherMenuId = fatherMenuId.equals("") ? null : fatherMenuId;
    }
}
