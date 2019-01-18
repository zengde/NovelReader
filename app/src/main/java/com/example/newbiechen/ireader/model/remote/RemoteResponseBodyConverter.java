package com.example.newbiechen.ireader.model.remote;

import com.example.newbiechen.ireader.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;

import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class RemoteResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    RemoteResponseBodyConverter(Gson gson, TypeAdapter<T> adapter){
        this.gson=gson;
        this.adapter=adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response=value.string();
        try{
            JSONObject json=new JSONObject(response);
            if(json.has("ok")&&!json.getBoolean("ok")){
                throw new ApiException(json.getString("msg"));
            }
            return adapter.read(gson.newJsonReader(new StringReader(response)));
        }catch (Exception e){
            throw new ApiException("返回数据错误！");
        }finally{
            value.close();
        }
    }
}
