package com.jack.rms.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class House {
    private Integer id;

    private String hType;

    private String rentType;

    private String hName;

    private String hAddress;

    private Integer hBedroomNum;

    private Integer hLivingroomNum;

    private Integer hWashroomNum;

    private Integer hArea;

    private String hDecation;

    private String hDirection;

    private Integer hFloorTotal;

    private Integer hFloor;

    private String hNo;

    private Integer hRent;

    private String hRentType;

    private String hTitle;

    private String hContent;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date hCheckinDate;

    private String hContact;

    private String hPhone;

    private Long hLon;

    private Long hLat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gethType() {
        return hType;
    }

    public void sethType(String hType) {
        this.hType = hType == null ? null : hType.trim();
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType == null ? null : rentType.trim();
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName == null ? null : hName.trim();
    }

    public String gethAddress() {
        return hAddress;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress == null ? null : hAddress.trim();
    }

    public Integer gethBedroomNum() {
        return hBedroomNum;
    }

    public void sethBedroomNum(Integer hBedroomNum) {
        this.hBedroomNum = hBedroomNum;
    }

    public Integer gethLivingroomNum() {
        return hLivingroomNum;
    }

    public void sethLivingroomNum(Integer hLivingroomNum) {
        this.hLivingroomNum = hLivingroomNum;
    }

    public Integer gethWashroomNum() {
        return hWashroomNum;
    }

    public void sethWashroomNum(Integer hWashroomNum) {
        this.hWashroomNum = hWashroomNum;
    }

    public Integer gethArea() {
        return hArea;
    }

    public void sethArea(Integer hArea) {
        this.hArea = hArea;
    }

    public String gethDecation() {
        return hDecation;
    }

    public void sethDecation(String hDecation) {
        this.hDecation = hDecation == null ? null : hDecation.trim();
    }

    public String gethDirection() {
        return hDirection;
    }

    public void sethDirection(String hDirection) {
        this.hDirection = hDirection == null ? null : hDirection.trim();
    }

    public Integer gethFloorTotal() {
        return hFloorTotal;
    }

    public void sethFloorTotal(Integer hFloorTotal) {
        this.hFloorTotal = hFloorTotal;
    }

    public Integer gethFloor() {
        return hFloor;
    }

    public void sethFloor(Integer hFloor) {
        this.hFloor = hFloor;
    }

    public String gethNo() {
        return hNo;
    }

    public void sethNo(String hNo) {
        this.hNo = hNo == null ? null : hNo.trim();
    }

    public Integer gethRent() {
        return hRent;
    }

    public void sethRent(Integer hRent) {
        this.hRent = hRent;
    }

    public String gethRentType() {
        return hRentType;
    }

    public void sethRentType(String hRentType) {
        this.hRentType = hRentType == null ? null : hRentType.trim();
    }

    public String gethTitle() {
        return hTitle;
    }

    public void sethTitle(String hTitle) {
        this.hTitle = hTitle == null ? null : hTitle.trim();
    }

    public String gethContent() {
        return hContent;
    }

    public void sethContent(String hContent) {
        this.hContent = hContent == null ? null : hContent.trim();
    }

    public Date gethCheckinDate() {
        return hCheckinDate;
    }

    public void sethCheckinDate(Date hCheckinDate) {
        this.hCheckinDate = hCheckinDate;
    }

    public String gethContact() {
        return hContact;
    }

    public void sethContact(String hContact) {
        this.hContact = hContact == null ? null : hContact.trim();
    }

    public String gethPhone() {
        return hPhone;
    }

    public void sethPhone(String hPhone) {
        this.hPhone = hPhone == null ? null : hPhone.trim();
    }

    public Long gethLon() {
        return hLon;
    }

    public void sethLon(Long hLon) {
        this.hLon = hLon;
    }

    public Long gethLat() {
        return hLat;
    }

    public void sethLat(Long hLat) {
        this.hLat = hLat;
    }
}