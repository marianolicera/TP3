package com.example.sensores;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnTomarFoto, btnGuardarFoto;
    ImageView imgView;
    Bitmap bitmap;

    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int TAKE_PICTURE = 101;

    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initUI();

        btnTomarFoto.setOnClickListener(this);
        btnGuardarFoto.setOnClickListener(this);

    }

    private void initUI() {

        btnGuardarFoto = findViewById(R.id.btnGuardarFoto);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        imgView = findViewById(R.id.imageView);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnTomarFoto) {
            checkPermissionCamera();

        } else if (id == R.id.btnGuardarFoto) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==TAKE_PICTURE){
            if(resultCode == Activity.RESULT_OK && data != null){
                bitmap  = (Bitmap) data.getExtras().get("data");
                imgView.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePicture();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkPermissionCamera() { //evaluar permisos y version de sdk

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                takePicture();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
            }

        }else{
            takePicture();
        }
    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, TAKE_PICTURE);
        }
    }
}