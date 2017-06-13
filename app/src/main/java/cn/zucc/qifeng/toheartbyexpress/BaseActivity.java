package cn.zucc.qifeng.toheartbyexpress;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

/**
 * Created by 80421 on 2017/5/31.
 * 用来发通知栏
 */




public class BaseActivity extends AppCompatActivity {
    NotificationManager manager=null;
    Notification notification=null;
    private notificationReceive  myReceiver;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNotfication("嘻嘻","一段奇遇在等着你");
        intentFilter=new IntentFilter();
        intentFilter.addAction("cn.seekpostionbroadcast");
        myReceiver=new notificationReceive(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                manager.notify(1,notification);
            }
        };
        registerReceiver(myReceiver,intentFilter);
        //用来添加通知栏
//
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    private void initNotfication(String Title, String Text){
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification=new NotificationCompat.Builder(this)
                .setContentTitle(Title)
                .setContentText(Text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_ac_unit)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_ac_unit))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
    }
    public static class notificationReceive extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
