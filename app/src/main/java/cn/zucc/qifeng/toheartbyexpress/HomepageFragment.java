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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;


import cn.zucc.qifeng.toheartbyexpress.PushTask.PublishTask;
import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.Goods;
import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.RecyclerViewAdapterForGood;

/**
 * Created by 80421 on 2017/5/17.
 */

public class HomepageFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ShareFragementp";

    //关于列表的
    private RecyclerView recyclerView;
    private RecyclerViewAdapterForGood adapter;
    private List<Goods> goodses;

    //广播的
    private MyReceiver myReceiver;
    private IntentFilter intentFilter;

    private Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initviewanddata(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myReceiver);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.publishtask:{
//                PublishTask.Start(getActivity());
//                break;
//            }
        }
    }
    private void initviewanddata(View view){
        toolbar= (Toolbar) view.findViewById(R.id.homepagetoolbar);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.goodsview);
        initgoodsData();
        adapter=new RecyclerViewAdapterForGood(goodses,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //广播
        intentFilter=new IntentFilter();
        intentFilter.addAction("cn.zucc.qifeng.toheartbyexpress.MylocBroadcastReceiver");
        myReceiver=new MyReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                String loc=intent.getStringExtra("loc");
                toolbar.setTitle(loc);
            }
        };
        getActivity().registerReceiver(myReceiver,intentFilter);
    }
    private void initgoodsData() {
        goodses=new ArrayList<>();
        //添加新闻
        Goods goods1=new Goods("第一个商品","第一个商品的简要信息",R.drawable.boss);
        Goods goods2=new Goods("第二个商品","第二个商品的简要信息",R.drawable.boss);
        Goods goods3=new Goods("第三个商品","第三个商品的简要信息",R.drawable.boss);
        Goods goods4=new Goods("第四个商品","第四个商品的简要信息",R.drawable.boss);
        Goods goods5=new Goods("第五个商品","第五个商品的简要信息",R.drawable.boss);
        Goods goods6=new Goods("第六个商品","第六个商品的简要信息",R.drawable.boss);
        goodses.add(goods1);
        goodses.add(goods2);
        goodses.add(goods3);
        goodses.add(goods4);
        goodses.add(goods5);
        goodses.add(goods6);
    }

    public  static class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String loc=intent.getStringExtra("loc");
            Log.d(TAG,loc);

        }
    }
}
