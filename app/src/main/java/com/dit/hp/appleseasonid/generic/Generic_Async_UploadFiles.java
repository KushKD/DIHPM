package com.dit.hp.appleseasonid.generic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dit.hp.appleseasonid.Modal.IDCardPojo;
import com.dit.hp.appleseasonid.enums.TaskType;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerFile;
import com.dit.hp.appleseasonid.network.HttpFileUpload;
import com.dit.hp.appleseasonid.utilities.Econstants;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Generic_Async_UploadFiles extends AsyncTask<IDCardPojo, String, IDCardPojo> {


    String outputStr;
    ProgressDialog dialog;
    Context context;
    AsyncTaskListenerFile taskListener;
    TaskType taskType;
    private ProgressDialog mProgressDialog;


    public Generic_Async_UploadFiles(Context context, AsyncTaskListenerFile taskListener, TaskType taskType){
        this.context = context;
        this.taskListener = taskListener;
        this.taskType = taskType;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Uploading");
        mProgressDialog.setMessage("Uploading Files and Images, Please Wait!");
       // mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();
    }

    @Override
    protected IDCardPojo doInBackground(IDCardPojo... mediaFiles) {
        HttpFileUpload hfu = null;
        String responseMessage =null;
        IDCardPojo myList = null;

        try {



            FileInputStream fstrm = null;
            try {

                 myList = mediaFiles[0];

                File file = new File(myList.getFilePath());
                    Log.e("File Name", file.getName());
                Log.e("File Path", file.getPath());

                    fstrm = new FileInputStream(file.getPath());
                    hfu = new HttpFileUpload(Econstants.url+myList.getFunctionName(),  file.getName(),
                            file.getPath(),
                            myList.getVahicleEntries().toJSON());
                    System.out.println("Connection ...Please wait");
                responseMessage = hfu.Send_Now(fstrm);
                myList.setReponse(responseMessage.split("~~")[0]);
                myList.setResponseCode(Integer.parseInt(responseMessage.split("~~")[1]));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        } catch (Exception e) {
            System.out.println("Exception is"+e.getMessage());
        }

        return myList;
    }

//    @Override
//    protected void onProgressUpdate() {
//
//        super.onProgressUpdate(values);
//        Log.e("Progress",Integer.toString(values[0]));
//        mProgressDialog.setProgress(values[0]);
//
//    }

    @Override
    protected void onPostExecute(IDCardPojo object) {
        super.onPostExecute(object);

        try {
            taskListener.onTaskCompleted(object, taskType);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mProgressDialog.dismiss();
    }



}
