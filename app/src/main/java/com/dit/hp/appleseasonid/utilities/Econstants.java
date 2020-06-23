package com.dit.hp.appleseasonid.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Base64;
import android.util.Log;

import com.dit.hp.appleseasonid.Modal.ResponsePojo;
import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.ScanDataPojo;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 01, 05 , 2020
 */
public class Econstants {

    //Development
    public static final String url = "http://192.168.1.37:8080/test/api";
    public static final String stateID = "9";
    public static final String blank = "";
    public static final String internetNotAvailable = "Internet not Available. Please Connect to Internet and try again.";
    public static final String methordGetDistrict = "/districts/";
    public static final String methordGetBarriers = "/barriers/";
    public static final String methordGetVehicleType = "/vehicletypes/";
    public static final String methodVehicleUserType = "/vehicleusertypes/";
    public static final String methordUploadData = "/uploadData/";

    public static final String Date_Format = "dd-MM-yyyy";


    public static ResponsePojoGet createOfflineObject(String url, String requestParams, String response, String Code, String functionName) {
        ResponsePojoGet pojo = new ResponsePojoGet();
        pojo.setUrl(url);
        pojo.setRequestParams(requestParams);
        pojo.setResponse(response);
        pojo.setFunctionName(functionName);
        pojo.setResponseCode(Code);

        return pojo;
    }



    public static ResponsePojo createOfflineObject(String url, String requestParams, String response, ScanDataPojo scanDataPojo) {
        ResponsePojo pojo = new ResponsePojo();
        pojo.setUrl(url);
        pojo.setRequestParams(requestParams);
        pojo.setResponse(response);

        pojo.setPassNo(scanDataPojo.getPassNo());
        pojo.setPrsonNo(scanDataPojo.getPrsonNo());
        pojo.setMobileNumbr(scanDataPojo.getMobileNumbr());
        pojo.setLatitude(scanDataPojo.getLatitude());
        pojo.setLongitude(scanDataPojo.getLongitude());
        pojo.setDistict(scanDataPojo.getDistict());
        pojo.setBarrrier(scanDataPojo.getBarrrier());
        pojo.setDateIssueDate(scanDataPojo.getDateIssueDate());
        pojo.setScanDate(scanDataPojo.getScanDate());
        pojo.setUploaddToServeer(scanDataPojo.isUploaddToServeer());
        pojo.setScannedByPhoneNumber(scanDataPojo.getScannedByPhoneNumber());
        pojo.setManual_persons(scanDataPojo.getNumber_of_passengers_manual());



        return pojo;
    }




    public static ResponsePojo createOfflineObject(String url, String requestParams, String response) {
        ResponsePojo pojo = new ResponsePojo();
        pojo.setUrl(url);
        pojo.setRequestParams(requestParams);
        pojo.setResponse(response);





        return pojo;
    }

    public static ResponsePojo createOfflinePaam(String url, String requestParams, String response, String params) {
        ResponsePojo pojo = new ResponsePojo();
        pojo.setUrl(url);
        pojo.setRequestParams(requestParams);
        pojo.setResponse(response);
        pojo.setParams(params);





        return pojo;
    }



    public static String generateAuthenticationPasswrd(String username, String password)
            throws UnsupportedEncodingException {
        StringBuilder SB = new StringBuilder();
        SB.append(username + ":" + password);
        System.out.println(Base64.encodeToString(SB.toString().getBytes(),Base64.DEFAULT));

        return Base64.encodeToString(SB.toString().getBytes(),Base64.DEFAULT);
      //  return Base64.getEncoder().encodeToString(SB.toString().getBytes("utf-8"));
    }
}
