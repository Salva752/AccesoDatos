package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profesor extends AppCompatActivity {
    FeedReaderDbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);

        dbHelper = new FeedReaderDbHelper(getApplicationContext());
        Button btnEst = (Button) findViewById(R.id.btnEst);
        Button btnCons = (Button) findViewById(R.id.btnCons);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        EditText etxtNombre = (EditText) findViewById(R.id.etxtNombre);
        EditText etxtEdad = (EditText) findViewById(R.id.etxtEdad);
        EditText etxtCiclo = (EditText) findViewById(R.id.etxtCiclo);
        EditText etxtCurso = (EditText) findViewById(R.id.etxtCurso);
        EditText etxtDespacho = (EditText) findViewById(R.id.etxtDespacho);

        btnEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profesor.this, Estudiante.class);
                startActivity(i);
                finish();
            }
        });

        btnCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profesor.this, Consultas.class);
                startActivity(i);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gets the data repository in write mode
                db = dbHelper.getWritableDatabase();

                //Crea un nuevo map de valores, en el cual las columnas son las claves  (Constante Columna, Valor del activity)
                ContentValues values = new ContentValues();
                values.put(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_NOMBRE, etxtNombre.getText().toString());
                values.put(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_EDAD, Integer.parseInt(etxtEdad.getText().toString()));
                values.put(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_CICLO, etxtCiclo.getText().toString());
                values.put(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_CURSO, Integer.parseInt(etxtCurso.getText().toString()));
                values.put(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_DESPACHO, Integer.parseInt(etxtDespacho.getText().toString()));

                // Inserta la nueva fila, con los valores del map
                long newRowId = db.insert(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_TABLE_NAME, null, values);
                if ((newRowId != -1)) {
                    Toast.makeText(getApplicationContext(), "Datos introducidos correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR: Datos no introducidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}