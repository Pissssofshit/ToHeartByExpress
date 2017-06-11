package cn.zucc.qifeng.toheartbyexpress.PushTask;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.Bean.FeedBack;
import cn.zucc.qifeng.toheartbyexpress.Bean.PurchaseItem;
import cn.zucc.qifeng.toheartbyexpress.Bean.Task;
import cn.zucc.qifeng.toheartbyexpress.Bean.User;
import cn.zucc.qifeng.toheartbyexpress.MainActivity;
import cn.zucc.qifeng.toheartbyexpress.PushTask.gdmap.poisearch.PoiKeywordSearchActivity;
import cn.zucc.qifeng.toheartbyexpress.R;
import cn.zucc.qifeng.toheartbyexpress.util.Constant;
import cn.zucc.qifeng.toheartbyexpress.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Response;

import static android.widget.Toast.*;


public class PublishTask extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PublishTask";

    private String thisname = "ComponentInfo{cn.zucc.qifeng.toheartbyexpress/cn.zucc.qifeng.toheartbyexpress.PushTask.PublishTask}";
    private TextView text;//购买地址
    private TextView address, phone, sum,deadline;
    private Intent intent;

    private Dialog thisdialog;
    private double longtitude = 0, latitude = 0;
    private List<PurchaseItem> list;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterForPuchase adapterForPuchase;

    private Button additem, deleteitem, publishtask;
    private CardView cradview;

    public static void Start(Context context, String user_account) {
        Intent intent = new Intent(context, PublishTask.class);
        intent.putExtra("user_account", user_account);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_task);

        initview();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String topAcitivity = getTopActivity(getApplicationContext());
                while (thisname.equals(topAcitivity)) {
                    try {
                        double summoney = 0;
                        for (int i = 0; i < list.size(); i++) {
                            PurchaseItem item = list.get(i);
                            if (item.getPrice() != 0 && item.getNumber() != 0) {
                                summoney = summoney + item.getPrice() * item.getNumber();
                            }
                        }
                        showsome(summoney);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String address = data.getStringExtra("address");
//            Log.d("publish", address);
//            Log.d(TAG+"latitude",data.getStringExtra("latitude"));
//            Log.d(TAG+"longtitude",data.getStringExtra("longtitude"));
            longtitude = Double.valueOf(data.getStringExtra("latitude"));
            latitude = Double.valueOf(data.getStringExtra("longtitude"));
            text.setText(address);
        }
        else if (resultCode==2){
            Bundle userdetails=data.getBundleExtra("userdetails");
            String s=userdetails.get("phone").toString();
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        }
        else if(resultCode==3)
        {

        }
    }

    private void showsome(final double i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sum.setText(i + "元");
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publishtask_userdetails:
                //进入收货详细页面中去
                intent=new Intent(PublishTask.this,UserDetails.class);
                startActivityForResult(intent,2);
                break;
            case R.id.publishtask_address:
                intent = new Intent(PublishTask.this, PoiKeywordSearchActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.purchase_additem:
                PurchaseItem item = new PurchaseItem();
                list.add(item);

                //它这个好像是从1开始算的
                adapterForPuchase.notifyItemInserted(list.size() - 1);
//                adapterForPuchase.notifyItemRangeChanged(2,mList.size()-2);
                break;
            case R.id.purchase_delteitem:
                makeText(this, list.size() + "", LENGTH_SHORT).show();
                adapterForPuchase.notifyItemRemoved(list.size() - 1);
                list.remove(list.size() - 1);

                //它这个好像是从1开始算的

                break;
            case R.id.publishtask_button:


                double summoney = 0;
                for (int i = 0; i < list.size(); i++) {
                    PurchaseItem goods = list.get(i);
                    if (goods.getPrice() != 0 && goods.getNumber() != 0) {
                        summoney = summoney + goods.getPrice() * goods.getNumber();
                    }
                }

                if (longtitude == 0 && latitude == 0) {
                    Toast.makeText(this, "请输入购买地址", Toast.LENGTH_SHORT).show();
                } else {
                    if ( "详细地址".equals(address.getText().toString())){
                        Toast.makeText(this,"请输入收货地址",Toast.LENGTH_SHORT).show();
                    }
                    else if ("电话号码".equals(phone.getText().toString())){
                        Toast.makeText(this,"请输入电话号码",Toast.LENGTH_SHORT).show();
                    }
                    else if ("最迟日期".equals(deadline.getText().toString())){
                        Toast.makeText(this,"请输入最迟日期",Toast.LENGTH_SHORT).show();
                    }
                    else {
                    Task task = new Task(getIntent().getStringExtra("user_account"), address.getText().toString(), phone.getText().toString()
                            , list, summoney, longtitude, latitude,deadline.getText().toString());

                    //向客户端发送信息
                    String message = new Gson().toJson(task);
                    HttpUtil.post(Constant.URL_PublishTask, message, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            //队出现异常的操作
                            Log.w(TAG, "onFailure: " + e.getMessage());
                            createmydialog("发布失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //对服务器返回的具体进行操作
                            //这是在新开的一个线程里操作的
                            String responseData = response.body().string();
                            FeedBack loginfeedback = new Gson().fromJson(responseData, FeedBack.class);
                            if (loginfeedback.getCode() == 138)
                                createmydialog("发布成功");
                            else if (loginfeedback.getCode() == 128)
                                createmydialog("发布失败,已有类似任务");

                        }
                    });
                    }
                }

                break;
        }

    }

    private void initview() {
        cradview= (CardView) findViewById(R.id.publishtask_userdetails);
        cradview.setOnClickListener(this);

        publishtask = (Button) findViewById(R.id.publishtask_button);
        publishtask.setOnClickListener(this);

        phone = (TextView) findViewById(R.id.publishtask_phone);
        address = (TextView) findViewById(R.id.publishtask_locaddress);
        sum = (TextView) findViewById(R.id.publishtask_sum);
        deadline= (TextView) findViewById(R.id.publishtask_deadline);

        text = (TextView) findViewById(R.id.publishtask_address);
        text.setOnClickListener(this);

        additem = (Button) findViewById(R.id.purchase_additem);
        additem.setOnClickListener(this);
        deleteitem = (Button) findViewById(R.id.purchase_delteitem);
        deleteitem.setOnClickListener(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.purchaselistview);

        initpurchaseData();
        adapterForPuchase = new RecyclerViewAdapterForPuchase(list, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterForPuchase);


    }


    private void createmydialog(final String message) {
        thisdialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        thisdialog.dismiss();
                        if ("发布成功".equals(message))
                            MainActivity.StartMainActivity(PublishTask.this, null);
                    }
                })
                .create();
    }

    private void initpurchaseData() {
        list = new ArrayList<>();

        PurchaseItem item1 = new PurchaseItem();
        list.add(item1);
    }

    public static String getTopActivity(Context context) {
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            return (runningTaskInfos.get(0).topActivity).toString();
        } else
            return null;
    }

}
