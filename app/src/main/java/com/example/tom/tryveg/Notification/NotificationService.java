package com.example.tom.tryveg.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.example.tom.tryveg.Globals;
import com.example.tom.tryveg.MainActivity;
import com.example.tom.tryveg.R;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {

    private Timer mTimer;
    private TimerTask timerTast = new TimerTask() {
        @Override
        public void run() {
            Log.e("Log", "Running");
            notifiy();
        }
    };

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer();
        mTimer.schedule(timerTast, 60 * 60 * 24 * 5*  1000, 60 * 60 * 24 * 5*  1000);
    }

    public void onDestroy() {
        mTimer.cancel();
        timerTast.cancel();

        Intent intent = new Intent("com.example.tom.tryveg");
        intent.putExtra("yourValue", "torestore");
        sendBroadcast(intent);
    }

    private void notifiy() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RSSPullService");

        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myIntent, Intent.FILL_IN_ACTION);
        Context context = getApplicationContext();

        Notification.Builder builder;

        Bitmap animalBitmap = BitmapFactory.decodeResource(getResources(), Globals.currentUser.getPetDrawable());

        builder = new Notification.Builder(context)
                .setContentTitle("New animal!")
                .setContentText("You saved a " + Globals.currentUser.getPet().getName())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setLargeIcon(animalBitmap)
                .setSmallIcon(R.drawable.leaf);
        Notification notification =  builder.build();

        if (Globals.currentUser.getPet().hasRington()) {
            notification.sound = Uri.parse("android.resource://" + getPackageName() + "/" + Globals.currentUser.getPet().getRington());
        }
        else {
            notification.defaults = Notification.DEFAULT_SOUND;
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void sendNotification(String title, String content, int smallIcon, Uri sound) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RSSPullService");

        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myIntent, Intent.FILL_IN_ACTION);
        Context context = getApplicationContext();

        Notification.Builder builder;

        Bitmap animalBitmap = BitmapFactory.decodeResource(getResources(), Globals.currentUser.getPetDrawable());

        builder = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(smallIcon);
        Notification notification =  builder.build();

        if (sound != null) {
            notification.sound = sound;
        }
        else {
            notification.defaults = Notification.DEFAULT_SOUND;
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    private void sendAnimalUpgradedNotification() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RSSPullService");

        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myIntent, Intent.FILL_IN_ACTION);
        Context context = getApplicationContext();

        Notification.Builder builder;

        builder = new Notification.Builder(context)
                .setContentTitle("New animal!")
                .setContentText("You saved a " + Globals.currentUser.getPet().getName())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(Globals.currentUser.getPetDrawable());
        Notification notification =  builder.build();

        if (Globals.currentUser.getPet().hasRington()) {
            notification.sound = Uri.parse("android.resource://" + getPackageName() + "/" + Globals.currentUser.getPet().getRington());
        }
        else {
            notification.defaults = Notification.DEFAULT_SOUND;
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void sendNotification(String title, String content) {
        sendNotification(title, content, R.drawable.ic_app_logo, null);
    }
}
