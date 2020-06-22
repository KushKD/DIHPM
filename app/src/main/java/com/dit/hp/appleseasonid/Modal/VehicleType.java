package com.dit.hp.appleseasonid.Modal;

import java.io.Serializable;

public class VehicleType implements Serializable {

    private String vehicleId;
    private String vehicleName;
    private String active;


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return vehicleName;
    }
}
