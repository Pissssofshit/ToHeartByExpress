package cn.zucc.qifeng.toheartbyexpress.PushTask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cn.zucc.qifeng.toheartbyexpress.R;

public class UserDetails extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Bundle userdetails;
    private Spinner spinner;
    private LinearLayout citylayout, streetlayout;
    private TextView city, street;
    private EditText address, starthour, startminutes, finishhour, finishminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_publishtask_userdetails);

        initview();


        //返回到上个activity的数据
//        userdetails=new Bundle();
//        userdetails.putString("phone","123123a");
//        intent=new Intent();
//        intent.putExtra("userdetails",userdetails);
//        setResult(2,intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userdetails_citylayout:
                Toast.makeText(UserDetails.this, "所在地区", Toast.LENGTH_SHORT).show();
                break;
            case R.id.userdetails_streetlayout:
                Toast.makeText(UserDetails.this, "街道", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initview() {

        //日期选项
        spinner = (Spinner) findViewById(R.id.spinnertime);
        final String arr[] = new String[]{
                "今天", "明天"
        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);

        spinner.setAdapter(arrayAdapter);//显示今天 明天

        starthour = (EditText) findViewById(R.id.userdetails_starthour);
        startminutes = (EditText) findViewById(R.id.userdetails_startminutes);
        finishhour = (EditText) findViewById(R.id.userdetails_finishhour);
        finishminute = (EditText) findViewById(R.id.userdetails_finishhour);

        //地区和街道
        citylayout = (LinearLayout) findViewById(R.id.userdetails_citylayout);
        citylayout.setOnClickListener(this);
        city = (TextView) findViewById(R.id.userdetails_citytv);

        streetlayout = (LinearLayout) findViewById(R.id.userdetails_streetlayout);
        streetlayout.setOnClickListener(this);
        street = (TextView) findViewById(R.id.userdetails_streettv);

        //address
        address = (EditText) findViewById(R.id.userdetails_address);


    }


}
