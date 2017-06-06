package cn.zucc.qifeng.toheartbyexpress.PushTask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.Bean.PurchaseItem;
import cn.zucc.qifeng.toheartbyexpress.PushTask.gdmap.poisearch.PoiKeywordSearchActivity;
import cn.zucc.qifeng.toheartbyexpress.R;


public class PublishTask extends AppCompatActivity implements View.OnClickListener{
   private static final String TAG="PublishTask";

    private TextView text;

    private List<PurchaseItem> list;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterForPuchase adapterForPuchase;

    private Button additem,deleteitem,publishtask;

    public static void Start(Context context){
        Intent intent=new Intent(context,PublishTask.class);
        context.startActivity(intent  );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_task);

        initview();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publishtask_address:
                Intent intent=new Intent(PublishTask.this,PoiKeywordSearchActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.purchase_additem:
                PurchaseItem item=new PurchaseItem();
                list.add(item);

                //它这个好像是从1开始算的
                adapterForPuchase.notifyItemInserted(list.size()-1);
//                adapterForPuchase.notifyItemRangeChanged(2,mList.size()-2);
                break;
            case R.id.purchase_delteitem:
                Toast.makeText(this,list.size()+"",Toast.LENGTH_SHORT).show();
                list.remove(list.size()-1);

                //它这个好像是从1开始算的
                adapterForPuchase.notifyItemRemoved(list.size());
                break;
            case R.id.publishtask_button:
                PurchaseItem purchaseItem=list.get(0);
                String message="name="+purchaseItem.getName()+"number"+purchaseItem.getNumber()
                        +"price"+purchaseItem.getPrice()+"details"+purchaseItem.getDetails();
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

                break;
        }

    }

    private void initview(){
        publishtask= (Button) findViewById(R.id.publishtask_button);
        publishtask.setOnClickListener(this);

        text= (TextView) findViewById(R.id.publishtask_address);
        text.setOnClickListener(this);

        additem= (Button) findViewById(R.id.purchase_additem);
        additem.setOnClickListener(this);
        deleteitem= (Button) findViewById(R.id.purchase_delteitem);
        deleteitem.setOnClickListener(this);



        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView= (RecyclerView) findViewById(R.id.purchaselistview);

        initpurchaseData();
        adapterForPuchase=new RecyclerViewAdapterForPuchase(list,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterForPuchase);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            String address=data.getStringExtra("address");
            Log.d("publish", address);
            //TextView text= (TextView) findViewById(R.id.publishtask_address);
            text.setText(address);
        }
    }
    private void initpurchaseData(){
        list=new ArrayList<>();

        PurchaseItem item1=new PurchaseItem();
        list.add(item1);
    }

}
