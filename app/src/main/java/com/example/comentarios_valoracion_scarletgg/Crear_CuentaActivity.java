package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Crear_CuentaActivity extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtContraseña;
    private CuentaDB cuentaDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        edtUsuario = findViewById(R.id.crea_txt_usuario); // Asigna los EditText según sus IDs
        edtContraseña = findViewById(R.id.crea_txt_contra);

        cuentaDB = new CuentaDB(this);
    }

    public void registrarCuenta(View view) {
        String usuario = edtUsuario.getText().toString();
        String contraseña = edtContraseña.getText().toString();

        // Verifica que los campos no estén vacíos antes de intentar registrar la cuenta
        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            // Llama al método para insertar la cuenta en la base de datos
            long resultado = cuentaDB.insertarCuenta(usuario, contraseña);

            if (resultado != -1) {
                // Éxito al insertar la cuenta
                // Puedes mostrar un mensaje, reiniciar los campos, etc.
                Toast.makeText(this, "Cuenta Creada Exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                // Fallo al insertar la cuenta
                // Puedes manejar el fallo de alguna manera
                Toast.makeText(this, "Fallo al crear la cuenta", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Muestra un mensaje de error si los campos están vacíos
            Toast.makeText(this, "Campo Vacio", Toast.LENGTH_SHORT).show();
        }
    }
}