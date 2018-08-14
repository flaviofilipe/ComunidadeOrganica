package br.com.flaviofilipe.comunidadeorganica.Fragments;


import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.io.IOException;
import java.util.List;

import br.com.flaviofilipe.comunidadeorganica.FirebaseModels.MapFirebase;
import br.com.flaviofilipe.comunidadeorganica.R;

public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    GoogleMap map;
    private LocationManager locationManager;
    View view;
    double latitude;
    double longitude;
    LatLng locationNow;
    Location location;

    //Tela do fragment 1
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragments_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {

            map = googleMap;

            //Add btn zoom
            map.getUiSettings().setZoomControlsEnabled(true);
            map.setMyLocationEnabled(true);

            //Pega a localização atual e move a cam
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            if (location == null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    locationNow = new LatLng(latitude, longitude);
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(locationNow, 15.0f));


                }
            }

        } catch (SecurityException ex) {
            Log.e("Erro Location Maps", "ERRO", ex);
        }


        MapFirebase mapFirebase = new MapFirebase(getContext(), getFragmentManager(), map);
        mapFirebase.getItens();

    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Muda o status

    }

    @Override
    public void onProviderEnabled(String provider) {
        //Provider habilitado
    }

    @Override
    public void onProviderDisabled(String provider) {
        //Provider Desabilitado
    }



}

