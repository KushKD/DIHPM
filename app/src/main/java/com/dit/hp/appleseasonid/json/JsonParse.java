package com.dit.hp.appleseasonid.json;

import com.dit.hp.appleseasonid.Modal.ScanDataPojo;
import com.dit.hp.appleseasonid.Modal.SuccessResponse;
import com.dit.hp.appleseasonid.Modal.VerifyObject;
import com.dit.hp.appleseasonid.utilities.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public class JsonParse {

    public static ScanDataPojo getObjectSave(String qrcodeData) throws JSONException {

        JSONObject responseObject = new JSONObject(qrcodeData);
        ScanDataPojo data = new ScanDataPojo();
        data.setPassNo(responseObject.getString("pass"));
        data.setMobileNumbr(responseObject.getString("mobile"));
        data.setPrsonNo(responseObject.getString("person"));
        data.setDateIssueDate(responseObject.getString("date"));
        data.setScanDate(CommonUtils.getCurrentDate());

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

    public static VerifyObject createVerifyMessage(String data) throws JSONException {

        JSONObject responseObject = new JSONObject(data);
        VerifyObject sr = new VerifyObject();
        sr.setId(responseObject.getString("id"));
        sr.setPass_id(responseObject.getString("pass_id"));

        return sr;
    }

    //createJson
    public static String createJson(VerifyObject data) throws JSONException {

        JSONObject object = new JSONObject();
        object.put("id",data.getId());
        object.put("pass_id",data.getPass_id());
        object.put("remarks",data.getRemarks());



        return object.toString();
    }

}
