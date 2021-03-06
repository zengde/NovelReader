package com.example.newbiechen.ireader.model.remote;

public class RemotResponse<T> {
    private boolean ok;
    private String code;
    private String msg;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg==null||msg.isEmpty()? "未知错误":msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ok:"+isOk()+" code:"+getCode()+" msg:"+getMsg();
    }
}
