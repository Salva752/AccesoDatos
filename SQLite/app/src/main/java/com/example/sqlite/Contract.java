package com.example.sqlite;

import android.provider.BaseColumns;

public class Contract {
    public final class FeedReaderContract {
        //Para evitar errors
        private FeedReaderContract() {}

        /* Clase de la tabla Alumnos */
        public abstract class FeedEntryAlumnos implements BaseColumns {
            public static final String ALUMNOS_TABLE_NAME = "Alumnos";
            public static final String ALUMNOS_COLUMN_NAME_NOMBRE = "nombre";
            public static final String ALUMNOS_COLUMN_NAME_EDAD = "edad";
            public static final String ALUMNOS_COLUMN_NAME_CICLO = "ciclo";
            public static final String ALUMNOS_COLUMN_NAME_CURSO = "curso";
            public static final String ALUMNOS_COLUMN_NAME_MEDIA = "media";
        }

        /* Clase de la tabla Profesores */
        public abstract class FeedEntryProfesores implements BaseColumns {
            public static final String PROFESORES_TABLE_NAME = "Profesores";
            public static final String PROFESORES_COLUMN_NAME_NOMBRE = "nombre";
            public static final String PROFESORES_COLUMN_NAME_EDAD = "edad";
            public static final String PROFESORES_COLUMN_NAME_CICLO = "ciclo";
            public static final String PROFESORES_COLUMN_NAME_CURSO = "curso";
            public static final String PROFESORES_COLUMN_NAME_DESPACHO = "despacho";
        }
        //Crear tabla Alumnos
        public static final String ALUMNOS_SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedReaderContract.FeedEntryAlumnos.ALUMNOS_TABLE_NAME + " (" +
                        FeedReaderContract.FeedEntryAlumnos._ID + " INTEGER PRIMARY KEY," +
                        FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_NOMBRE + " TEXT," +
                        FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_EDAD + " INTEGER," +
                        FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CICLO + " TEXT," +
                        FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_CURSO + " TEXT," +
                        FeedReaderContract.FeedEntryAlumnos.ALUMNOS_COLUMN_NAME_MEDIA + " TEXT)";

        //Crear tabla Profesores
        public static final String PROFESORES_SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedReaderContract.FeedEntryProfesores.PROFESORES_TABLE_NAME + " (" +
                        FeedReaderContract.FeedEntryProfesores._ID + " INTEGER PRIMARY KEY," +
                        FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_NOMBRE + " TEXT," +
                        FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_EDAD + " INTEGER," +
                        FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_CICLO + " TEXT," +
                        FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_CURSO + " TEXT," +
                        FeedReaderContract.FeedEntryProfesores.PROFESORES_COLUMN_NAME_DESPACHO + " TEXT)";

        //Borrar tabla Alumnos
        public static final String ALUMNOS_SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryAlumnos.ALUMNOS_TABLE_NAME;

        //Borrar tabla Profesores
        public static final String PROFESORES_SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryProfesores.PROFESORES_TABLE_NAME;

    }

}
