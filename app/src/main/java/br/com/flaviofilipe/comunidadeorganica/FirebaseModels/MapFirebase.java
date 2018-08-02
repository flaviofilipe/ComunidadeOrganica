package br.com.flaviofilipe.comunidadeorganica.FirebaseModels;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import br.com.flaviofilipe.comunidadeorganica.Maps;
import br.com.flaviofilipe.comunidadeorganica.Posts;

public class MapFirebase {

    GoogleMap map;
    public MapFirebase(GoogleMap map) {
        this.map = map;
    }

    public void getLocations(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("LOCATIONS");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.i("LOCATIONS_LATITUDE", String.valueOf(dataSnapshot.getValue(Maps.class).getLatitude()));
                Log.i("LOCATIONS_LOGITUDE", String.valueOf(dataSnapshot.getValue(Maps.class).getLongitude()));

                String name = dataSnapshot.getValue(Maps.class).getName();
                String description = dataSnapshot.getValue(Maps.class).getDescription();
                Double latitude = dataSnapshot.getValue(Maps.class).getLatitude();
                Double longitude = dataSnapshot.getValue(Maps.class).getLongitude();


                LatLng location = new LatLng(latitude, longitude);
                MarkerOptions marker = new MarkerOptions();
                marker.position(location).title(name);
                map.addMarker(marker);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

                String name = dataSnapshot.getValue(Maps.class).getName();
                String description = dataSnapshot.getValue(Maps.class).getDescription();
                Double latitude = dataSnapshot.getValue(Maps.class).getLatitude();
                Double longitude = dataSnapshot.getValue(Maps.class).getLongitude();


                LatLng location = new LatLng(latitude, longitude);
                MarkerOptions marker = new MarkerOptions();
                marker.position(location).title(name);
                map.addMarker(marker);
                Log.i("LOCATIONS", dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        };

        query.addChildEventListener(childEventListener);
    }
}
