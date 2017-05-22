package cn.zucc.qifeng.toheartbyexpress;

import android.app.Application;
import android.content.Context;

/**
 * Created by 80421 on 2017/5/22.
 */


public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
