package com.document.recibedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btnmapa;
    TextView tvnombre,tvapellido,tvlatitud,tvlongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=(ImageView)findViewById(R.id.idimg);
        tvnombre=(TextView)findViewById(R.id.idnombre);
        tvapellido=(TextView)findViewById(R.id.idapelido);
        tvlatitud=(TextView)findViewById(R.id.idlatitud);
        tvlongitud=(TextView)findViewById(R.id.idlongitud);
        btnmapa=(Button)findViewById(R.id.vermapa);


        Intent intent=getIntent();
        String action=intent.getAction();
        String type=intent.getType();
        if (Intent.ACTION_SEND.equals(action)&&type!=null){
            if("image/*".equals(type)){
                handleSendImage(intent);

                manipularTexto(intent);

            }
        }
        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String la=tvlatitud.getText().toString();
                String lo=tvlongitud.getText().toString();

                irmapa(la,lo);
            }
        });
    }

    private void irmapa(String la, String lo) {

        if (TextUtils.isEmpty(la)){
            Toast.makeText(this, "FAlta  latitiud", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(lo)){
            Toast.makeText(this, "Falta longitiud", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent= new Intent(this,Mapa.class);
            Bundle bundle= new Bundle();
            bundle.putString("la",la);
            bundle.putString("lo",lo);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    public void manipularTexto(Intent intent){
        String sharedText=intent.getStringExtra(Intent.EXTRA_TEXT);
        if(sharedText!=null){
            String []datos =sharedText.split(",");
            String nombre =datos[0];
            String apellido =datos[1];
            String latitud =datos[2];
            String lonigutd=datos[3];

            tvnombre.setText(nombre);
            tvapellido.setText(apellido);
            tvlatitud.setText(latitud);
            tvlongitud.setText(lonigutd);

          //  ((TextView)findViewById(R.id.txt_receptor)).setText(sharedText);

        }
    }
    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            img.setImageURI(imageUri);
            // Update UI to reflect image being shared
        }
    }

}
