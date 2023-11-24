package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ForoActivity extends AppCompatActivity {
    private LinearLayout lyComentario1, lyComentario2, lyComentario3,
            lyComentario4, lyComentario5, lyComentario6;
    //private List<LinearLayout> comentarios;
    private Button cmdRecientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        lyComentario1 = findViewById(R.id.lyComentario1);
        lyComentario2 = findViewById(R.id.lyComentario2);
        lyComentario3 = findViewById(R.id.lyComentario3);
        lyComentario4 = findViewById(R.id.lyComentario4);
        lyComentario5 = findViewById(R.id.lyComentario5);
        lyComentario6 = findViewById(R.id.lyComentario6);
        cmdRecientes = findViewById(R.id.cmdRecientes);

    }

    public void cmdRecientes (View v)
    {
        lyComentario1.setVisibility(View.VISIBLE);
        lyComentario2.setVisibility(View.VISIBLE);
        lyComentario3.setVisibility(View.GONE);

        lyComentario4.setVisibility(View.GONE);
        lyComentario5.setVisibility(View.GONE);
        lyComentario6.setVisibility(View.GONE);
    }

    public void cmdRMasValorados (View v)
    {
        lyComentario2.setVisibility(View.VISIBLE);
        lyComentario5.setVisibility(View.VISIBLE);
        lyComentario6.setVisibility(View.VISIBLE);

        lyComentario1.setVisibility(View.GONE);
        lyComentario3.setVisibility(View.GONE);
        lyComentario4.setVisibility(View.GONE);
    }

    public void cmdTodo (View v)
    {
        lyComentario1.setVisibility(View.VISIBLE);
        lyComentario2.setVisibility(View.VISIBLE);
        lyComentario3.setVisibility(View.VISIBLE);
        lyComentario4.setVisibility(View.VISIBLE);
        lyComentario5.setVisibility(View.VISIBLE);
        lyComentario6.setVisibility(View.VISIBLE);
    }

    public void NuevoComentario (View v)
    {
        Intent n_ventana = new Intent(this, ComentarioActivity.class);
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