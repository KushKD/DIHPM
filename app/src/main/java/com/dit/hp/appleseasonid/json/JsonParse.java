package com.dit.hp.appleseasonid.json;

import android.util.Log;

import com.dit.hp.appleseasonid.Modal.IDCardOwnerServerVerify;
import com.dit.hp.appleseasonid.Modal.IDCardServerObject;
import com.dit.hp.appleseasonid.Modal.IdCardScanPojo;
import com.dit.hp.appleseasonid.Modal.SuccessResponse;
import com.dit.hp.appleseasonid.Modal.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public class JsonParse {

    public static IdCardScanPojo getObjectSave(String qrcodeData) throws JSONException {
        JSONObject responseObject = new JSONObject(qrcodeData);
        IdCardScanPojo data = new IdCardScanPojo();
        data.setVehicle_number(responseObject.getString("vehicle_number"));
        data.setId_card_number(responseObject.getString("id_card_number"));
        data.setMobile_number(responseObject.getLong("mobile_number"));


        return data;
    }

    public static SuccessResponse getSuccessResponse(String data) throws JSONException {

        JSONObject responseObject = new JSONObject(data);
        SuccessResponse sr = new SuccessResponse();
        sr.setStatus(responseObject.getString("STATUS"));
        sr.setMessage(responseObject.getString("MSG"));
        sr.setResponse(responseObject.getString("RESPONSE"));

        return sr;
    }

    public static User getUSerDetilas(String data) throws JSONException {

        JSONObject responseObject = new JSONObject(data);
        User sr = new User();
        sr.setUserName(responseObject.getString("user_name"));
        sr.setUserId(responseObject.getInt("user_id"));
        sr.setMobileNumber(responseObject.getLong("mobile_number"));
        Log.e("Data",sr.toString());
        return sr;
    }



    public static IDCardServerObject getIdCardUserServerDetails(String data) throws JSONException {

        JSONObject responseObject = new JSONObject(data);
        IDCardServerObject sr = new IDCardServerObject();
        sr.setImageUrl(responseObject.getString("fileDownloadUri"));
        sr.setGenerateIDCardUrl_(responseObject.getString("generateIDCardUrl_"));

        JSONObject responseObject2 = responseObject.getJSONObject("ownerData");

        sr.setDriverName(responseObject2.getString("vehicleOwnerName"));
        sr.setIdCardNumber(responseObject2.getString("idCardNumber"));
        sr.setPhoneNumber(responseObject2.getString("vehicleOwnerMobileNumber"));

        return sr;
    }


    public static IDCardOwnerServerVerify getIdCardUserServerDetailsComplete(String data) throws JSONException {

        JSONObject responseObject = new JSONObject(data);
        IDCardOwnerServerVerify sr = new IDCardOwnerServerVerify();
        sr.setImageurl(responseObject.optString("fileDownloadUri"));
        sr.setIdcardUrl(responseObject.optString("generateIDCardUrl_"));

        JSONObject responseObject2 = responseObject.getJSONObject("ownerData");

        sr.setVehicleOwnerName(responseObject2.optString("vehicleOwnerName"));
        sr.setIdCardNumber(responseObject2.optString("idCardNumber"));
       // sr.setVehicleOwnerMobileNumber(Long.valueOf(responseObject2.getString("vehicleOwnerMobileNumber")));
        sr.setIsValidFrom(responseObject2.optString("isValidFrom"));
        sr.setIsValidUpto(responseObject2.optString("isValidUpto"));
        sr.setVehicleOwnerAadhaarNumber(responseObject2.optString("vehicleOwnerAadhaarNumber"));
        sr.setVehicleOwnerChassisNumber(responseObject2.optString("vehicleOwnerChassisNumber"));
        sr.setVehicleOwnerDrivingLicence(responseObject2.optString("vehicleOwnerDrivingLicence"));
        sr.setVehicleOwnerEngineNumber(responseObject2.optString("vehicleOwnerEngineNumber"));
        sr.setVehicleOwnerVehicleNumber(responseObject2.optString("vehicleOwnerVehicleNumber"));
        sr.setDataEnteredBy(responseObject2.optInt("dataEnteredBy"));
        sr.setVehicleBarrierId(responseObject2.optInt("vehicleBarrierId"));
        sr.setVehicleDistrictId(responseObject2.optInt("vehicleDistrictId"));
        sr.setVehicleOwnerId(responseObject2.optInt("vehicleOwnerId"));

        return sr;
    }








}
