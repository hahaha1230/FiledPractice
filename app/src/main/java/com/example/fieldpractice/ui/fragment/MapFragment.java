package com.example.fieldpractice.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.fieldpractice.R;
import com.example.fieldpractice.ui.activity.MainActivity;

/**
 * Created by JG on 2019/4/24.
 */

public class MapFragment extends Fragment {
    private static MapFragment fragment = null;
    public static final int POSITION = 0;
    private MapView mapView;
    private AMap aMap;
    private View mapLayout;

    public static Fragment newInstance(){
        if(fragment==null){
            synchronized(MapFragment.class){
                if(fragment==null){
                    fragment=new MapFragment();
                }
            }
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mapLayout == null) {
            Log.i("hhh", "MF onCreateView() null");
            mapLayout = inflater.inflate(R.layout.tab_map_layout, container, false);
            mapView = (MapView) mapLayout.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            if (aMap == null) {
                aMap = mapView.getMap();
            }
        }else {
            if (mapLayout.getParent() != null) {
                ((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
            }
        }
        return mapLayout;
       // return inflater.inflate(R.layout.tab_map_layout, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

      //  ((MainActivity) activity).onSectionAttached(Constants.MAP_FRAGMENT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.i("hhh", " onResume");
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        Log.i("hhh", " onPause");
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("hhh", " onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onDestroy() {
        Log.i("hhh", " onDestroy");
        super.onDestroy();
        mapView.onDestroy();
    }
}
