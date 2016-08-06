package com.example.tom.tryveg.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tom on 01-Aug-16.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Service stops", "ohhhhh");
        context.startService(new Intent(context, NotificationService.class));
    }
}
