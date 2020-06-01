package com.document.recibedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import static com.document.recibedatos.R.*;

public class Mapa extends AppCompatActivity {


    Button btn3;

    String la,lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_mapa);

        la=getIntent().getStringExtra("la");
        lo=getIntent().getStringExtra("lo");

        MapaFragment fr = new MapaFragment();
        Bundle args = new Bundle();

        args.putString("la", la);
        args.putString("lo", lo);
        fr.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.contenerdor,fr).commit();

    }


}
