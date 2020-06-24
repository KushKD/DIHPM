package com.dit.hp.appleseasonid.Modal;

import com.dit.hp.appleseasonid.enums.TaskType;

import java.io.Serializable;

public class IDCardPojo implements Serializable {

    private VehicleOwnerEntries vahicleEntries;
    private String filePath;
    private String functionName;
    private TaskType taskType;
    private String url;
    private String reponse;
    private int responseCode;

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public VehicleOwnerEntries getVahicleEntries() {
        return vahicleEntries;
    }

    public void setVahicleEntries(VehicleOwnerEntries vahicleEntries) {
        this.vahicleEntries = vahicleEntries;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "IDCardPojo{" +
                "vahicleEntries=" + vahicleEntries +
                ", filePath='" + filePath + '\'' +
                ", functionName='" + functionName + '\'' +
                ", taskType=" + taskType +
                ", url='" + url + '\'' +
                ", reponse='" + reponse + '\'' +
                ", responseCode=" + responseCode +
                '}';
    }
}
