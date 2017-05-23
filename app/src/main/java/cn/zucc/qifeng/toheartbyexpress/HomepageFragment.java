package cn.zucc.qifeng.toheartbyexpress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.Goods;
import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.PublishTask;
import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.RecyclerViewAdapter;

/**
 * Created by 80421 on 2017/5/17.
 */

public class HomepageFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ShareFragementp";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<Goods> goodses;
    private FloatingActionButton publishtask;
    private Toolbar toolbar;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initviewanddata(view);
        initLocation();

        toolbar.setTitle("正在定位");
        startLocation();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publishtask:{
                PublishTask.Start(getActivity());
                break;
            }
        }
    }
    private void initviewanddata(View view){
        toolbar= (Toolbar) view.findViewById(R.id.homepagetoolbar);
        publishtask= (FloatingActionButton) view.findViewById(R.id.publishtask);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.goodsview);
        initPersonData();
        adapter=new RecyclerViewAdapter(goodses,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void initPersonData() {
        goodses=new ArrayList<>();
        //添加新闻
        Goods goods1=new Goods("第一个商品","第一个商品的简要信息",R.drawable.boss);
        Goods goods2=new Goods("第二个商品","第二个商品的简要信息",R.drawable.boss);
        Goods goods3=new Goods("第三个商品","第三个商品的简要信息",R.drawable.boss);
        Goods goods4=new Goods("第四个商品","第四个商品的简要信息",R.drawable.boss);
        Goods goods5=new Goods("第五个商品","第五个商品的简要信息",R.drawable.boss);
        Goods goods6=new Goods("第六个商品","第六个商品的简要信息",R.drawable.boss);
        goodses.add(goods1);
        goodses.add(goods2);
        goodses.add(goods3);
        goodses.add(goods4);
        goodses.add(goods5);
        goodses.add(goods5);
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
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }
    private void destroyLocation(){
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
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getActivity());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
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
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append( location.getCity() );//城市
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                //解析定位结果，
                String result = sb.toString();
                toolbar.setTitle(result);
            } else {
                toolbar.setTitle("定位失败");
            }
        }
    };
}
