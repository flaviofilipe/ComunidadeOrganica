package br.com.flaviofilipe.comunidadeorganica.FirebaseModels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import br.com.flaviofilipe.comunidadeorganica.Fragments.MapInfoFragment;
import br.com.flaviofilipe.comunidadeorganica.Maps;

public class MapFirebase {

    private GoogleMap map;
    private Context context;
    FragmentManager fragmentManager;
    MapInfoFragment mapInfo;
    private FirebaseDatabase mDatabase;
    DatabaseReference myRef;


    public MapFirebase(Context context, FragmentManager fragmentManager, GoogleMap map) {
        this.map = map;
        this.context = context;
        this.fragmentManager = fragmentManager;

        mDatabase = FirebaseDatabase.getInstance();
    }

    private void getLocations(final DataSnapshot dataSnapshot) {
        if (dataSnapshot != null) {

            String name = dataSnapshot.getValue(Maps.class).getName();
            String description = dataSnapshot.getValue(Maps.class).getDescription();
            Double latitude = dataSnapshot.getValue(Maps.class).getLatitude();
            Double longitude = dataSnapshot.getValue(Maps.class).getLongitude();


            //mapInfo = new MapInfoFragment(dataSnapshot,name);

            LatLng location = new LatLng(latitude, longitude);
            MarkerOptions marker = new MarkerOptions();
            marker.position(location)
                    .title(name)
                    .snippet(description);
            map.addMarker(marker);
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    //mapInfo.show(fragmentManager,"teste"); //Exibr o fragment);

                    //Toast.makeText(context, dataSnapshot.getValue().toString(), Toast.LENGTH_LONG).show();

                    infoMap(marker.getTitle());

                    //Toast.makeText(context, marker.getTitle(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    public void getItens() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("LOCATIONS");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                Log.i("MarkerInfoSnapShot00", dataSnapshot.getValue().toString());
                getLocations(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                getLocations(dataSnapshot);
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

    public void infoMap(String name) {

        myRef = mDatabase.getReference().child("LOCATIONS");
        myRef.orderByChild("name").equalTo(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot != null) {
                        Maps myMap = snapshot.getValue(Maps.class);
                        Log.i("MyMap", myMap.getName());


                        mapInfo = new MapInfoFragment(myMap);
                        mapInfo.show(fragmentManager,"teste"); //Exibr o fra
                        Log.i("MarkerInfoSnapShot0111", dataSnapshot.getValue().toString());
                    }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
