package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

public class IDCardServerObject implements Serializable {

    private String imageUrl;
    private String driverName;
    private String idCardNumber;

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
                '}';
    }
}
