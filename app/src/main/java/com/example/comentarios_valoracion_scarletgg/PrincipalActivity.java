package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void NuevoComentario (View v)
    {
        Intent n_ventana = new Intent(this, ForoActivity.class);
        startActivity(n_ventana);
        //Toast.makeText(v.getContext(), "Esta opción lo llevará a otra ventana", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuPrincipal = getMenuInflater();
        menuPrincipal.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.m_vercomenatario) {
            vercomenatio();
            return true;
        } else if (item.getItemId() == R.id.m_dejarcomentario) {
            dejarcomenatio();
            return true;
        }else if (item.getItemId() == R.id.m_paginaprincipal) {
            paginaprincipal();
            return true;
        }
        return false;
    }


    public void vercomenatio() {
        Toast.makeText(this, "Ha presionado sobre la ocpión Ver comentario", Toast.LENGTH_SHORT).show();
        Intent n_ventana = new Intent(this, ForoActivity.class);
        startActivity(n_ventana);
    }


    public void dejarcomenatio() {
        Toast.makeText(this, "Ha presionado sobre la ocpión Dejar comentario", Toast.LENGTH_SHORT).show();
        Intent n_ventana = new Intent(this, ComentarioActivity.class);
        startActivity(n_ventana);
    }

    public void paginaprincipal() {
        Toast.makeText(this, "Ha presionado sobre la ocpión Página principal", Toast.LENGTH_SHORT).show();
        Intent n_ventana = new Intent(this, PrincipalActivity.class);
        startActivity(n_ventana);
    }
}