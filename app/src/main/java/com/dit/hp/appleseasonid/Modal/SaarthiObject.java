package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

public class SaarthiObject implements Serializable {


    private Integer errorCode;
    private String errorMessage;
    private String dlLicNum;
    private String dlLicName;
    private String dlLicStatus;
    private String issuing_authority;
    private String dlNonTransValidTill;
    private String dlTransValidTill;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDlLicNum() {
        return dlLicNum;
    }

    public void setDlLicNum(String dlLicNum) {
        this.dlLicNum = dlLicNum;
    }

    public String getDlLicName() {
        return dlLicName;
    }

    public void setDlLicName(String dlLicName) {
        this.dlLicName = dlLicName;
    }

    public String getDlLicStatus() {
        return dlLicStatus;
    }

    public void setDlLicStatus(String dlLicStatus) {
        this.dlLicStatus = dlLicStatus;
    }

    public String getIssuing_authority() {
        return issuing_authority;
    }

    public void setIssuing_authority(String issuing_authority) {
        this.issuing_authority = issuing_authority;
    }

    public String getDlNonTransValidTill() {
        return dlNonTransValidTill;
    }

    public void setDlNonTransValidTill(String dlNonTransValidTill) {
        this.dlNonTransValidTill = dlNonTransValidTill;
    }

    public String getDlTransValidTill() {
        return dlTransValidTill;
    }

    public void setDlTransValidTill(String dlTransValidTill) {
        this.dlTransValidTill = dlTransValidTill;
    }

    @Override
    public String toString() {
        return "SaarthiObject{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", dlLicNum='" + dlLicNum + '\'' +
                ", dlLicName='" + dlLicName + '\'' +
                ", dlLicStatus='" + dlLicStatus + '\'' +
                ", issuing_authority='" + issuing_authority + '\'' +
                ", dlNonTransValidTill='" + dlNonTransValidTill + '\'' +
                ", dlTransValidTill='" + dlTransValidTill + '\'' +
                '}';
    }
}
