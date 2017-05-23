package cn.zucc.qifeng.toheartbyexpress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * Created by 80421 on 2017/5/17.
 */

public class MapFragment extends Fragment {
    private static final String TAG = "MapFragementp";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MapFragment+onCreate: ");
    }

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        Log.d(TAG, "MapFragment+onCreateView: ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "MapFragment+onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "MapFragment+onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "MapFragment+onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "MapFragment+onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "MapFragment+onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "MapFragment+onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MapFragment+onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "MapFragment+onDetach: ");
    }
}
