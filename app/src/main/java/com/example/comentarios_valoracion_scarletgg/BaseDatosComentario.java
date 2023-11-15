package com.example.comentarios_valoracion_scarletgg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosComentario extends SQLiteOpenHelper {
    private static final String tabla_comentario = "CREATE TABLE comentario(id integer primary key autoincrement," +
            "fecha TEXT," +
            "nombre TEXT," +
            "comentario TEXT," +
            "rating FLOAT)";
    private static final String db_name = "DbComentario.sqlite";

    private static final int db_version = 1;

    // Método para eliminar un registro por ID de forma rápida
    public void eliminarRegistroRapido(int id) {
        getWritableDatabase().delete(tabla_comentario, "id = ?", new String[]{String.valueOf(id)});
    }


    public BaseDatosComentario (Context c) {
        super (c, db_name, null, db_version);
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla_comentario);

    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1 ) {

    }
}
