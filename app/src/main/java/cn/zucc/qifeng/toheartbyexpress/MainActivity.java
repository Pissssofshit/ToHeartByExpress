package cn.zucc.qifeng.toheartbyexpress;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MapFragment mapFragment;
    private MeFragment meFragment;
    private ShareFragment shareFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static void StartMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();


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
                    case R.id.tab_share:
                        if (shareFragment == null) {
                            shareFragment = new ShareFragment();
                            fragmentTransaction.add(R.id.main_fragment, shareFragment);
                        } else fragmentTransaction.show(shareFragment);
                        break;
                    case R.id.tab_me:
                        if (meFragment == null) {
                            meFragment = new MeFragment();
                            fragmentTransaction.add(R.id.main_fragment, meFragment);
                        } else fragmentTransaction.show(meFragment);
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
        if (shareFragment != null) fragmentTransaction.hide(shareFragment);
        if (meFragment != null) fragmentTransaction.hide(meFragment);

    }

}