package cn.zucc.qifeng.toheartbyexpress.ItemOfMepage;

import cn.zucc.qifeng.toheartbyexpress.util.*;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Message;
import android.support.annotation.Nullable;
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

public class My_address extends AppCompatActivity implements View.OnClickListener {
    public static final int UPDATE_TEXT = 1;

    String responseData = "";
    private EditText etAccount;
    private EditText etPassword;
    private TextView tv_view;
    private Button btnRegister, btnLogin;
    static final String TAG = "My_address";

    public static void Start(Context context) {
        Intent intent = new Intent(context, My_address.class);
        context.startActivity(intent);
    }

    private String login(String account, String password) {
        String loginUrlStr = Constant.URL_Login + "?userAccount=" + account + "&userPassword=" + password;
        return loginUrlStr;
    }

    private String register(String account, String password) {
        String registerUrlStr = Constant.URL_Register + "?userAccount=" + account + "&userPassword=" + password;
        return registerUrlStr;
    }

    private void showResponse(final String response) {
        //因为修改UI只能在主线程之中
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_view.setText(response);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();
        Log.e(TAG, account + "" + password);
        switch (v.getId()) {
            //登录
            case R.id.btn_login:
                if (!"".equals(account) && !"".equals(password)) {
                    String address = login(account, password);
                    Log.d(TAG, "注册 ");
                    HttpUtil.get(address, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            //队出现异常的操作
                            Log.w(TAG, "onFailure: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //对服务器返回的具体进行操作
                            //这是在新开的一个线程里操作的
                            responseData = response.body().string();
                            Log.d(TAG, responseData);
                            showResponse(responseData);
                        }
                    });
                }
                break;
            //注册
            case R.id.btn_register:
                if (!"".equals(account) && !"".equals(password)) {
                    String address = register(etAccount.getText().toString(), etPassword.getText().toString());
                    Log.d(TAG, "登录" + address);
                    HttpUtil.get(address, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            //队出现异常的操作
                            Log.w(TAG, "onFailure: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //对服务器返回的具体进行操作
                            //这是在新开的一个线程里操作的
                            responseData = response.body().string();
                            Log.d(TAG, responseData);
                            showResponse(responseData);
                        }
                    });
                } else
                    Toast.makeText(My_address.this, "账号、密码都不能为空！" + etAccount.getText().toString() + etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        initview();

    }

    private void initview() {
        tv_view = (TextView) findViewById(R.id.dwtextview);
        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

}
