package com.williambastos.todolistv3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Task implements Serializable {

    @SerializedName("userId")
    private Integer userId;
    @SerializedName("id")
    private Integer idTask;
    @SerializedName("title")
    private String taskName;
    @SerializedName("completed")
    private Boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean idDone) {
        this.isDone = idDone;
    }
}
