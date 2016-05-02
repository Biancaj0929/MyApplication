package umuc.com.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

/*File: NotificationPublisher.java
* Author: Team Bucket List
* Date: 1 May 2016
* Purpose:  Generates and sends notification message to user device. Message is displayed
*           in notification area and notification bar.
*/

public class NotificationService extends Service
{

    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),Personal.class);

        NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat.Builder(this.getApplicationContext())
                .setContentTitle("My Bucket List")
                .setContentText("Check and update your goals.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[] {1000, 1000})
                .setSound(sound);


        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingNotificationIntent);
        Notification notification1 = notification.build();
        notification1.flags |= Notification.FLAG_AUTO_CANCEL;

        mManager.notify(0, notification1);
    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
