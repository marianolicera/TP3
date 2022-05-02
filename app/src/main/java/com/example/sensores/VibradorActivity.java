package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;


public class VibradorActivity extends AppCompatActivity {
    Button botonvibrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrador);

        botonvibrador = findViewById(R.id.btnvibrar);
        botonvibrador.setForeground(getResources().getDrawable(R.drawable.vibrar));

        botonvibrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
            }
        });
    }

}