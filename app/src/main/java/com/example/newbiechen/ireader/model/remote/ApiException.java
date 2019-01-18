package com.example.newbiechen.ireader.model.remote;

public class ApiException extends RuntimeException{
    public ApiException(RemotResponse result) {
        super(result.getMsg());
    }

    public ApiException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return getLocalizedMessage();
    }
}
