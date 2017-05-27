package cn.zucc.qifeng.toheartbyexpress;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;

import cn.zucc.qifeng.toheartbyexpress.Bean.FeedBack;
import cn.zucc.qifeng.toheartbyexpress.Bean.User;

import cn.zucc.qifeng.toheartbyexpress.ItemOfMepage.My_address;
import cn.zucc.qifeng.toheartbyexpress.util.Constant;
import cn.zucc.qifeng.toheartbyexpress.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 80421 on 2017/5/16.
 */

public class SignUp_Fragment extends Fragment implements View.OnClickListener {
    private static String TAG = "SignUp_Fragment";

    private EditText txtsignupAccount, txtsignupPassword, txtsignupname;
    private Button buttonsignup;
    private String responseData = "";
    private TextView testresponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
//        buttonsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "功能有待完善", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
//
//        });
        initview(view);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                String user_account = txtsignupAccount.getText().toString();
                String user_password = txtsignupPassword.getText().toString();
                String user_name=txtsignupname.getText().toString();
                if (!"".equals(user_account) && !"".equals(user_password)&&!"".equals(user_name)) {

                    //转换为json类型的字符串发送给服务器
                    User userlogin = new User(user_account,user_password,user_name);
                    String message = new Gson().toJson(userlogin);
                    Log.d(TAG, "json格式的登录信息" + message);

                    HttpUtil.post(Constant.URL_Register, message, new okhttp3.Callback() {
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
                            FeedBack loginfeedback = new Gson().fromJson(responseData, FeedBack.class);
                            String showresponse = "code:" + loginfeedback.getCode() + "message:" + loginfeedback.getMessage();
                            showResponse(showresponse);

                        }
                    });
                } else
                    Toast.makeText(getActivity(), "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initview(View view) {
        txtsignupAccount = (EditText) view.findViewById(R.id.Signupaccount);
        txtsignupPassword = (EditText) view.findViewById(R.id.Signuppassword);
        txtsignupname = (EditText) view.findViewById(R.id.Signupname);
        testresponse = (TextView) view.findViewById(R.id.testresponse);

        buttonsignup = (Button) view.findViewById(R.id.btn_signup);


        buttonsignup.setOnClickListener(this);
    }

    private void showResponse(final String response) {
        //因为修改UI只能在主线程之中
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),response,Toast.LENGTH_SHORT).show();
                testresponse.setText(response);
            }
        });
    }
}
