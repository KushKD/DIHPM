package com.dit.hp.appleseasonid.interfaces;

import com.dit.hp.appleseasonid.Modal.IDCardPojo;
import com.dit.hp.appleseasonid.enums.TaskType;

import org.json.JSONException;

public interface AsyncTaskListenerFile {

    void onTaskCompleted(IDCardPojo object, TaskType taskType) throws JSONException;
}
