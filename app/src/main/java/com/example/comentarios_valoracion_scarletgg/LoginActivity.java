package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText var_usuario, var_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        var_usuario = (EditText) findViewById(R.id.txt_usuario);
        var_password = (EditText)findViewById(R.id.txt_contra);
    }

    public void iniciarSesion(View view) {
        String usuario = var_usuario.getText().toString();
        String contraseña = var_password.getText().toString();

        if (usuario.equals("grupo6") && contraseña.equals("123")) {
            // Datos de inicio de sesión correctos, abrir la nueva actividad
            Intent intent = new Intent(this, Perfil_UsuarioActivity.class);
            startActivity(intent);
        } else {
            // Datos de inicio de sesión incorrectos, mostrar un mensaje de error
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

}