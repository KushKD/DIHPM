package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

/**
 * @author Kush.Dhawan
 * @project IdCardHPAppleSeason
 * @Time 02, 07 , 2020
 */
public class IDCardOwnerServerVerify implements Serializable {

    private String imageurl;
    private String idcardUrl;
    private Integer vehicleOwnerId;
    private String idCardNumber;
    private String vehicleDistrictName;
    private Integer vehicleDistrictId;
    private String vehicleBarrierName;
    private Integer vehicleBarrierId;
    private String vehicleTypeName;
    private Integer vehicleTypeId;
    private String VehicleOwnerType;
    private String vehicleOwnerName;
    private Long vehicleOwnerMobileNumber;
    private String isValidFrom;
    private String isValidUpto;
    private String vehicleOwnerAadhaarNumber;
    private String vehicleOwnerVehicleNumber;
    private String vehicleOwnerChassisNumber;
    private String vehicleOwnerEngineNumber;
    private String vehicleOwnerDrivingLicence;
    private Integer dataEnteredBy;
    private String dataEnteredByName;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getIdcardUrl() {
        return idcardUrl;
    }

    public void setIdcardUrl(String idcardUrl) {
        this.idcardUrl = idcardUrl;
    }

    public Integer getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public void setVehicleOwnerId(Integer vehicleOwnerId) {
        this.vehicleOwnerId = vehicleOwnerId;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getVehicleDistrictName() {
        return vehicleDistrictName;
    }

    public void setVehicleDistrictName(String vehicleDistrictName) {
        this.vehicleDistrictName = vehicleDistrictName;
    }

    public Integer getVehicleDistrictId() {
        return vehicleDistrictId;
    }

    public void setVehicleDistrictId(Integer vehicleDistrictId) {
        this.vehicleDistrictId = vehicleDistrictId;
    }

    public String getVehicleBarrierName() {
        return vehicleBarrierName;
    }

    public void setVehicleBarrierName(String vehicleBarrierName) {
        this.vehicleBarrierName = vehicleBarrierName;
    }

    public Integer getVehicleBarrierId() {
        return vehicleBarrierId;
    }

    public void setVehicleBarrierId(Integer vehicleBarrierId) {
        this.vehicleBarrierId = vehicleBarrierId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleOwnerType() {
        return VehicleOwnerType;
    }

    public void setVehicleOwnerType(String vehicleOwnerType) {
        VehicleOwnerType = vehicleOwnerType;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public Long getVehicleOwnerMobileNumber() {
        return vehicleOwnerMobileNumber;
    }

    public void setVehicleOwnerMobileNumber(Long vehicleOwnerMobileNumber) {
        this.vehicleOwnerMobileNumber = vehicleOwnerMobileNumber;
    }

    public String getIsValidFrom() {
        return isValidFrom;
    }

    public void setIsValidFrom(String isValidFrom) {
        this.isValidFrom = isValidFrom;
    }

    public String getIsValidUpto() {
        return isValidUpto;
    }

    public void setIsValidUpto(String isValidUpto) {
        this.isValidUpto = isValidUpto;
    }

    public String getVehicleOwnerAadhaarNumber() {
        return vehicleOwnerAadhaarNumber;
    }

    public void setVehicleOwnerAadhaarNumber(String vehicleOwnerAadhaarNumber) {
        this.vehicleOwnerAadhaarNumber = vehicleOwnerAadhaarNumber;
    }

    public String getVehicleOwnerVehicleNumber() {
        return vehicleOwnerVehicleNumber;
    }

    public void setVehicleOwnerVehicleNumber(String vehicleOwnerVehicleNumber) {
        this.vehicleOwnerVehicleNumber = vehicleOwnerVehicleNumber;
    }

    public String getVehicleOwnerChassisNumber() {
        return vehicleOwnerChassisNumber;
    }

    public void setVehicleOwnerChassisNumber(String vehicleOwnerChassisNumber) {
        this.vehicleOwnerChassisNumber = vehicleOwnerChassisNumber;
    }

    public String getVehicleOwnerEngineNumber() {
        return vehicleOwnerEngineNumber;
    }

    public void setVehicleOwnerEngineNumber(String vehicleOwnerEngineNumber) {
        this.vehicleOwnerEngineNumber = vehicleOwnerEngineNumber;
    }

    public String getVehicleOwnerDrivingLicence() {
        return vehicleOwnerDrivingLicence;
    }

    public void setVehicleOwnerDrivingLicence(String vehicleOwnerDrivingLicence) {
        this.vehicleOwnerDrivingLicence = vehicleOwnerDrivingLicence;
    }

    public Integer getDataEnteredBy() {
        return dataEnteredBy;
    }

    public void setDataEnteredBy(Integer dataEnteredBy) {
        this.dataEnteredBy = dataEnteredBy;
    }

    public String getDataEnteredByName() {
        return dataEnteredByName;
    }

    public void setDataEnteredByName(String dataEnteredByName) {
        this.dataEnteredByName = dataEnteredByName;
    }

    @Override
    public String toString() {
        return "IDCardOwnerServerVerify{" +
                "imageurl='" + imageurl + '\'' +
                ", idcardUrl='" + idcardUrl + '\'' +
                ", vehicleOwnerId=" + vehicleOwnerId +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", vehicleDistrictName='" + vehicleDistrictName + '\'' +
                ", vehicleDistrictId=" + vehicleDistrictId +
                ", vehicleBarrierName='" + vehicleBarrierName + '\'' +
                ", vehicleBarrierId=" + vehicleBarrierId +
                ", vehicleTypeName='" + vehicleTypeName + '\'' +
                ", vehicleTypeId=" + vehicleTypeId +
                ", VehicleOwnerType='" + VehicleOwnerType + '\'' +
                ", vehicleOwnerName='" + vehicleOwnerName + '\'' +
                ", vehicleOwnerMobileNumber=" + vehicleOwnerMobileNumber +
                ", isValidFrom='" + isValidFrom + '\'' +
                ", isValidUpto='" + isValidUpto + '\'' +
                ", vehicleOwnerAadhaarNumber='" + vehicleOwnerAadhaarNumber + '\'' +
                ", vehicleOwnerVehicleNumber='" + vehicleOwnerVehicleNumber + '\'' +
                ", vehicleOwnerChassisNumber='" + vehicleOwnerChassisNumber + '\'' +
                ", vehicleOwnerEngineNumber='" + vehicleOwnerEngineNumber + '\'' +
                ", vehicleOwnerDrivingLicence='" + vehicleOwnerDrivingLicence + '\'' +
                ", dataEnteredBy=" + dataEnteredBy +
                ", dataEnteredByName='" + dataEnteredByName + '\'' +
                '}';
    }
}

