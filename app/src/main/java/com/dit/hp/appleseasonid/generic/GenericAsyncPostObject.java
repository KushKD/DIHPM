package com.dit.hp.appleseasonid.generic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.dit.hp.appleseasonid.Modal.ResponsePojo;
import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.UploadObject;

import com.dit.hp.appleseasonid.enums.TaskType;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerObject;
import com.dit.hp.appleseasonid.network.HttpManager;

import org.json.JSONException;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public class GenericAsyncPostObject extends AsyncTask<UploadObject,Void , ResponsePojoGet> {

    ProgressDialog dialog;
    Context context;
    AsyncTaskListenerObject taskListener_;
    TaskType taskType;
    private ProgressDialog mProgressDialog;

    public GenericAsyncPostObject(Context context, AsyncTaskListenerObject taskListener, TaskType taskType){
        this.context = context;
        this.taskListener_ = taskListener;
        this.taskType = taskType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Loading", "Connecting to Server .. Please Wait", true);
        dialog.setCancelable(false);
    }

    @Override
    protected ResponsePojoGet doInBackground(UploadObject... uploadObjects) {
        UploadObject data = null;
        data = uploadObjects[0];
        HttpManager http_manager = null;
        ResponsePojoGet Data_From_Server = null;
        boolean save = false;

        try{
            http_manager = new HttpManager();

            if(TaskType.SCAN_ID_CARD.toString().equalsIgnoreCase(data.getTasktype().toString())){
                Data_From_Server = http_manager.PostDataScanQRCode(data);
                Log.e("Data hhghsds",Data_From_Server.toString());
                return Data_From_Server;
            }else if(TaskType.VERIFY_DETAILS.toString().equalsIgnoreCase(data.getTasktype().toString())){
                Data_From_Server = http_manager.PostDataScanQRCode(data);
                Log.e("Data hhghsds",Data_From_Server.toString());
                return Data_From_Server;
            }




        }catch(Exception e){
            return Data_From_Server;
        }


        return Data_From_Server;
    }

    @Override
    protected void onPostExecute(ResponsePojoGet result) {
        super.onPostExecute(result);

        try {
            taskListener_.onTaskCompleted(result, taskType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
    }
}
