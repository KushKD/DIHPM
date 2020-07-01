package com.dit.hp.appleseasonid.Modal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Kush.Dhawan
 * @project IdCardHPAppleSeason
 * @Time 01, 07 , 2020
 */
public class IdCardScanPojo implements Serializable {

    private String vehicle_number;
    private Long mobile_number;
    private String id_card_number ;

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public Long getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(Long mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    @Override
    public String toString() {
        return "IdCardScanPojo{" +
                "vehicle_number='" + vehicle_number + '\'' +
                ", mobile_number=" + mobile_number +
                ", id_card_number='" + id_card_number + '\'' +
                '}';
    }

    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("vehicle_number", getVehicle_number());
            jsonObject.put("mobile_number", getMobile_number());
            jsonObject.put("id_card_number", getId_card_number());



            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
