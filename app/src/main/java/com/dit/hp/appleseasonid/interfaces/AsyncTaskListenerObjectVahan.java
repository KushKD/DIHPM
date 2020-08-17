package com.dit.hp.appleseasonid.interfaces;

import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.VahanObject;
import com.dit.hp.appleseasonid.enums.TaskType;

import org.json.JSONException;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public interface AsyncTaskListenerObjectVahan {
    void onTaskCompleted(VahanObject result, TaskType taskType) throws JSONException, IOException, SAXException, ParserConfigurationException;
}
