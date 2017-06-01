package cn.zucc.qifeng.toheartbyexpress.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;

import java.io.IOException;

import cn.zucc.qifeng.toheartbyexpress.Bean.FeedBack;
import cn.zucc.qifeng.toheartbyexpress.Bean.SeekTask;
import cn.zucc.qifeng.toheartbyexpress.util.Constant;
import cn.zucc.qifeng.toheartbyexpress.util.HttpUtil;
import cn.zucc.qifeng.toheartbyexpress.util.isBackgroud;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 80421 on 2017/5/31.
 */

public class sendpostion extends Service {
    private String result;
    private static final String TAG = "sendpostonservice";
    private Context mContext = this;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private SeekTask seekTask = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public void onCreate() {
        Log.d(TAG, "sendpostonservice onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, final int startId) {
        Log.d(TAG, "sendpostonservicee onStartCommand1");

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Intent intent=new Intent("cn.seekpostionbroadcast");
                while (isBackgroud.isAppInForeground(mContext)){
                    if (seekTask != null) {
                        seekTask.setUser_account(intent.getStringExtra("user_account"));
                        Log.d(TAG,"seekTask user_account:"+seekTask.getUser_account());
                        String message=new Gson().toJson(seekTask);
                        Log.d(TAG, "json格式的登录信息" + message);
                        HttpUtil.post(Constant.URL_SearchTask, message, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                //队出现异常的操作
                                Log.w(TAG, "onFailure: " + e.getMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                //对服务器返回的具体进行操作
                                //这是在新开的一个线程里操作的
                                String responseData = response.body().string();
                                Log.d(TAG, responseData);
                                FeedBack loginfeedback = new Gson().fromJson(responseData, FeedBack.class);
//                                if("有附近的"){
//                                    intent.putExtra();
//                                    sendBroadcast(intent);
//                                }

                            }
                        });
                    }
                }
            }
        }).start();


        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "sendpostonservicee onDestroy");

    }

    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    private void resetOption() {
        // 设置是否需要显示地址信息

        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);
        // 设置是否开启缓存
        locationOption.setLocationCacheEnable(true);
        // 设置是否单次定位
        locationOption.setOnceLocation(true);
        //设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
        locationOption.setOnceLocationLatest(true);
        //设置是否使用传感器
        locationOption.setSensorEnable(true);
        //设置是否开启wifi扫描，如果设置为false时同时会停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        String strInterval = "2000";
        if (!TextUtils.isEmpty(strInterval)) {
            try {
                // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
                locationOption.setInterval(Long.valueOf(strInterval));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        String strTimeout = "3000";
        if (!TextUtils.isEmpty(strTimeout)) {
            try {
                // 设置网络请求超时时间
                locationOption.setHttpTimeOut(Long.valueOf(strTimeout));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer[] sb = new StringBuffer[5];
                for (int i = 0; i < 5; i++) {
                    sb[i] = new StringBuffer();
                }
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb[0].append(location.getLongitude());
                    sb[1].append(location.getLatitude());
                    sb[2].append(location.getAddress());
                    sb[3].append(location.getCity());

                    seekTask = new SeekTask(sb[0].toString(), sb[1].toString(), sb[2].toString(), sb[3].toString());
//                    sb.append("经度:" + location.getLongitude() + "\n");
//                    sb.append("纬度:" + location.getLatitude() + "\n");
//                    sb.append("地址:" + location.getAddress() + "\n");
//                    sb.append("城市"+location.getCity() );//城市

                } else {
                    //定位失败
                    sb[4].append("定位失败" + "\n");
                    sb[4].append("错误码:" + location.getErrorCode() + "\n");
                    sb[4].append("错误信息:" + location.getErrorInfo() + "\n");
                    sb[4].append("错误描述:" + location.getLocationDetail() + "\n");
                    result = sb[4].toString();
                }
                //解析定位结果，

            } else {
            }
        }
    };

    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

}
