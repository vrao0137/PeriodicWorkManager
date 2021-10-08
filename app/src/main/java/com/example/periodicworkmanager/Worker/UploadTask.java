package com.example.periodicworkmanager.Worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.periodicworkmanager.R;

public class UploadTask extends Worker {
    public static Context context;

    public UploadTask(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @Override
    public Result doWork() {

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("oneTimeRequest","oneTimeRequest",
                    NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        Intent intent=new Intent();
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "oneTimeRequest")
                .setContentTitle("This Periodic WorkManager")
                .setContentText("This task done")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        manager.notify(1, builder.build());

      /*  Intent intent=new Intent();
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
        NotificationCompat.Builder notification=new NotificationCompat.Builder(context,"channelId")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("MyMessage")
                .setContentTitle("new")
                .setContentIntent(pendingIntent);
        android.app.NotificationManager manager=(android.app.NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,notification.build());*/

        return Result.success();
    }
}
