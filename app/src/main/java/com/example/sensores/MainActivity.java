package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnIngresarCamara, btnIngresarFlash, btnIngresarVibrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIngresarCamara = (Button) findViewById(R.id.btnCamara);
        btnIngresarFlash = (Button) findViewById(R.id.btnFlash);
        btnIngresarVibrar = (Button) findViewById(R.id.btnVibrar);


        btnIngresarCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CameraActivity.class);
                startActivity(i);
            }
        });

        btnIngresarFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FlashActivity.class);
                startActivity(i);
            }
        });
        btnIngresarVibrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),VibradorActivity.class);
                startActivity(i);
            }
        });
    }

}