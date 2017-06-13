package cn.zucc.qifeng.toheartbyexpress.PushTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private EditText phone, address,finishhour, finishminute;
    private Button save;
    private SharedPreferences saveuserinformation;
    private SharedPreferences.Editor editor;
    private String savedphone, savedcity, savedstreet, savedaddress;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {

            city.setText(data.getStringExtra("quhao"));

        }
        else if(requestCode==2)
        {
           street.setText(data.getStringExtra("jiedao"));

        }
    }
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
                intent=new Intent(UserDetails.this,testdis.class);
                intent.putExtra("code",1);
                startActivityForResult(intent,1);
                //Toast.makeText(UserDetails.this, "所在地区", Toast.LENGTH_SHORT).show();
                break;

            case R.id.userdetails_streetlayout:
                if(!"".equals(city.getText().toString())) {
                    intent = new Intent(UserDetails.this, testdis2.class);
                    intent.putExtra("quhaos",city.getText().toString());

                    startActivityForResult(intent, 2);
                }
                break;
            case R.id.userdetails_save:
                //储存本次操作的内容
                if (!"".equals(phone.getText().toString())&&!"请选择".equals(city.getText().toString())&&!"请选择".equals(street.getText().toString())
                        &&!"".equals(address.getText().toString())&&!"".equals(finishhour.getText().toString())&&!"".equals(finishhour.getText().toString())){


                editor = saveuserinformation.edit();
                editor.putString("phone", phone.getText().toString());
                editor.putString("city", city.getText().toString());
                editor.putString("street", street.getText().toString());
                editor.putString("address", address.getText().toString());
                editor.commit();

                //返回到上个地方
                userdetails = new Bundle();
                userdetails.putString("phone", phone.getText().toString());
                userdetails.putString("city", city.getText().toString());
                userdetails.putString("street", street.getText().toString());
                userdetails.putString("address", address.getText().toString());
                userdetails.putString("finishhour", finishhour.getText().toString());
                userdetails.putString("finishminutes", finishhour.getText().toString());
                userdetails.putString("date",spinner.getSelectedItem().toString());
                intent = new Intent(UserDetails.this, PublishTask.class);
                intent.putExtra("userdetails", userdetails);
                setResult(2, intent);
                finish();
                }
                else {
                    Toast.makeText(this,"请填写完整",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this, phone.getText().toString(), Toast.LENGTH_SHORT).show();
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
        //
        phone = (EditText) findViewById(R.id.userdetails_phone);
        //
        save = (Button) findViewById(R.id.userdetails_save);
        save.setOnClickListener(this);
        //get默认值
        saveuserinformation = getSharedPreferences("userpublishtaskaddress", Context.MODE_PRIVATE);
        savedphone = saveuserinformation.getString("phone", "");
        savedcity = saveuserinformation.getString("city", "请选择");
        savedstreet = saveuserinformation.getString("street", "请选择");
        savedaddress = saveuserinformation.getString("address", "");
        //设置默认值
        phone.setText(savedphone);
        city.setText(savedcity);
        street.setText(savedstreet);
        if (!"".equals(savedaddress)) {
            address.setText(savedaddress);
        }


    }


}
