package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.comentarios_valoracion_scarletgg.databinding.ActivityMainBinding;

public class Principal_galeria extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_principal_galeria);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CargarFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.Home){
                CargarFragment(new HomeFragment());
            }else if(item.getItemId()==R.id.galeria){
                CargarFragment(new GaleriaFragment());
            }else if(item.getItemId()==R.id.foro){
                CargarFragment(new AcercaFragment());
            }

            return true;
        });

    }

    private void CargarFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}