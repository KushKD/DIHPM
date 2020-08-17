package com.dit.hp.appleseasonid.generic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.UploadObject;
import com.dit.hp.appleseasonid.Modal.VahanObject;
import com.dit.hp.appleseasonid.enums.TaskType;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerObject;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerObjectVahan;
import com.dit.hp.appleseasonid.network.HttpManager;

import org.json.JSONException;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public class GenericAsyncPostObjectVahan extends AsyncTask<VahanObject,Void , VahanObject> {

    ProgressDialog dialog;
    Context context;
    AsyncTaskListenerObjectVahan taskListener_;
    TaskType taskType;
    private ProgressDialog mProgressDialog;

    public GenericAsyncPostObjectVahan(Context context, AsyncTaskListenerObjectVahan taskListener, TaskType taskType){
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
    protected VahanObject doInBackground(VahanObject... uploadObjects) {
        VahanObject data = null;
        data = uploadObjects[0];
        HttpManager http_manager = null;
        VahanObject Data_From_Server = null;
        boolean save = false;

        try{
            http_manager = new HttpManager();

            if(TaskType.VAHAN.toString().equalsIgnoreCase(data.getTaskType().toString())){
                Data_From_Server = http_manager.getVahanDetails(data);
                return Data_From_Server;
            }




        }catch(Exception e){
            return Data_From_Server;
        }


        return Data_From_Server;
    }

    @Override
    protected void onPostExecute(VahanObject result) {
        super.onPostExecute(result);

        try {
            taskListener_.onTaskCompleted(result, taskType);
        } catch (JSONException | IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
    }
}
