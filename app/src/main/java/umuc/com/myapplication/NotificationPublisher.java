package umuc.com.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import java.security.Provider;

/*File: NotificationPublisher.java
* Author: Team Bucket List
* Date: 1 May 2016
* Purpose:  Extends BroadcastReceiver class to receive the alarm notification from
*           AlarmManager. Activates NotificationService to send notification to device.
*/

public class NotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, NotificationService.class);
        context.startService(service);
    }
}
