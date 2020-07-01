package com.dit.hp.appleseasonid.json;

import android.util.Log;

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








}
