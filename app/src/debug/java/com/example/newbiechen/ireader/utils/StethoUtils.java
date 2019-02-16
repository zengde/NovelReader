package com.example.newbiechen.ireader.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.Interceptor;

public class StethoUtils {
    public static void install(Application application){
        Stetho.initializeWithDefaults(application);
    }

    public static Interceptor addInterceptor(){
        return new StethoInterceptor();
    }
}
