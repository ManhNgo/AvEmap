package com.example.manhngo.avemap.Fragments;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.manhngo.avemap.Fragments.CallBack.FragmentCallbacks;
import com.example.manhngo.avemap.MainActivity;
import com.example.manhngo.avemap.PermissionUtils.PermissionUtils;
import com.example.manhngo.avemap.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MMapFragment extends Fragment implements
        OnMapReadyCallback,
        OnMarkerClickListener,
        OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        FragmentCallbacks{

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Context context= getActivity();

    private SupportMapFragment fragment;

    MainActivity main;

    private GoogleMap mMap;

    private boolean showTraffic = false;

    private boolean mShowPermissionDeniedDialog = false;

    private static String TAG = MMapFragment.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
        View fragmentView = inflate(R.layout.control_map_layout, container, true);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
        }
        fragment.getMapAsync(this);
    }

    private Marker mHCMUS;

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        updateMapType();
        updateTraffic();
        updateMyLocation();


        LatLng HCMUS = new LatLng(10.763904, 106.682113);
        mHCMUS = mMap.addMarker(new MarkerOptions()
                .position(HCMUS)
                .title("Khoa Hoc Tu Nhien"));
        mHCMUS.setTag(0);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HCMUS, 15));

        mMap.setOnMarkerClickListener(this);

    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(getContext(), "Map is not ready yet", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void updateMyLocation() {
        if (!checkReady()) {
            return;
        }

        mMap.setMyLocationEnabled(false);
        // Enable the location layer. Request the location permission if needed.
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Uncheck the box until the layer has been enabled and request missing permission.
            PermissionUtils.requestPermission((AppCompatActivity) getActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
    }


    private void updateTraffic() {
        if (!checkReady()) {
            return;
        }
        mMap.setTrafficEnabled(false);
    }

    private void updateMapType() {

       /* if (mMap == null) {
            return;
        }

        String layerName = ((String) mSpinner.getSelectedItem());
        if (layerName.equals(getString(R.string.normal))) {
            mMap.setMapType(MAP_TYPE_NORMAL);
        } else if (layerName.equals(getString(R.string.hybrid))) {
            mMap.setMapType(MAP_TYPE_HYBRID);


        } else if (layerName.equals(getString(R.string.satellite))) {
            mMap.setMapType(MAP_TYPE_SATELLITE);
        } else if (layerName.equals(getString(R.string.terrain))) {
            mMap.setMapType(MAP_TYPE_TERRAIN);
        } else if (layerName.equals(getString(R.string.none_map))) {
            mMap.setMapType(MAP_TYPE_NONE);
        } else {
            Log.i("LDA", "Error setting layer with name " + layerName);
        }*/
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(
                getActivity(),
                "Example de Message for Android",
                Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        if (clickCount != null) {
            clickCount++;
            marker.setTag(clickCount);
            Toast.makeText(getActivity(),
                    marker.getTitle() +
                            " has been clicked " +
                            clickCount +
                            " times.", Toast.LENGTH_SHORT).show();

        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, results,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            mMap.setMyLocationEnabled(true);
            //mMyLocationCheckbox.setChecked(true);
        } else {
            mShowPermissionDeniedDialog = true;
        }
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {

    }

    @Override
    public void onMsgFromMainToFragment_Traffic(boolean showTraffic) {
        Toast.makeText(
                getActivity(),
                "Example de Message for Android" + showTraffic,
                Toast.LENGTH_SHORT).show();
        this.showTraffic = showTraffic;
    }

    @Override
    public void onMsgFromMainToFragmentIntLenght(int lenght) {

    }
}
