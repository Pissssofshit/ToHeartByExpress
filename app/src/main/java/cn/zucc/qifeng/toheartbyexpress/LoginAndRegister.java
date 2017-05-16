package cn.zucc.qifeng.toheartbyexpress;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginAndRegister extends AppCompatActivity implements View.OnClickListener {
    static final String TAG = "LoginAndResgister";
    TextView forgotPassword;
    EditText txtloginAccount, txtloginPassword;
    FloatingActionButton tosingup;
    Button loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);

        forgotPassword = (TextView) findViewById(R.id.forgotpassword);

        txtloginAccount = (EditText) findViewById(R.id.Loginaccount);
        txtloginPassword = (EditText) findViewById(R.id.Loginpassword);
        loginbutton= (Button) findViewById(R.id.buttonLogin);

        tosingup = (FloatingActionButton) findViewById(R.id.fab);

        forgotPassword.setOnClickListener(this);
        loginbutton.setOnClickListener(this);
        tosingup.setOnClickListener(this);

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
                replaceFragment(new SignUp_Fragment());
                break;
            case R.id.buttonLogin:
                Snackbar.make(v, "登录功能有待完善", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            default:
                break;
        }
    }
}
