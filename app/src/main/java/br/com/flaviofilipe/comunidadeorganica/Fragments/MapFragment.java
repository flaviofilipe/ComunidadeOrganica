package br.com.flaviofilipe.comunidadeorganica.Fragments;


import android.content.Context;
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
import com.google.android.gms.maps.model.MarkerOptions;

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
        //requestPermission();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("STATUS", "onResume");

    }


    @Override
    public void onPause() {
        super.onPause();
        //Remove atualização do privider para enconomizar recursos
        locationManager.removeUpdates(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.i("STATUS", "MapRead");

        try {

            map = googleMap;

            //Add btn zoom
            map.getUiSettings().setZoomControlsEnabled(true);
            map.setMyLocationEnabled(true);
            //map.setMinZoomPreference(17);


            if(location == null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    locationNow = new LatLng(latitude, longitude);
                    Log.i("LATITUDE", String.valueOf(latitude));
                    Log.i("LONGITUDE", String.valueOf(longitude));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(locationNow, 15.0f));


                    //map.moveCamera(CameraUpdateFactory.newLatLng(locationNow));

                }
            }


                //map.animateCamera(CameraUpdateFactory.newLatLngZoom(locationNow, 12.0f));

        } catch (SecurityException ex) {
            Log.e("Erro Location Map", "ERRO", ex);
        }

        LatLng ifba = new LatLng(-14.841753, -40.877857);
        MarkerOptions marker = new MarkerOptions();
        marker.position(ifba).title("IFBA");

        map.addMarker(marker);

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

