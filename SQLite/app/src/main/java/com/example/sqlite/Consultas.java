package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Consultas extends AppCompatActivity {
    FeedReaderDbHelper dbHelper;
    SQLiteDatabase dbRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        dbHelper = new FeedReaderDbHelper(getApplicationContext());
        Button btnEst = (Button) findViewById(R.id.btnEst);
        Button btnProf = (Button) findViewById(R.id.btnProf);
        Button btnTodosEstudiantes = (Button) findViewById(R.id.btnTodosEstudiantes);
        Button btnTodosProfesores = (Button) findViewById(R.id.btnTodosProfesores);
        Button btnCursoEstudiantes = (Button) findViewById(R.id.btnCursoEstudiantes);
        Button btnCicloEstudiantes = (Button) findViewById(R.id.btnCicloEstudiantes);
        TextView tvConsultas = (TextView) findViewById(R.id.tvConsultas);

        btnEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Consultas.this, Estudiante.class);
                startActivity(i);
                finish();
            }
        });

        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Consultas.this, Profesor.class);
                startActivity(i);
                finish();
            }
        });

        btnTodosEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para leer de la db
                dbRead = dbHelper.getReadableDatabase();

                Cursor cursor = dbRead.query(
                        Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_TABLE_NAME,  // La tabla a la que le haces el Query
                        null,                                                     // El array de todas las columnas
                        null,                                                    // Las columnas para el WHERE
                        null,                                                 // Los valores para el WHERE
                        null,                                                     // Group by
                        null,                                                      // Filtro por Group by
                        null                                                      // El orden
                );

                //Para imprimir la informacion de TODOS
                String todo = "";
                if(!cursor.moveToNext()){
                    Toast.makeText(getApplicationContext(), "No hay nada introducido a la base de datos, porfavor introduce algo", Toast.LENGTH_LONG).show();
                }else {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        todo = todo + "Nombre: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_NOMBRE)) + "\n";
                        todo = todo + "Edad: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_EDAD)) + "\n";
                        todo = todo + "Ciclo: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CICLO)) + "\n";
                        todo = todo + "Curso: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CURSO)) + "\n";
                        todo = todo + "Media: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_MEDIA)) + "\n";
                        todo = todo + "\n";
                        cursor.moveToNext();

                    }
                    tvConsultas.setText(todo);
                }
            }
        });

        btnTodosProfesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para leer de la db
                dbRead = dbHelper.getReadableDatabase();

                Cursor cursor = dbRead.query(
                        Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_TABLE_NAME,  // La tabla a la que le haces el Query
                        null,                                                     // El array de todas las columnas
                        null,                                                    // Las columnas para el WHERE
                        null,                                                 // Los valores para el WHERE
                        null,                                                     // Group by
                        null,                                                      // Filtro por Group by
                        null                                                      // El orden
                );
                String todo = "";
                if(!cursor.moveToNext()){
                    Toast.makeText(getApplicationContext(), "No hay nada introducido a la base de datos, porfavor introduce algo", Toast.LENGTH_LONG).show();
                }else {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        todo = todo + "Nombre: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_NOMBRE)) + "\n";
                        todo = todo + "Edad: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_EDAD)) + "\n";
                        todo = todo + "Ciclo: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_CICLO)) + "\n";
                        todo = todo + "Curso: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_CURSO)) + "\n";
                        todo = todo + "Despacho: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_DESPACHO)) + "\n";
                        todo = todo + "\n";
                        cursor.moveToNext();
                    }
                    tvConsultas.setText(todo);
                }
            }
        });

        btnCursoEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para leer de la db
                dbRead = dbHelper.getReadableDatabase();

                Cursor cursor = dbRead.query(
                        Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_TABLE_NAME,                       // La tabla a la que le haces el Query
                        null,                                                                          // El array de todas las columnas
                        null,                                                                          // Las columnas para el WHERE
                        null,                                                                       // Los valores para el WHERE
                        null,                                                                           // Group by
                        null,                                                                            // Filtro por Group by
                        Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CURSO+" DESC"  // El orden
                );

                //Para imprimir la informacion de TODOS
                String todo = "";
                if(!cursor.moveToNext()){
                    Toast.makeText(getApplicationContext(), "No hay nada introducido a la base de datos, porfavor introduce algo", Toast.LENGTH_LONG).show();
                }else {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        todo = todo + "Nombre: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_NOMBRE)) + "\n";
                        todo = todo + "Edad: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_EDAD)) + "\n";
                        todo = todo + "Ciclo: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CICLO)) + "\n";
                        todo = todo + "Curso: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CURSO)) + "\n";
                        todo = todo + "Media: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_MEDIA)) + "\n";
                        todo = todo + "\n";
                        cursor.moveToNext();

                    }
                    tvConsultas.setText(todo);
                }
            }
        });

        btnCicloEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para leer de la db
                dbRead = dbHelper.getReadableDatabase();

                Cursor cursor = dbRead.query(
                        Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_TABLE_NAME,                        // La tabla a la que le haces el Query
                        null,                                                                           // El array de todas las columnas
                        null,                                                                          // Las columnas para el WHERE
                        null,                                                                       // Los valores para el WHERE
                        null,                                                                           // Group by
                        null,                                                                            // Filtro por Group by
                        Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CICLO+" ASC"  // El orden
                );

                //Para imprimir la informacion de TODOS
                String todo = "";
                if(!cursor.moveToNext()){
                    Toast.makeText(getApplicationContext(), "No hay nada introducido a la base de datos, porfavor introduce algo", Toast.LENGTH_LONG).show();
                }else {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        todo = todo + "Nombre: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_NOMBRE)) + "\n";
                        todo = todo + "Edad: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_EDAD)) + "\n";
                        todo = todo + "Ciclo: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CICLO)) + "\n";
                        todo = todo + "Curso: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CURSO)) + "\n";
                        todo = todo + "Media: " + cursor.getString(cursor.getColumnIndex(Contract.FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_MEDIA)) + "\n";
                        todo = todo + "\n";
                        cursor.moveToNext();

                    }
                    tvConsultas.setText(todo);
                }
            }
        });

    }
}