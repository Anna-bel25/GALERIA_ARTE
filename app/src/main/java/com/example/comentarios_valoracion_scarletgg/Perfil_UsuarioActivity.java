package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class Perfil_UsuarioActivity extends AppCompatActivity {

    EditText txt_usuario_perfil, txt_contra_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

       // txt_usuario_perfil = (EditText) findViewById(R.id.edittext_usuario_perfil);
       //txt_contra_perfil = (EditText) findViewById(R.id.edittext_contra_perfil);

        EditText edittext_usuario_perfil = findViewById(R.id.edittext_usuario_perfil);
        edittext_usuario_perfil.setEnabled(false);

        EditText edittext_contra_perfil = findViewById(R.id.edittext_contra_perfil);
        edittext_contra_perfil.setEnabled(false);

        CuentaDB cuentaDB = new CuentaDB(this);
        CuentaLogin usuarioActual = cuentaDB.obtenerUsuarioActual();

        if (usuarioActual != null) {
            edittext_usuario_perfil.setText(usuarioActual.getUsuario());
        }
    }
}

