package cn.zucc.qifeng.toheartbyexpress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 80421 on 2017/5/17.
 */

public class MeFragment extends Fragment {
    private static final String TAG="MeFragementp";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_me,container,false);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "MeFragment+onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "MeFragment+onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "MeFragment+onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "MeFragment+onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "MeFragment+onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "MeFragment+onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MeFragment+onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "MeFragment+onDetach: ");
    }
}
