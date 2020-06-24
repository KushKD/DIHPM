package com.dit.hp.appleseasonid.network;


import android.util.Log;

import com.dit.hp.appleseasonid.utilities.Econstants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class HttpFileUpload {
    URL connectURL;
    String responseString;

    String filename_, filepath_, jsonData_;
    byte[ ] dataToServer;
    FileInputStream fileInputStream = null;
    DataOutputStream dos = null;
    public String ResponceCode = null;
    BufferedReader reader =null;

    public HttpFileUpload(String urlString, String filename, String filepath, String jsonData){
        try{

            connectURL = new URL(urlString);
            filename_= filename;
            filepath_ = filepath;
            jsonData_ = jsonData;
            System.out.println("connected");
        }catch(Exception ex){
            Log.i("HttpFileUpload","URL Malformatted");
        }
    }

    public String Send_Now(FileInputStream fStream){
        fileInputStream = fStream;
       return Sending();
    }

   private String Sending(){
        String iFileName = filename_;
        Log.e("iFileName",iFileName);
        Log.e("filepath",filepath_);
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String Tag="fSnd";
        try
        {
            Log.e(Tag,"Starting Http File Sending to URL");

            // Open a HTTP connection to the URL
            HttpURLConnection conn = (HttpURLConnection)connectURL.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
            try{
                dos = new DataOutputStream(conn.getOutputStream());
            }catch(Exception e){
                System.out.println(e.getCause());
            }
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"jsondata\""+ lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(jsonData_);
            dos.writeBytes(lineEnd);

            Log.e("JsonData",jsonData_.toString());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            //UploadFile
            dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + iFileName +"\"" + lineEnd);
            dos.writeBytes(lineEnd);
            //Log.e("We are here",dos.);

            // create a buffer of maximum size
            int bytesAvailable = fileInputStream.available();
            Log.e("Bytes Avaul", Integer.toString(bytesAvailable));
            Log.e("File Path",fileInputStream.toString());
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[ ] buffer = new byte[bufferSize];

            // read file and write it into form...
            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0)
            {
                //  Log.e("bytesRead",Integer.toString(bytesRead));
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0,bufferSize);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // close streams
            fileInputStream.close();

            dos.flush();

  System.out.println("@#@#@#@#@#@#@#@#@#"+conn.getResponseCode());

            if (conn.getResponseCode() != 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                conn.disconnect();
              Log.e("Error",sb.toString());
                return sb.toString()+"~~"+conn.getResponseCode();
            }else {


                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                conn.disconnect();
                Log.e("Success",sb.toString());
                return sb.toString()+"~~"+conn.getResponseCode();

            }




        }
        catch (MalformedURLException ex)
        {
            return  ex.getLocalizedMessage().toString();
        }

        catch (IOException ioe)
        {
            return  ioe.getLocalizedMessage().toString();
        }
    }


}
