package cn.zucc.qifeng.toheartbyexpress;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by 80421 on 2017/5/22.
 */


public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext(){
        return context;
    }
}
