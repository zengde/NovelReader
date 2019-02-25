package com.example.newbiechen.ireader.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;
import org.acra.sender.ReportSenderFactory;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalArcaSender implements ReportSenderFactory {
    @NonNull
    @Override
    public ReportSender create(@NonNull Context context, @NonNull CoreConfiguration config) {
        return new LocalSender(context);
    }

    @Override
    public boolean enabled(@NonNull CoreConfiguration config) {
        return true;
    }
}

class LocalSender implements ReportSender {

    private static final String TAG = "Arca LocalSender";
    private FileWriter crashReport = null;

    LocalSender(Context ctx) {
        // the destination
        File dir=FileUtils.isSdCardExist()? ctx.getExternalFilesDir(null):ctx.getFilesDir();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename= "acra-log-"+sdf.format(new Date())+".txt";

        File logFile = new File(dir, filename);

        try {
            crashReport = new FileWriter(logFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(@NonNull Context context,@NonNull CrashReportData report) throws ReportSenderException {
        // Iterate over the CrashReportData instance and do whatever
        // you need with each pair of ReportField key / String value

        try {
            String finalReport = report.toJSON();

            BufferedWriter buf = new BufferedWriter(crashReport);

            buf.write(finalReport);
            buf.flush();
            buf.close();
        } catch (JSONException e){
            Log.e(TAG, "JSON ERROR", e);
        } catch (IOException e) {
            Log.e(TAG, "Report write ERROR", e);
        }
    }

}
