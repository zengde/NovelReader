package com.example.newbiechen.ireader.utils;

import android.os.Environment;
import android.util.Log;

import org.acra.ACRA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugUtils {

    public static void trackBreadcrumb(String tag,String event) {

        Log.d("AcraCustom",tag+"."+event);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String time= sdf.format(new Date());

        writeFileData(time+"--"+tag+"."+event+"\r\n");
        ACRA.getErrorReporter().putCustomData(time, tag+"."+event);
    }

    private static void writeFileData(String content){
        try {
            File file=new File(Environment.getExternalStorageDirectory(),"log.txt");

            BufferedWriter buf = new BufferedWriter(new FileWriter(file,true));
            buf.write(content);
            buf.flush();
            buf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
