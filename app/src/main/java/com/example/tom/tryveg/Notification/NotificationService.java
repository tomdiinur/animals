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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.example.tom.tryveg.Globals;
import com.example.tom.tryveg.MainActivity;
import com.example.tom.tryveg.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NotificationService extends Service {

    private Timer mTimer;
    private Context context;
    private TimerTask timerTast = new TimerTask() {
        @Override
        public void run() {
            Log.e("Log", "Running");
            notifiy();
        }
    };

    public NotificationService() {
    }

    public NotificationService(Context context) {
        this.context = context;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer();
        mTimer.schedule(timerTast, 60 * 60 * 24 * 5 * 1000, 60 * 60 * 24 * 5 * 1000);
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

        Intent myIntent;
        if (context != null) {
            myIntent = new Intent(context, MainActivity.class);
        } else {
            myIntent = new Intent(getBaseContext(), MainActivity.class);
        }
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
        Notification notification = builder.build();

        if (Globals.currentUser.getPet().hasRington()) {
            notification.sound = Uri.parse("android.resource://" + getPackageName() + "/" + Globals.currentUser.getPet().getRington());
        } else {
            notification.defaults = Notification.DEFAULT_SOUND;
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void sendNotification(String title, String content, int smallIcon, Uri sound, Bitmap thumbnailUrl) {

        if (smallIcon == -1) {
            smallIcon = R.drawable.ic_app_logo;
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RSSPullService");

        Intent myIntent;
        PendingIntent pendingIntent;
        Context currContext;

        if (context != null) {
            myIntent = new Intent(context, MainActivity.class);
            pendingIntent = PendingIntent.getActivity(context, 0, myIntent, Intent.FILL_IN_ACTION);
            currContext = context;

        } else {
            myIntent = new Intent(getBaseContext(), MainActivity.class);
            pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myIntent, Intent.FILL_IN_ACTION);
            currContext = getApplicationContext();
        }

        Notification.Builder builder;

        Bitmap animalBitmap = BitmapFactory.decodeResource(currContext.getResources(), Globals.currentUser.getPetDrawable());

        builder = new Notification.Builder(currContext)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setLargeIcon(thumbnailUrl)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(smallIcon);
        Notification notification = builder.build();


        if (sound != null) {
            notification.sound = sound;
        } else {
            notification.defaults = Notification.DEFAULT_SOUND;
        }

        NotificationManager notificationManager = (NotificationManager) currContext.getSystemService(Context.NOTIFICATION_SERVICE);
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
        Notification notification = builder.build();

        if (Globals.currentUser.getPet().hasRington()) {
            notification.sound = Uri.parse("android.resource://" + getPackageName() + "/" + Globals.currentUser.getPet().getRington());
        } else {
            notification.defaults = Notification.DEFAULT_SOUND;
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    public void sendNotification(String title, String content) {
        sendNotification(title, content, R.drawable.ic_app_logo, null, null);
    }

    public void sendNotification(String title, String content, final String imageURL) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Bitmap> result = executor.submit(new Callable<Bitmap>() {
            public Bitmap call() throws Exception {

                URL url = new URL(imageURL);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                return image;

            }
        });

        try {
            sendNotification(title, content, -1, null, getCircleBitmap(result.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
            sendNotification(title, content, -1, null, null);

        } catch (ExecutionException e) {
            e.printStackTrace();
            sendNotification(title, content, -1, null, null);

        }

    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

}