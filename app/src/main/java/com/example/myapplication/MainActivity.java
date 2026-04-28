package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Magnifier;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //protected EditText editText;
    //private Button buttonSave=null;
    //private EditText editText=null;
    //private SharedPreferences myPrefs=null;

    private static final int REQUEST_CODE = 100;
    private static final int REQUEST_TO_MULTIPLE_PERMISSIONS = 1;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linearlayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b = (Button) findViewById(R.id.button);
        this.editText = (EditText) findViewById(R.id.editText);
        Toast.makeText(this, "TEXTO", Toast.LENGTH_LONG).show();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TAG", "Botao Clicado");
                editText.setText("Cainan Brito");
                Toast.makeText(MainActivity.this, editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.linearlayout);
        setContentView(R.layout.layout_preferences);;
        buttonSave = this.findViewById(R.id.button);
        editText = this.findViewById(R.id.editText);
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String name = myPrefs.getString("nome","");
        if (name!=null){
            editText.setText(name);
        }

        onClickListener();

    }

    public void onClickListener(){
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LocaleListCompat currentLocales = AppCompatDelegate.getApplicationLocales();

                String targetLang;
                if (!currentLocales.isEmpty() && currentLocales.get(0).getLanguage().equals("en")) {
                    targetLang = "pt";
                } else {
                    targetLang = "en";
                }
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(targetLang));

                SharedPreferences.Editor ePrefs = myPrefs.edit();
                ePrefs.putString("nome",MainActivity.this.editText.getText().toString());
                ePrefs.commit();
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_preferences);
        Button b = findViewById(R.id.button);
        b.setOnClickListener( v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
            } else {
                abrirCamera();
            }
        });
    }

    private void abrirCamera() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao abrir câmera: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            abrirCamera();
        }
    }*/
}