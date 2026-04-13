package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //protected EditText editText;
    private Button buttonSave=null;
    private EditText editText=null;
    private SharedPreferences myPrefs=null;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linearlayout);
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
                SharedPreferences.Editor ePrefs = myPrefs.edit();
                ePrefs.putString("nome",MainActivity.this.editText.getText().toString());
                ePrefs.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_ola_mundo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu){
            Log.i("TAG","Settings");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}