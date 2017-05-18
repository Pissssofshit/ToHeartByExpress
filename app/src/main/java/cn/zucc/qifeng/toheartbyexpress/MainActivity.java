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
        fragmentTransaction = fragmentManager.beginTransaction();


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_map:
                        Log.d(TAG, "hi ");
                        if (mapFragment==null)
                            mapFragment = new MapFragment();

                        fragmentTransaction.add(R.id.main_fragment, mapFragment);

                        fragmentTransaction.hide(mapFragment);
                        fragmentTransaction.show(mapFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_share:

                        break;
                    case R.id.tab_me:

                        break;

                }
            }
        });

    }
//    if (meFragment == null)//map_fragmen没有创建过
//    {
//        meFragment = new MeFragment();
//        fragmentTransaction.add(R.id.main_fragment, meFragment);
//    } else {
//        fragmentTransaction.show(meFragment);
//    }
//                        fragmentTransaction.commit();
}