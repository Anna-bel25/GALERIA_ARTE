package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.comentarios_valoracion_scarletgg.CuentaDB;

public class Perfil_UsuarioActivity extends AppCompatActivity {

    EditText edittext_usuario_perfil, edittext_contra_perfil;
    Button btn_editar_perfil;
    ImageView imgView_editar_1, imgView_editar_2;

    CuentaDB cuentaDB;
    CuentaLogin usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        // Asegúrate de que cuentaDB se inicialice correctamente
        CuentaDB cuentaDB = new CuentaDB(this);

        // Obtén el nombre de usuario del usuario que ha iniciado sesión
        String nombreUsuario = "usuario_que_inició_sesión";
        String contraseñausuario = "contraseña_del_usuario";

        // Llama al método para obtener la cuenta del usuario
        CuentaLogin usuarioActual = cuentaDB.obtenerCuentaPorCredenciales(nombreUsuario, contraseñausuario);

        // txt_usuario_perfil = (EditText) findViewById(R.id.edittext_usuario_perfil);
       //txt_contra_perfil = (EditText) findViewById(R.id.edittext_contra_perfil);

        EditText edittext_usuario_perfil = findViewById(R.id.edittext_usuario_perfil);
        EditText edittext_contra_perfil = findViewById(R.id.edittext_contra_perfil);
        ImageView imgView_editar_1 = findViewById(R.id.imgView_editar_1);
        ImageView imgView_editar_2 = findViewById(R.id.imgView_editar_2);
        Button btn_editar_perfil = findViewById(R.id.btn_editar_perfil);

        // Obtener los datos del intent
        String usuario = getIntent().getStringExtra("usuario");
        String contraseña = getIntent().getStringExtra("contraseña");

        // Mostrar los datos en los EditText
        edittext_usuario_perfil.setText(usuario);
        edittext_contra_perfil.setText(contraseña);


        // Habilitar la edición al hacer clic en la imagen
        imgView_editar_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext_usuario_perfil.setEnabled(true);
            }
        });
        imgView_editar_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext_contra_perfil.setEnabled(true);
            }
        });
        // -----------------------------------------------------


        // En algún lugar de tu código donde quieras guardar los cambios, por ejemplo, al hacer clic en un botón "Guardar Cambios"
      /*  btn_editar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los nuevos datos del usuario
                String nuevoUsuario = edittext_usuario_perfil.getText().toString();
                String nuevaContraseña = edittext_contra_perfil.getText().toString();

                // Actualizar la cuenta en la base de datos
                usuarioActual.setUsuario(nuevoUsuario);
                usuarioActual.setContraseña(nuevaContraseña);

                int filasActualizadas = cuentaDB.actualizarCuenta(usuarioActual);

                // Verificar si la actualización fue exitosa
                if (filasActualizadas > 0) {
                    Toast.makeText(Perfil_UsuarioActivity.this, "Cambios guardados exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Perfil_UsuarioActivity.this, "Error al guardar cambios", Toast.LENGTH_SHORT).show();
                }

                // Deshabilitar la edición después de guardar los cambios
                edittext_usuario_perfil.setEnabled(false);
                edittext_contra_perfil.setEnabled(false);
            }
        });*/

        btn_editar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (usuarioActual != null) {
                        // Obtener los nuevos datos del usuario
                        String nuevoUsuario = edittext_usuario_perfil.getText().toString();
                        String nuevaContraseña = edittext_contra_perfil.getText().toString();

                        // Actualizar la cuenta en la base de datos
                        usuarioActual.setUsuario(nuevoUsuario);
                        usuarioActual.setContraseña(nuevaContraseña);

                        int filasActualizadas = cuentaDB.actualizarCuenta(usuarioActual);

                        // Verificar si la actualización fue exitosa
                        if (filasActualizadas > 0) {
                            Toast.makeText(Perfil_UsuarioActivity.this, "Cambios guardados exitosamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Perfil_UsuarioActivity.this, "Error al guardar cambios", Toast.LENGTH_SHORT).show();
                        }

                        // Deshabilitar la edición después de guardar los cambios
                        edittext_usuario_perfil.setEnabled(false);
                        edittext_contra_perfil.setEnabled(false);
                    } else {
                        // Manejar el caso donde usuarioActual es null
                        Toast.makeText(Perfil_UsuarioActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("PerfilActivity", "Excepción en el evento de clic", e);
                }
            }
        });

    }


}

