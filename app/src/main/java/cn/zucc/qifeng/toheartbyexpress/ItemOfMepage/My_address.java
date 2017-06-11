package cn.zucc.qifeng.toheartbyexpress.ItemOfMepage;

import cn.zucc.qifeng.toheartbyexpress.service.postionservice;
import cn.zucc.qifeng.toheartbyexpress.service.testservice;
import cn.zucc.qifeng.toheartbyexpress.util.*;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.amap.api.location.AMapLocationListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.annotation.Target;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cn.zucc.qifeng.toheartbyexpress.MyApplication;
import cn.zucc.qifeng.toheartbyexpress.R;
import cn.zucc.qifeng.toheartbyexpress.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.internal.Util;

public class My_address extends AppCompatActivity {
    private static final String TAG = "My_address";
    public static final String ACTION_UPDATEUI = "cn.zucc.qifeng.toheartbyexpress.MylocBroadcastReceiver";
    private TextView textView;
    private MyReceiver myReceiver;
    private IntentFilter intentFilter;
    public static void Start(Context context) {
        Intent intent = new Intent(context, My_address.class);
        context.startActivity(intent);

    }
//    private ServiceConnection connection = new ServiceConnection() {
//        private static final String Targe = "serviceconnection";
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//           myBinder= (testservice.MyBinder) service;
//            Log.d(Targe,myBinder.getloc());
//            textView.setText(myBinder.getloc());
//            Log.d(Targe, "成功连接");
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            Log.d(Targe, "连接断开");
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        textView = (TextView) findViewById(R.id.textloc);

        intentFilter=new IntentFilter();
        intentFilter.addAction("cn.zucc.qifeng.toheartbyexpress.MylocBroadcastReceiver");
        myReceiver=new MyReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                String loc=intent.getStringExtra("loc");
                textView.setText(loc);
            }
        };
        registerReceiver(myReceiver,intentFilter);

//        boolean flag = isBackgroud.isServiceWork(getApplicationContext(), Constant.testserviceaddress);
//        Intent intent = new Intent(this,postionservice.class);
//        if (!flag) {
//            //如果服务已经启动就不再启动了
//            startService(intent);
//        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    public   static class MyReceiver extends BroadcastReceiver {
        public MyReceiver(){

        }
        @Override
        public void onReceive(Context context, Intent intent) {
            String loc=intent.getStringExtra("loc");
            Log.d(TAG,loc);


        }
    }

}
