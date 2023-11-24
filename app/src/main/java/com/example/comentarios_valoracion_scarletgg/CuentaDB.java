package com.example.comentarios_valoracion_scarletgg;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.comentarios_valoracion_scarletgg.CuentaLogin;


import androidx.annotation.Nullable;

import java.util.Arrays;
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

    // verifica las credenciales en la base de datos
    @SuppressLint("Range")
    public CuentaLogin obtenerCuentaPorCredenciales(String usuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", "usuario", "contraseña"};
        String selection = "usuario = ? AND contraseña = ?";

        // Verificar que tanto usuario como contraseña no sean nulos
        if (usuario == null || contraseña == null) {
            Log.e("CuentaDB", "Usuario o contraseña nulos");
            return null;
        }

        String[] selectionArgs = {usuario, contraseña};
        Cursor cursor = db.query("Cuentas", columns, selection, selectionArgs, null, null, null);

        CuentaLogin cuenta = null;

        Log.d("CuentaDB", "Query: " + selection + ", Args: " + Arrays.toString(selectionArgs));

        if (cursor.moveToFirst()) {
            cuenta = new CuentaLogin();
            cuenta.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cuenta.setUsuario(cursor.getString(cursor.getColumnIndex("usuario")));
            cuenta.setContraseña(cursor.getString(cursor.getColumnIndex("contraseña")));

            Log.d("CuentaDB", "Usuario encontrado: " + cuenta.getUsuario());
        } else {
            Log.d("CuentaDB", "Usuario no encontrado");
        }

        cursor.close();

        return cuenta;
    }




    /*public CuentaLogin EditarUsuario() {
        // Supongamos que ya tienes una instancia de CuentaDB llamada cuentaDB

        // Obtén la cuenta actual
        CuentaLogin cuentaActual = cuentaDB.obtenerUsuarioActual();

        // Verifica si se encontró una cuenta
        if (cuentaActual != null) {
            // Modifica el usuario y la contraseña según sea necesario
            cuentaActual.setUsuario("nuevoUsuario");
            cuentaActual.setContraseña("nuevaContraseña");

            // Actualiza la cuenta en la base de datos
            int filasActualizadas = cuentaDB.actualizarCuenta(cuentaActual);

            if (filasActualizadas > 0) {
                Log.d("CuentaDB", "Cuenta actualizada exitosamente");
            } else {
                Log.e("CuentaDB", "Error al actualizar la cuenta");
            }
        } else {
            Log.e("CuentaDB", "No se encontró una cuenta para actualizar");
        }

    }*/

    // Método para actualizar la cuenta en la base de datos
   /* public int actualizarCuenta(CuentaLogin cuenta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", cuenta.getUsuario());
        values.put("contraseña", cuenta.getContraseña());

        // Actualizar la fila en la tabla
        return db.update("Cuentas", values, "id = ?", new String[]{String.valueOf(cuenta.getId())});
    }*/

    public int actualizarCuenta(CuentaLogin cuenta) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("usuario", cuenta.getUsuario());
            values.put("contraseña", cuenta.getContraseña());

            // Actualizar la fila en la tabla
            return db.update("Cuentas", values, "id = ?", new String[]{String.valueOf(cuenta.getId())});
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CuentaDB", "Excepción al actualizar cuenta", e);
            return -1; // O algún otro valor que indique un error
        }
    }

    // Método para eliminar una cuenta por su ID
    public int eliminarCuentaPorId(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete("Cuentas", "id = ?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CuentaDB", "Excepción al eliminar cuenta por ID", e);
            return -1; // O algún otro valor que indique un error
        }
    }

}
