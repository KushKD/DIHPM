package com.dit.hp.appleseasonid.network;


import android.util.Log;

import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.UploadObject;
import com.dit.hp.appleseasonid.Modal.VahanObject;
import com.dit.hp.appleseasonid.security.CryptographyAES;
import com.dit.hp.appleseasonid.utilities.Econstants;
import com.dit.hp.appleseasonid.utilities.Preferences;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public class HttpManager {

    CryptographyAES AES = new CryptographyAES();

    public ResponsePojoGet GetData(UploadObject data) throws IOException {
        BufferedReader reader = null;
        URL url_ =  null;
        ResponsePojoGet response = null;
        HttpURLConnection con = null;

        try {
             url_ = new URL(data.getUrl()+data.getMethordName()+data.getParam());
             con = (HttpURLConnection) url_.openConnection();

            if (con.getResponseCode() != 200) {
                reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                con.disconnect();
                response = Econstants.createOfflineObject(data.getUrl(), data.getParam(),sb.toString() ,  Integer.toString(con.getResponseCode()), data.getMethordName());

                return response;
            }else {


                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                con.disconnect();
                //sb.tostring
                response = Econstants.createOfflineObject(data.getUrl(), data.getParam(),sb.toString() ,  Integer.toString(con.getResponseCode()), data.getMethordName());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = Econstants.createOfflineObject(data.getUrl(), data.getParam(),e.getLocalizedMessage() ,  Integer.toString(con.getResponseCode()), data.getMethordName());

            return response;
        } finally {
            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    response = Econstants.createOfflineObject(data.getUrl(), data.getParam(),e.getLocalizedMessage() ,  Integer.toString(con.getResponseCode()), data.getMethordName());
                    return response;
                }
            }
        }
    }

    public ResponsePojoGet PostDataScanQRCode(UploadObject data) {

        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;
        ResponsePojoGet response = null;


        try {

            URL = data.getUrl()+data.getMethordName();


            url_ = new URL(URL);
            conn_ = (HttpURLConnection) url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/json");
            conn_.connect();

            System.out.println(data.getParam());
            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(data.getParam());
            out.close();

            try {
                int HttpResult = conn_.getResponseCode();
                if (HttpResult != HttpURLConnection.HTTP_OK) {
                    Log.e("Error", conn_.getResponseMessage());
                    response = new ResponsePojoGet();
                    response = Econstants.createOfflineObject(URL, data.getParam(), conn_.getResponseMessage() , Integer.toString(conn_.getResponseCode()), data.getMethordName());
                    return response;


                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(), "utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                    Log.e("Data from Service", sb.toString());
                    response = new ResponsePojoGet();
                    response = Econstants.createOfflineObject(URL, data.getParam(), sb.toString() , Integer.toString(conn_.getResponseCode()), data.getMethordName());

                }

            } catch (Exception e) {
                data.getScanDataPojo().setUploaddToServeer(false);
                response = new ResponsePojoGet();
                response = Econstants.createOfflineObject(URL, data.getParam(), conn_.getResponseMessage(), Integer.toString(conn_.getResponseCode()), data.getMethordName());
                return response;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn_ != null)
                conn_.disconnect();
        }
        return response;
    }

    /**
     * Post Data
     */
    public VahanObject getVahanDetails(VahanObject object) {
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        VahanObject returnObject  = new VahanObject();
        try {
//{"regNo":"HP511404","Ip":"127.0.0.1","userId":"1"}
            Log.e("userId   ",  Preferences.getInstance().userid);
            Log.e("url",object.getUrl()+object.getFunction_name());
            conn_ = NetworkUtils.getInputStreamConnection(object.getUrl()+object.getFunction_name());

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("regNo", object.getParameters_to_send().trim());
            jsonObject.put("Ip","127.0.0.1");
            jsonObject.put("userId", Preferences.getInstance().userid);
            System.out.println("JSON TO SEND==== " + jsonObject.toString());
            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            try {
                int HttpResult = conn_.getResponseCode();
                if (HttpResult != HttpURLConnection.HTTP_OK) {
                    sb = NetworkUtils.getErrorStream(conn_);
                    returnObject.setResponse(sb.toString());
                    returnObject.setSuccessFail("FALIURE");
                    returnObject.setFunction_name(object.getFunction_name());
                    returnObject.setParameters_to_send(object.getParameters_to_send());
                    return returnObject;

                } else {
                    sb = NetworkUtils.getInputStream(conn_);
                      returnObject.setResponse(sb.toString());
                    returnObject.setSuccessFail("SUCCESS");
                    returnObject.setFunction_name(object.getFunction_name());
                    returnObject.setParameters_to_send(object.getParameters_to_send());
                    return returnObject;

                }
            } catch (Exception e) {
                returnObject.setResponse(e.getLocalizedMessage());
                returnObject.setSuccessFail("FALIURE");
                returnObject.setFunction_name(object.getFunction_name());
                returnObject.setParameters_to_send(object.getParameters_to_send());
                return returnObject;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (conn_ != null)
                conn_.disconnect();
        }
        return returnObject;
    }







}
