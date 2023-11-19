package com.example.comentarios_valoracion_scarletgg;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.comentarios_valoracion_scarletgg.CuentaLogin;


import androidx.annotation.Nullable;

import java.util.HashMap;

public class CuentaDB extends SQLiteOpenHelper {

    private static final String tabla_cuenta = "CREATE TABLE Cuentas(id integer primary key autoincrement," +
            "usuario TEXT," +
            "contraseña TEXT)";
    private static final String db_name = "DbCuenta.sqlite";

    private static final int db_version = 1;

    public CuentaDB (Context c) {
        super (c, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla_cuenta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Lógica de actualización (si es necesaria) cuando cambia la versión de la base de datos
    }

    // Método para insertar una nueva cuenta en la base de datos
    public long insertarCuenta(String usuario, String contraseña) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("contraseña", contraseña);
        long id = db.insert("Cuentas", null, values);
        //db.close();
        return id;
    }

    // verifica las credenciales en la base de datos
    public boolean verificarCredenciales(String usuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id"};
        String selection = "usuario = ? AND contraseña = ?";
        String[] selectionArgs = {usuario, contraseña};
        Cursor cursor = db.query("Cuentas", columns, selection, selectionArgs, null, null, null);

        boolean credencialesCorrectas = cursor.getCount() > 0;

        cursor.close();
        //db.close();

        return credencialesCorrectas;
    }


    // Método para obtener el usuario actual
    public CuentaLogin obtenerUsuarioActual() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", "usuario", "contraseña"};
        Cursor cursor = db.query("Cuentas", columns, null, null, null, null, null);

        CuentaLogin cuenta = null;

        if (cursor.moveToFirst()) {
            cuenta = new CuentaLogin();
            cuenta.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cuenta.setUsuario(cursor.getString(cursor.getColumnIndex("usuario")));
            cuenta.setContraseña(cursor.getString(cursor.getColumnIndex("contraseña")));
        }

        cursor.close();

        return cuenta;
    }

}
