package cn.zucc.qifeng.toheartbyexpress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.Goods;
import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.PublishTask;
import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.RecyclerViewAdapter;

/**
 * Created by 80421 on 2017/5/17.
 */

public class HomepageFragment extends Fragment {
    private static final String TAG = "ShareFragementp";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<Goods> goodses;
    private FloatingActionButton publishtask;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        publishtask= (FloatingActionButton) view.findViewById(R.id.publishtask);
        publishtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublishTask.Start(getActivity());
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.goodsview);
        initPersonData();


        adapter=new RecyclerViewAdapter(goodses,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private void initPersonData() {
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
        goodses.add(goods5);
    }
}
