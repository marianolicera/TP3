package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnIngresarCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIngresarCamara = (Button) findViewById(R.id.btnCamara);
        btnIngresarCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CameraActivity.class);
                startActivity(i);
            }
        });
    }


    /*public void onClick(View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnCamara:
                miIntent=new Intent(MainActivity.this,CameraActivity.class);
                startActivity(miIntent);
                break;
            case R.id.btn:
                miIntent=new Intent(MainActivity.this,ConsultarCapitalActivity.class);
                startActivity(miIntent);
                break;
            case R.id.btn3:
                miIntent=new Intent(MainActivity.this,BorrarCiudadActivity.class);
                startActivity(miIntent);
                break;
        }
    }*/
}