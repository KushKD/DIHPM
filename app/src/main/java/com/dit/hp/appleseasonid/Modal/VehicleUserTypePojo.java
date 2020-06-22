package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

public class VehicleUserTypePojo implements Serializable {

    private String vehicleOwnerTypeId;
    private String vehicleOwnerTypeName;
    private String active;

    public String getVehicleOwnerTypeId() {
        return vehicleOwnerTypeId;
    }

    public void setVehicleOwnerTypeId(String vehicleOwnerTypeId) {
        this.vehicleOwnerTypeId = vehicleOwnerTypeId;
    }

    public String getVehicleOwnerTypeName() {
        return vehicleOwnerTypeName;
    }

    public void setVehicleOwnerTypeName(String vehicleOwnerTypeName) {
        this.vehicleOwnerTypeName = vehicleOwnerTypeName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return vehicleOwnerTypeName;
    }
}
