package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Carga la BD antes de entrar a X ventana
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
        Button btnEst = (Button) findViewById(R.id.btnEst);
        Button btnCons = (Button) findViewById(R.id.btnCons);
        Button btnProf = (Button) findViewById(R.id.btnProf);

        btnEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Estudiante.class);
                startActivity(i);
                finish();
            }
        });

        btnCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Consultas.class);
                startActivity(i);
                finish();
            }
        });

        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Profesor.class);
                startActivity(i);
                finish();
            }
        });


    }
}