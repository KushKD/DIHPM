package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

public class IDCardServerObject implements Serializable {

    private String imageUrl;
    private String driverName;
    private String idCardNumber;
    private String generateIDCardUrl_;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGenerateIDCardUrl_() {
        return generateIDCardUrl_;
    }

    public void setGenerateIDCardUrl_(String generateIDCardUrl_) {
        this.generateIDCardUrl_ = generateIDCardUrl_;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    @Override
    public String toString() {
        return "IDCardServerObject{" +
                "imageUrl='" + imageUrl + '\'' +
                ", driverName='" + driverName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", generateIDCardUrl_='" + generateIDCardUrl_ + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
