package com.example.newbiechen.ireader.model.remote;

import com.example.newbiechen.ireader.utils.ToastUtils;

public class ApiException extends RuntimeException{
    public ApiException(RemotResponse result) {
        super(result.getMsg());
    }

    @Override
    public String toString() {
        return getLocalizedMessage();
    }
}
