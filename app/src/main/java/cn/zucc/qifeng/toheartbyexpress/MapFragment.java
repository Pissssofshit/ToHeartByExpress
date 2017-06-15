package cn.zucc.qifeng.toheartbyexpress;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Inflater;

import cn.zucc.qifeng.toheartbyexpress.Bean.PurchaseItem;
import cn.zucc.qifeng.toheartbyexpress.Bean.SeekTask;
import cn.zucc.qifeng.toheartbyexpress.Bean.Task;
import cn.zucc.qifeng.toheartbyexpress.itemOfNear.AcceptTask;
import cn.zucc.qifeng.toheartbyexpress.itemOfNear.RecyclerVIewAdapterForTask;
import cn.zucc.qifeng.toheartbyexpress.util.Constant;
import cn.zucc.qifeng.toheartbyexpress.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 80421 on 2017/5/17.
 */

public class MapFragment extends Fragment {
    private static final String TAG = "MapFragementp";

    private RecyclerView recyclerView;
    private List<mapTask> tasklist;
    private RecyclerVIewAdapterForTask adapterForTask;

    private HomepageFragment.MyReceiver myReceiver;
    private IntentFilter intentFilter;

    private static boolean ifgetlongitude = false;
    private double longitude, latitude;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        initdata();
        initview(view);
        adapterForTask = new RecyclerVIewAdapterForTask(tasklist, getActivity());
        recyclerView.setAdapter(adapterForTask);
        adapterForTask.setOnItemClickListener(new RecyclerVIewAdapterForTask.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + position  , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), AcceptTask.class);
                intent.putExtra("test","mytest");
                startActivity(intent);
            }
        });
        return view;
    }




    private void initview(View view) {


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.task_recyclerview);
        //adapterForTask = new RecyclerVIewAdapterForTask(tasklist, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
       // recyclerView.setAdapter(adapterForTask);

        //广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("cn.zucc.qifeng.toheartbyexpress.MylocBroadcastReceiver");
        myReceiver = new HomepageFragment.MyReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);

                longitude = intent.getDoubleExtra("longitude", 0);
                latitude = intent.getDoubleExtra("latitude", 0);
                ifgetlongitude = true;
            }
        };
        getActivity().registerReceiver(myReceiver, intentFilter);

    }

    private void loaddata() {
        if (ifgetlongitude) {
            //发送到gettask 获得一个task列表
            SeekTask seekTask = new SeekTask(longitude, latitude);
            String message = new Gson().toJson(seekTask);
            HttpUtil.post(Constant.URL_GetTask, message, new okhttp3.Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String data=response.body().toString();
                    //传来一个json格式的mapTask 类型的list.
                    // 创建一个JsonParser
                    JsonParser parser = new JsonParser();
                    // 通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
                    JsonElement el = parser.parse(data);
                    // 把JsonElement对象转换成JsonArray
                    JsonArray jsonArray = null;
                    if (el.isJsonArray()) {
                        jsonArray = el.getAsJsonArray();
                    }
                    // 遍历JsonArray对象
                    mapTask onetask=null;
                    Iterator it = jsonArray.iterator();
                    while (it.hasNext()) {
                        JsonElement e = (JsonElement) it.next();
                        // JsonElement转换为JavaBean对象
                        onetask= new Gson().fromJson(e, mapTask.class);
                        tasklist.add(onetask);
                    }
                    Toast.makeText(getContext(),"yes"+"size"+tasklist.size(),Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(getActivity(),"无法连接到服务器",Toast.LENGTH_SHORT).show();
                }
            });
            //有任务了加载
            if (tasklist.size() != 0) {
                adapterForTask = new RecyclerVIewAdapterForTask(tasklist, getActivity());
                recyclerView.setAdapter(adapterForTask);
//                adapterForTask.setOnItemClickListener(new RecyclerVIewAdapterForTask.MyItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int postion) {
//                        Toast.makeText(getContext(),postion+"",Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        }
    }

    private void initdata() {
        List<PurchaseItem> purchaseItemList=new ArrayList<>();
        List<PurchaseItem> purchaseItemList1=new ArrayList<>();
        PurchaseItem item=new  PurchaseItem("item1","somedetails",10,10);
        PurchaseItem item1=new  PurchaseItem("item2","somedetails",10,10);
        purchaseItemList.add(item);
        purchaseItemList1.add(item1);
        mapTask task1=new mapTask(1,"task1","taskpublishuser","1234567",purchaseItemList,1,"10:50","清风",20);
        mapTask task2=new mapTask(1,"task1","taskpublishuser","1234567",purchaseItemList1,1,"10:50","清风",20);
        tasklist=new ArrayList<>();
        tasklist.add(task1);
        tasklist.add(task2);
//        if (tasklist.size() != 0) {
//            adapterForTask = new RecyclerVIewAdapterForTask(tasklist, getActivity());
//            recyclerView.setAdapter(adapterForTask);
//            adapterForTask.setOnItemClickListener(new RecyclerVIewAdapterForTask.OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int postion) {
//                    Toast.makeText(getContext(),postion+"",Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(myReceiver);
    }

    public class mapTask extends cn.zucc.qifeng.toheartbyexpress.Bean.Task {
        private String username;
        private double distance;
        private int publish_id;

        public mapTask(int publish_id, String username, String user_address, String user_phone, List<PurchaseItem> purcharseList,
                       double sum, String deadline, String storename, double distance) {
            this.publish_id = publish_id;
            this.username = username;
            this.user_address = user_address;
            this.user_phone = user_phone;
            this.purcharseList = purcharseList;
            this.sum = sum;
            this.deadline = deadline;
            this.storename = storename;
            this.distance = distance;
        }

        public int getPublish_id() {
            return publish_id;
        }

        public void setPublish_id(int publish_id) {
            this.publish_id = publish_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

    }

}
