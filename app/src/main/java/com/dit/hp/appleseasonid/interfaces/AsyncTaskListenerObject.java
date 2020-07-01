package com.dit.hp.appleseasonid.interfaces;

import com.dit.hp.appleseasonid.Modal.ResponsePojo;
import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.enums.TaskType;

import org.json.JSONException;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public interface AsyncTaskListenerObject {
    void onTaskCompleted(ResponsePojoGet result, TaskType taskType) throws JSONException;
}
