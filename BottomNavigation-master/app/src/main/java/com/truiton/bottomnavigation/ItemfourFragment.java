package com.truiton.bottomnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ItemfourFragment extends Fragment{
    public static ItemfourFragment newInstance() {
        ItemfourFragment fragment = new ItemfourFragment();
        return fragment;
    }
    GoogleMap map;
        MapView m;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // inflat and return the layout
            View v = inflater.inflate(R.layout.fragment_item_four, container, false);
            m = (MapView) v.findViewById(R.id.mapView);
            m.onCreate(savedInstanceState);
            return v;
        }


        @Override
        public void onResume() {
            super.onResume();
            m.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            m.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            m.onDestroy();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            m.onLowMemory();
        }

    }
