package cn.zucc.qifeng.toheartbyexpress;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class PublishTask extends AppCompatActivity {
    public static void Start(Context context){
        Intent intent=new Intent(context,PublishTask.class);
        context.startActivity(intent  );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_task);
        Button button1= (Button) findViewById(R.id.start_map);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            String address=data.getStringExtra("address");
            Log.d("publish", address);
            TextView text= (TextView) findViewById(R.id.address);
            text.setText(address);
        }
    }
}
