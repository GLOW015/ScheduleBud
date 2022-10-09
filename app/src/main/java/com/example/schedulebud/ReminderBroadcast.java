package com.example.schedulebud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.schedulebud.R;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtils _notificationUtils = new NotificationUtils(context);
        NotificationCompat.Builder _builder = _notificationUtils.setNotification(intent.getStringExtra("title"), intent.getStringExtra("body"));
        _notificationUtils.getManager().notify(intent.getIntExtra("notificationID", 1), _builder.build());
    }
}
