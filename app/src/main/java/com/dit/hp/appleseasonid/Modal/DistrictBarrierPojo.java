package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 01, 05 , 2020
 */
public class DistrictBarrierPojo implements Serializable {

    private String barrierId;
    private String districtId;
    private String barrierName;


    public String getBarrierId() {
        return barrierId;
    }

    public void setBarrierId(String barrierId) {
        this.barrierId = barrierId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getBarrierName() {
        return barrierName;
    }

    public void setBarrierName(String barrierName) {
        this.barrierName = barrierName;
    }

    @Override
    public String toString() {
        return barrierName;
    }
}
