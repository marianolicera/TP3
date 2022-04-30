package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class FlashActivity extends AppCompatActivity {

    Switch switchFlash;
    CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        switchFlash = findViewById(R.id.switchFlash);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            switchFlash.setEnabled(true);
        } else {
            Toast.makeText(FlashActivity.this, "Tu dispositivo no tiene flash!", Toast.LENGTH_SHORT).show();
        }

        switchFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    try {
                        cameraManager.setTorchMode("0", false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    switchFlash.setText("Encender");
                } else {
                    try {
                        cameraManager.setTorchMode("0", true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    switchFlash.setText("Apagar");
                }
            }
        });
    }
}