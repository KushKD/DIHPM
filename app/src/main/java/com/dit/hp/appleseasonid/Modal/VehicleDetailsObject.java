package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

public class VehicleDetailsObject implements Serializable {

    /**
     * <VehicleDetails>
     *     <stautsMessage>OK</stautsMessage>
     *     <rc_regn_no>DL4SBG8823</rc_regn_no>
     *     <rc_chasi_no>Test Chassis No 40QP3317G9</rc_chasi_no>
     *     <rc_eng_no>Test Engine No 7264151583</rc_eng_no>
     *     <rc_fit_upto>07-Aug-2020</rc_fit_upto>
     *     <rc_registered_at>DelhiCourt</rc_registered_at>
     *     <rc_status_as_on>07-Aug-2020</rc_status_as_on>
     *     <rc_status>ACTIVE/RC SURRENDER/NOC ISSUED</rc_status>
     * </VehicleDetails>
     */

    private String rcRegistrationNo;
    private String rcChassisNo;
    private String rcEngineNumber;
    private String rcFitUpto;
    private String rcRegisteredAt;
    private String rcStatus;
    private String rcStatusAsOn;
    private String rcOwnerName;

    public String getRcOwnerName() {
        return rcOwnerName;
    }

    public void setRcOwnerName(String rcOwnerName) {
        this.rcOwnerName = rcOwnerName;
    }

    public String getRcRegistrationNo() {
        return rcRegistrationNo;
    }

    public void setRcRegistrationNo(String rcRegistrationNo) {
        this.rcRegistrationNo = rcRegistrationNo;
    }

    public String getRcChassisNo() {
        return rcChassisNo;
    }

    public void setRcChassisNo(String rcChassisNo) {
        this.rcChassisNo = rcChassisNo;
    }

    public String getRcEngineNumber() {
        return rcEngineNumber;
    }

    public void setRcEngineNumber(String rcEngineNumber) {
        this.rcEngineNumber = rcEngineNumber;
    }

    public String getRcFitUpto() {
        return rcFitUpto;
    }

    public void setRcFitUpto(String rcFitUpto) {
        this.rcFitUpto = rcFitUpto;
    }

    public String getRcRegisteredAt() {
        return rcRegisteredAt;
    }

    public void setRcRegisteredAt(String rcRegisteredAt) {
        this.rcRegisteredAt = rcRegisteredAt;
    }

    public String getRcStatus() {
        return rcStatus;
    }

    public void setRcStatus(String rcStatus) {
        this.rcStatus = rcStatus;
    }

    public String getRcStatusAsOn() {
        return rcStatusAsOn;
    }

    public void setRcStatusAsOn(String rcStatusAsOn) {
        this.rcStatusAsOn = rcStatusAsOn;
    }

    @Override
    public String toString() {
        return "VehicleDetailsObject{" +
                "rcRegistrationNo='" + rcRegistrationNo + '\'' +
                ", rcChassisNo='" + rcChassisNo + '\'' +
                ", rcEngineNumber='" + rcEngineNumber + '\'' +
                ", rcFitUpto='" + rcFitUpto + '\'' +
                ", rcRegisteredAt='" + rcRegisteredAt + '\'' +
                ", rcStatus='" + rcStatus + '\'' +
                ", rcStatusAsOn='" + rcStatusAsOn + '\'' +
                ", rcOwnerName='" + rcOwnerName + '\'' +
                '}';
    }
}
