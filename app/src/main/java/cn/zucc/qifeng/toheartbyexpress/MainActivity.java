package cn.zucc.qifeng.toheartbyexpress;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
<<<<<<< HEAD
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
=======
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

<<<<<<< HEAD
import cn.zucc.qifeng.toheartbyexpress.PushTask.PublishTask;
import cn.zucc.qifeng.toheartbyexpress.service.postionservice;
import cn.zucc.qifeng.toheartbyexpress.service.sendpostion;
import cn.zucc.qifeng.toheartbyexpress.util.Constant;
import cn.zucc.qifeng.toheartbyexpress.util.isBackgroud;

public class MainActivity extends BaseActivity implements View.OnClickListener{
=======
import cn.zucc.qifeng.toheartbyexpress.ItemOfMepage.ChatFragment;
import cn.zucc.qifeng.toheartbyexpress.itemOfHomepage.PublishTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
    private static final String TAG = "MainActivity";

    private MapFragment mapFragment;
    private MeFragment meFragment;
    private HomepageFragment homepageFragment;
<<<<<<< HEAD
    private MessageFragment messageFragment;
    private FloatingActionButton pushtask;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String user_account=null;
    public static void StartMainActivity(Context context,String user_account) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("user_account",user_account);
=======
    private ChatFragment chatFragment;
    FloatingActionButton pushtask;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static void StartMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publishtask:
<<<<<<< HEAD
                PublishTask.Start(this,user_account);
=======
                PublishTask.Start(this);
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        Intent intent=getIntent();
        user_account=intent.getStringExtra("user_account");
        boolean isworkpositonserive = isBackgroud.isServiceWork(getApplicationContext(), Constant.postionserviceaddress);
        boolean isworksendpostionservice=isBackgroud.isServiceWork(getApplicationContext(), Constant.sendposiontservice);
        //如果服务已经启动就不再启动了
        if (!isworkpositonserive) {
            Intent postionsericeintent = new Intent(this,postionservice.class);
            startService(postionsericeintent);
        }
        if (!isworksendpostionservice){
            Intent sendserviceintetn=new Intent(this, sendpostion.class);
            sendserviceintetn.putExtra("user_account",intent.getStringExtra("user_account"));
            Log.d(TAG,intent.getStringExtra("user_account"));
            startService(sendserviceintetn);
        }
         //发送通知栏
//        manager.notify(1,notification);
=======
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d

        fragmentManager = getSupportFragmentManager();
        pushtask= (FloatingActionButton) findViewById(R.id.publishtask);
        pushtask.setOnClickListener(this);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                fragmentTransaction = fragmentManager.beginTransaction();//这个只能提交一次 注意！！
                hideallframgment(fragmentTransaction);
                switch (tabId) {
                    case R.id.tab_map:
                        if (mapFragment == null) {//如果没有创建过就 实例化一个，并add到ft中 ，提交后显示
                            mapFragment = new MapFragment();
                            fragmentTransaction.add(R.id.main_fragment, mapFragment);
                        } else fragmentTransaction.show(mapFragment);
                        break;
                    case R.id.tab_homepage:
                        if (homepageFragment == null) {
                            homepageFragment = new HomepageFragment();
                            fragmentTransaction.add(R.id.main_fragment, homepageFragment);
                        } else fragmentTransaction.show(homepageFragment);
                        break;
                    case R.id.tab_me:
                        if (meFragment == null) {
                            meFragment = new MeFragment();
                            fragmentTransaction.add(R.id.main_fragment, meFragment);
                        } else fragmentTransaction.show(meFragment);
                        break;
                    case R.id.tab_chat:
<<<<<<< HEAD
                        if (messageFragment==null){
                            messageFragment =new MessageFragment();
                            fragmentTransaction.add(R.id.main_fragment,messageFragment);
                        }else fragmentTransaction.show(messageFragment);
=======
                        if (chatFragment==null){
                            chatFragment =new ChatFragment();
                            fragmentTransaction.add(R.id.main_fragment,chatFragment);
                        }else fragmentTransaction.show(chatFragment);
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
                        break;
                    case R.id.tab_null:
                        break;
                }
                fragmentTransaction.commit();
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(MainActivity.this, "小哥哥我已经在了别点了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hideallframgment(FragmentTransaction fragmentTransaction) {
        if (mapFragment != null) fragmentTransaction.hide(mapFragment);
        if (homepageFragment != null) fragmentTransaction.hide(homepageFragment);
        if (meFragment != null) fragmentTransaction.hide(meFragment);
<<<<<<< HEAD
        if(messageFragment!=null) fragmentTransaction.hide(messageFragment);
=======
        if(chatFragment!=null) fragmentTransaction.hide(chatFragment);
>>>>>>> 2938fddd106178d9413369b5d29bee5d17bdbf3d
    }

}