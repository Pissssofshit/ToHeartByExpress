package cn.zucc.qifeng.toheartbyexpress.ItemOfMepage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zucc.qifeng.toheartbyexpress.R;

public class My_address extends AppCompatActivity {

    public static void Start(Context context){
        Intent intent=new Intent(context,My_address.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
    }
}
