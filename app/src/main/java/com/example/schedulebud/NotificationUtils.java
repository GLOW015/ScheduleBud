package com.example.schedulebud;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationUtils extends ContextWrapper {

    private NotificationManager _notificationManager;
    private Context _context;
    private String CHANNEL_ID = "ToDoNotifications";
    private CharSequence TIMELINE_CHANNEL_NAME = "ToDoNotificationChannel";

    public NotificationUtils(Context base) {
        super(base);
        _context = base;
        createChannel();
    }

    public NotificationCompat.Builder setNotification(String title, String body) {
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, TIMELINE_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if(_notificationManager == null)
        {
            _notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return _notificationManager;
    }

    public void setReminder(long timeInMillis, String title, String body, int notificationID) {
        Intent _intent = new Intent(_context, ReminderBroadcast.class);
        _intent.putExtra("title", title);
        _intent.putExtra("body", body);
        _intent.putExtra("notificationID", notificationID);
        PendingIntent _pendingIntent = PendingIntent.getBroadcast(_context, notificationID, _intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager _alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        _alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, _pendingIntent);
    }

    public void cancelReminder(long timeInMillis, String title, String body, int notificationID)
    {
        Intent _intent = new Intent(_context, ReminderBroadcast.class);
        _intent.putExtra("title", title);
        _intent.putExtra("body", body);
        _intent.putExtra("notificationID", notificationID);
        PendingIntent _pendingIntent = PendingIntent.getBroadcast(_context, notificationID, _intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager _alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        _alarmManager.cancel(_pendingIntent);
    }
}
