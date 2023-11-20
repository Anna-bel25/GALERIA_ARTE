package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText var_usuario, var_password;
    private CuentaDB cuentaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        var_usuario = (EditText) findViewById(R.id.txt_usuario);
        var_password = (EditText) findViewById(R.id.txt_contra);

        cuentaDB = new CuentaDB(this);

        //abrir ventana(actividad) de crear cuenta
        Button btn_crear_cuenta_login = findViewById(R.id.btn_crear_cuenta_login);

        btn_crear_cuenta_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para abrir Activity2
                Intent intent = new Intent(LoginActivity.this, Crear_CuentaActivity.class);
                startActivity(intent);
            }
        });
        // ----------------------------------------------
    }


    public void iniciarSesion(View view) {
        String usuario = var_usuario.getText().toString();
        String contraseña = var_password.getText().toString();

        if (cuentaDB.verificarCredenciales(usuario, contraseña)) {
            // Las credenciales son correctas, puedes redirigir a la siguiente actividad
            Intent intent = new Intent(this, Principal_galeria.class);
            startActivity(intent);
            finish(); // Finaliza la actividad de login para que no se pueda volver atrás
        } else {
            // Las credenciales son incorrectas, muestra un mensaje de error o realiza otra acción
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }


}