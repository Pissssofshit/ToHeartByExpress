package cn.zucc.qifeng.toheartbyexpress.itemOfHomepage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zucc.qifeng.toheartbyexpress.R;

public class PublishTask extends AppCompatActivity {
    public static void Start(Context context){
        Intent intent=new Intent(context,PublishTask.class);
        context.startActivity(intent  );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_task);
    }
}
