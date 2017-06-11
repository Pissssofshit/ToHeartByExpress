package cn.zucc.qifeng.toheartbyexpress;

<<<<<<< HEAD
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
=======
import android.os.Bundle;
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
<<<<<<< HEAD
import android.support.v7.app.AlertDialog;
=======
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.CheckBox;
=======
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.google.gson.Gson;

import java.io.IOException;

import cn.zucc.qifeng.toheartbyexpress.Bean.FeedBack;
import cn.zucc.qifeng.toheartbyexpress.Bean.User;
import cn.zucc.qifeng.toheartbyexpress.service.testservice;
import cn.zucc.qifeng.toheartbyexpress.util.Constant;
import cn.zucc.qifeng.toheartbyexpress.util.HttpUtil;
import cn.zucc.qifeng.toheartbyexpress.util.isBackgroud;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Response;

public class LoginAndRegister extends AppCompatActivity implements View.OnClickListener {
    static final String TAG = "LoginAndResgister";
    private TextView forgotPassword;
    private EditText txtloginAccount, txtloginPassword;
    private FloatingActionButton tosingup;
    private Button loginbutton;
    private String user_account = null, user_password = null;
    private Dialog loginDialog;

=======
public class LoginAndRegister extends AppCompatActivity implements View.OnClickListener {
    static final String TAG = "LoginAndResgister";
    TextView forgotPassword;
    EditText txtloginAccount, txtloginPassword;
    FloatingActionButton tosingup;
    Button loginbutton;
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);

<<<<<<< HEAD
        initview();
=======
        forgotPassword = (TextView) findViewById(R.id.forgotpassword);

        txtloginAccount = (EditText) findViewById(R.id.Loginaccount);
        txtloginPassword = (EditText) findViewById(R.id.Loginpassword);
        loginbutton= (Button) findViewById(R.id.buttonLogin);

        tosingup = (FloatingActionButton) findViewById(R.id.fab);

        forgotPassword.setOnClickListener(this);
        loginbutton.setOnClickListener(this);
        tosingup.setOnClickListener(this);
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.login_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: 111");
        switch (v.getId()) {

            case R.id.forgotpassword:
                Snackbar.make(v, "功能有待完善", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case R.id.fab:
<<<<<<< HEAD
                //跳转到注册
                replaceFragment(new SignUp_Fragment());
                break;
            case R.id.buttonLogin:
                loginDialog.show();
                user_account = txtloginAccount.getText().toString();
                user_password = txtloginPassword.getText().toString();
                //方便测试用：
                if ("".equals(user_account) && "".equals(user_password)) {
                    loginDialog.dismiss();
                    MainActivity.StartMainActivity(this, user_account);

                } else {
                    if (!"".equals(user_account) && !"".equals(user_password)) {
                        User userlogin = new User(user_account, user_password);
                        String message = new Gson().toJson(userlogin);
                        Log.d(TAG, "json格式的登录信息" + message);
                        HttpUtil.post(Constant.URL_Login, message, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                //队出现异常的操作
                                Log.w(TAG, "onFailure: " + e.getMessage());
                                showResponse("Failure");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                //对服务器返回的具体进行操作
                                //这是在新开的一个线程里操作的
                                String responseData = response.body().string();
                                Log.d(TAG, responseData);
                                FeedBack loginfeedback = new Gson().fromJson(responseData, FeedBack.class);

                                showResponse(String.valueOf(loginfeedback.getMessage()));

                            }
                        });
                    } else {
                        loginDialog.dismiss();
                        Toast.makeText(LoginAndRegister.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
                    }
                }
=======
                replaceFragment(new SignUp_Fragment());
                break;
            case R.id.buttonLogin:
//                Snackbar.make(v, "登录功能有待完善", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                MainActivity.StartMainActivity(this);
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
                break;
            default:
                break;
        }
    }
<<<<<<< HEAD

    private void initview() {
        forgotPassword = (TextView) findViewById(R.id.forgotpassword);

        txtloginAccount = (EditText) findViewById(R.id.Loginaccount);
        txtloginPassword = (EditText) findViewById(R.id.Loginpassword);
        loginbutton = (Button) findViewById(R.id.buttonLogin);

        tosingup = (FloatingActionButton) findViewById(R.id.fab);
        loginDialog = new AlertDialog.Builder(this)
                .setTitle("提示").setMessage("正在登陆").create();

        forgotPassword.setOnClickListener(this);
        loginbutton.setOnClickListener(this);
        tosingup.setOnClickListener(this);
    }

    private void showResponse(final String response) {
        //因为修改UI只能在主线程之中
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginDialog.dismiss();
                Toast.makeText(LoginAndRegister.this, response, Toast.LENGTH_LONG).show();
                if ("Failure".equals(response)) {
                    loginDialog.dismiss();
                    Toast.makeText(LoginAndRegister.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                }

                if ("登陆成功".equals(response)) {
                    loginDialog.dismiss();
                    MainActivity.StartMainActivity(LoginAndRegister.this, user_account);
                }
            }
        });
    }

=======
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
}
