package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.comentarios_valoracion_scarletgg.CuentaDB;

public class Perfil_UsuarioActivity extends AppCompatActivity {

    EditText edittext_usuario_perfil, edittext_contra_perfil;
    Button btn_editar_perfil;
    ImageView imgView_editar_1, imgView_editar_2,img_cuenta;
    TextView txtView_usuario;

    CuentaDB cuentaDB;
    CuentaLogin usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        // Asegúrate de que cuentaDB se inicialice correctamente
        cuentaDB = new CuentaDB(this);

        // Obtén el nombre de usuario del usuario que ha iniciado sesión
        String nombreUsuario = getIntent().getStringExtra("usuario");
        String contraseñausuario = getIntent().getStringExtra("contraseña");

        // Llama al método para obtener la cuenta del usuario
        CuentaLogin usuarioActual = cuentaDB.obtenerCuentaPorCredenciales(nombreUsuario, contraseñausuario);

        EditText edittext_usuario_perfil = findViewById(R.id.edittext_usuario_perfil);
        EditText edittext_contra_perfil = findViewById(R.id.edittext_contra_perfil);
        ImageView imgView_editar_1 = findViewById(R.id.imgView_editar_1);
        ImageView imgView_editar_2 = findViewById(R.id.imgView_editar_2);
        Button btn_editar_perfil = findViewById(R.id.btn_editar_perfil);
        TextView txtView_usuario = findViewById(R.id.txtView_usuario);
        ImageView img_cuenta = findViewById(R.id.img_cuenta);
        ImageView img_estadisticas = findViewById(R.id.img_estadisticas);
        //ImageView img_cuenta = findViewById(R.id.img_cuenta);

        // Obtener los datos del intent
        String usuario = getIntent().getStringExtra("usuario");
        String contraseña = getIntent().getStringExtra("contraseña");

        // Mostrar los datos en los EditText
        txtView_usuario.setText(usuario);
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
        // abrir ventanas desde imagenes
        img_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la nueva actividad
                Intent intent = new Intent(Perfil_UsuarioActivity.this, Cuenta_PerfilActivity.class);

                // Puedes agregar datos adicionales al Intent si es necesario
                // intent.putExtra("clave", valor);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });
        img_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la nueva actividad
                Intent intent = new Intent(Perfil_UsuarioActivity.this, EstadisticasActivity.class);

                // Puedes agregar datos adicionales al Intent si es necesario
                // intent.putExtra("clave", valor);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        // -----------------------------------------------------

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


        btn_editar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (usuarioActual != null) {
                        // Obtener los nuevos datos del usuario
                        String nuevoUsuario = edittext_usuario_perfil.getText().toString();

                        // Actualizar la cuenta en la base de datos
                        usuarioActual.setUsuario(nuevoUsuario);

                        // Actualizar el TextView
                        txtView_usuario.setText(nuevoUsuario);

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

