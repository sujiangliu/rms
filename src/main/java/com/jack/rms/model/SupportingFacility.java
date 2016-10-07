package com.jack.rms.model;

public class SupportingFacility {
    private Integer id;

    private Integer hId;

    private String sfId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public String getSfId() {
        return sfId;
    }

    public void setSfId(String sfId) {
        this.sfId = sfId == null ? null : sfId.trim();
    }
}