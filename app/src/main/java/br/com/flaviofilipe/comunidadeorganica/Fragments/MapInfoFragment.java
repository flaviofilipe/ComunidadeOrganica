package br.com.flaviofilipe.comunidadeorganica.Fragments;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.io.IOException;
import java.util.List;

import br.com.flaviofilipe.comunidadeorganica.Maps;
import br.com.flaviofilipe.comunidadeorganica.R;

@SuppressLint("ValidFragment")
public class MapInfoFragment extends BottomSheetDialogFragment {

    Maps maps;

    TextView txtName;
    TextView txtDescription;
    TextView txtEndereco;
    TextView txtContato;

    private Address localizacao;

    public MapInfoFragment(Maps maps) {
        this.maps = maps;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_mapinfo, container, false);


        String name = maps.getName();
        String description = maps.getDescription();
        String email = maps.getEmail();
        String tel = maps.getTel();
        double latitude = maps.getLatitude();
        double longitude = maps.getLongitude();


        txtName = view.findViewById(R.id.txt_mapName);
        txtDescription = view.findViewById(R.id.txt_mapDescription);
        txtEndereco = view.findViewById(R.id.txt_mapEndereco);
        txtContato = view.findViewById(R.id.txt_mapContato);

        try {
            localizacao = getEndereco(latitude, longitude);
            String endereco = localizacao.getAddressLine(0);
            txtEndereco.setText(endereco);

        } catch (IOException e) {
            e.printStackTrace();
        }

        txtName.setText(name);
        txtDescription.setText(description);

        if (tel != null && email != null)
            txtContato.setText("Tel: " + tel + " | Email: " + email);
        else if(tel != null && email == null)
            txtContato.setText("Tel: " + tel);
        else if(tel == null && email != null)
            txtContato.setText("Email: " + email);
        else
            txtContato.setText("");
        return view;
    }



    public Address getEndereco(double latitude, double longitude)
            throws IOException {

        Geocoder geocoder;
        Address address = null;
        List<Address> addresses;
        geocoder = new Geocoder(getContext());
        addresses = geocoder.getFromLocation(latitude,longitude,1);

        if(addresses.size() > 0)
            address=addresses.get(0);
        return address;
    }

}
