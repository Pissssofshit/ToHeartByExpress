package cn.zucc.qifeng.toheartbyexpress;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.ItemOfMepage.My_address;
import cn.zucc.qifeng.toheartbyexpress.ItemOfMepage.My_checkupdate;
import cn.zucc.qifeng.toheartbyexpress.ItemOfMepage.My_space;
import cn.zucc.qifeng.toheartbyexpress.ItemOfMepage.My_suggestion;
import cn.zucc.qifeng.toheartbyexpress.ItemOfMepage.My_wallet;

/**
 * Created by 80421 on 2017/5/17.
 */

public class MeFragment extends Fragment {
    private static final String TAG = "MeFragementp";
    private String[] data = {"我的空间", "我的钱包", "地址管理", "检查更新", "意见反馈"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        TextView textView = (TextView) view.findViewById(R.id.metext);
        textView.setText("苟全性命于乱世");
        ImageView imageView = (ImageView) view.findViewById(R.id.meimage);
        imageView.setImageResource(R.drawable.twogou);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        ListView listview = (ListView) view.findViewById(R.id.melist);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data[position].equals("我的空间")) {
                    My_space.Start(getActivity());
                }
                if (data[position].equals("我的钱包")) {
                    My_wallet.Start(getActivity());
                }
                if (data[position].equals("地址管理")) {
                    My_address.Start(getActivity());
                }
                if (data[position].equals("检查更新")) {
                    My_checkupdate.Start(getActivity());
                }
                if (data[position].equals("意见反馈")) {
                    My_suggestion.Start(getActivity());
                }

            }
        });
        return view;
    }


}
