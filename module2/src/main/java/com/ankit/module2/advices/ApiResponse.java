package com.ankit.module2.advices;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
public class ApiResponse <T>{
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;
    private T data;
    private  ApiError error;

    public ApiResponse(){
        this.timeStamp = LocalDateTime.now();
    }
    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this(); // calling the default constructor for dateAndTime
        this.error = error;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
