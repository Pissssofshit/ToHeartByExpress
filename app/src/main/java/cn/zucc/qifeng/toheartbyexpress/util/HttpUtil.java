package cn.zucc.qifeng.toheartbyexpress.util;

import android.os.Bundle;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 80421 on 2017/5/25.
 */

public class HttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void get(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).get().build();
        client.newCall(request).enqueue(callback);
    }

//    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder().url(address).build();
//        client.newCall(request).enqueue(callback);
//    }

    public static void post(String address,String json,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request.Builder().url(address).post(body).build();
        client.newCall(request).enqueue(callback);
    }
}
