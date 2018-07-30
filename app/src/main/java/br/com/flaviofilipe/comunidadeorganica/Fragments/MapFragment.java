package br.com.flaviofilipe.comunidadeorganica.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.flaviofilipe.comunidadeorganica.Activitys.MainActivity;
import br.com.flaviofilipe.comunidadeorganica.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    private LocationManager locationManager;
    View view;

    //Tela do fragment 1
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragments_map, container, false);
        //requestPermission();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        //ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {
            //Capturando localização atual
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String privider = locationManager.getBestProvider(criteria, true);
            Toast.makeText(getActivity(), "Provider: "+privider, Toast.LENGTH_SHORT);

            map = googleMap;

            //Add btn zoom
            map.getUiSettings().setZoomControlsEnabled(true);
            map.setMyLocationEnabled(true);
        } catch (SecurityException ex) {
            Log.e("Erro Location Map", "ERRO", ex);
        }

        LatLng ifba = new LatLng(-14.841926, -40.8793097);
        MarkerOptions marker = new MarkerOptions();
        marker.position(ifba).title("IFBA");

        map.addMarker(marker);
        map.moveCamera(CameraUpdateFactory.newLatLng(ifba));

    }



    /*
    Ativar GPS
    private void createNoGpsDialog(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        AlertDialog mNoGpsDialog = builder.setMessage("Por favor ative seu GPS para usar esse aplicativo.")
                .setPositiveButton("Ativar", dialogClickListener)
                .create();
        mNoGpsDialog.show();

    }
    */

}

