package com.example.newbiechen.ireader;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.newbiechen.ireader.service.DownloadService;
import com.example.newbiechen.ireader.utils.LocalArcaSender;
import com.example.newbiechen.ireader.utils.StethoUtils;
import com.squareup.leakcanary.LeakCanary;

import org.acra.*;
import org.acra.annotation.*;

/**
 * Created by newbiechen on 17-4-15.
 */
@AcraCore(reportSenderFactoryClasses = LocalArcaSender.class,
        logcatArguments = { "-t", "200","-v","threadtime", "AcraCustom:D", "*:S" })
public class App extends Application {
    private static Context sInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        startService(new Intent(getContext(), DownloadService.class));

        // 初始化内存分析工具
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }

        StethoUtils.install(this);

    }

    public static Context getContext(){
        return sInstance;
    }
}