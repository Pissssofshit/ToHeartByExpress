package cn.zucc.qifeng.toheartbyexpress.PushTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 80421 on 2017/6/13.
 */

public class testdis extends Activity implements
        DistrictSearch.OnDistrictSearchListener {
    private ListView listView;
    ArrayList<String> test=new ArrayList<String>();
    private  String TAG="testdis";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DistrictSearch search = new DistrictSearch(this);
        DistrictSearchQuery query = new DistrictSearchQuery();
        //query.setKeywords("杭州");//传入关键字
        query.setKeywords("杭州");//传入关键字
        query.setShowBoundary(true);//是否返回边界值
        search.setQuery(query);
        search.setOnDistrictSearchListener(this);//绑定监听器

        search.searchDistrictAsyn();//开始搜索

        listView = new ListView(this);


    }


    private List<String> getData(){
        Log.d(TAG, "getData: ");
        List<String> data = new ArrayList<String>();
        for(int i=0;i<test.size();i++)
        {
            data.add(test.get(i));
        }

        return data;
    }
    public void onDistrictSearched(DistrictResult districtResult) {
//在回调函数中解析districtResult获取行政区划信息
//在districtResult.getAMapException().getErrorCode()=1000时调用districtResult.getDistrict()方法
//获取查询行政区的结果，详细信息可以参考DistrictItem类。
        Log.d(TAG, "onDistrictSearched: ");
        List<DistrictItem> district = districtResult.getDistrict();

        List<DistrictItem>  tmp=district.get(0).getSubDistrict();
        for(int i=0;i<tmp.size();i++)
        {
            Log.d(TAG, "onDistrictSearched: "+tmp.get(i).getName());
            test.add(tmp.get(i).getName());

        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        setContentView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub

                Log.d(TAG, "onItemClick: "+test.get(arg2));


                Intent intent=new Intent();
                intent.putExtra("quhao",test.get(arg2));
                setResult(1,intent);
                finish();
            }

        });

    }
}

