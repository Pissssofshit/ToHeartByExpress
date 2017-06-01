package cn.zucc.qifeng.toheartbyexpress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.itemofMessagepage.Message;
import cn.zucc.qifeng.toheartbyexpress.itemofMessagepage.RecyclerViewAdapterForMessage;

/**
 * Created by 80421 on 2017/5/22.
 */

public class MessageFragment extends Fragment {

    private  Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<Message> messages;
    private RecyclerViewAdapterForMessage adapterForMessage;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        initview(view);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_friend:
                        Toast.makeText(getContext(),"好友列表",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        return view;
    }



    public void initview(View view){
        toolbar= (Toolbar) view.findViewById(R.id.messagetoolbar);
        toolbar.setTitle("消息");
        toolbar.inflateMenu(R.menu.message_menu);

        initMessageDate();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView= (RecyclerView) view.findViewById(R.id.messagelist);
        adapterForMessage=new RecyclerViewAdapterForMessage(messages,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterForMessage);
    }
    private void initMessageDate(){
        messages=new ArrayList<>();

        Message message1=new Message("消息1","消息简要说明",R.drawable.chat);
        Message message2=new Message("消息2","消息简要说明",R.drawable.chat);
        Message message3=new Message("消息3","消息简要说明",R.drawable.chat);
        Message message4=new Message("消息4","消息简要说明",R.drawable.chat);
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
    }
}
