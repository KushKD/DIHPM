package com.dit.hp.appleseasonid.Modal;

import com.dit.hp.appleseasonid.enums.TaskType;

import java.io.Serializable;

public class VahanObject implements Serializable {

    private String url;
    private String function_name;
    private String successFail;
    private String response;
    private String parameters_to_send;
    private TaskType taskType;

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

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getSuccessFail() {
        return successFail;
    }

    public void setSuccessFail(String successFail) {
        this.successFail = successFail;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getParameters_to_send() {
        return parameters_to_send;
    }

    public void setParameters_to_send(String parameters_to_send) {
        this.parameters_to_send = parameters_to_send;
    }

    @Override
    public String toString() {
        return "VahanObject{" +
                "url='" + url + '\'' +
                ", function_name='" + function_name + '\'' +
                ", successFail='" + successFail + '\'' +
                ", response='" + response + '\'' +
                ", parameters_to_send='" + parameters_to_send + '\'' +
                ", taskType=" + taskType +
                '}';
    }
}
