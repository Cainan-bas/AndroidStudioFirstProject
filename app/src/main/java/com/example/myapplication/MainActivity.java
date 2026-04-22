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
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                //if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                } else {
                    Toast.makeText(this, "Permissao ja concedida 1", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Permissao ja concedida anteriormente", Toast.LENGTH_SHORT).show();
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                if(Environment.isExternalStorageManager()){
                    Toast.makeText(this, "Permissao ja concedida 2", Toast.LENGTH_SHORT).show();
                } else {
                    //Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    //Uri uri = Uri.fromParts("package", getPackageName(), null);
                    //intent.setData(uri);
                    startActivity(intent);

                }
            }
        });
    }

    private boolean checkAndRequestPermission() {
        int diskPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionNeeded = new ArrayList<>();

        if (diskPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]), REQUEST_TO_MULTIPLE_PERMISSIONS
            );
            return false;
        }

        return true;
    }
}