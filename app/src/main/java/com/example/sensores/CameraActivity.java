package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    Button btnCamara;
    ImageView imgView;
    String rutaImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        btnCamara = (Button) findViewById(R.id.btnCamaraTake);
        imgView = (ImageView) findViewById(R.id.imageView);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            File imagenArchivo = null;
            try {
                imagenArchivo = createImage();
            }catch (IOException ex){
                Log.e("Error!", ex.toString());
            }

            if(imagenArchivo != null){
                Uri fotoUri = FileProvider.getUriForFile(this, "com.example.sensortp3.fileprovider", imagenArchivo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intent, 1);
            }
        }
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data) {
        super.onActivityResult(requesCode, resultCode, data);
        if (requesCode == 1 && resultCode == RESULT_OK) { //validación
            //Bundle extras = data.getExtras(); //nos trae la información
            Bitmap imageBitmap = BitmapFactory.decodeFile(rutaImagen); //usamos bitmap para obtener la imagen
            imgView.setImageBitmap(imageBitmap); //mostramos la imagen ya almacenada en el dispositivo
        }
    }

    private File createImage() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);
        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }
}