package com.example.comentarios_valoracion_scarletgg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Cuenta_PerfilActivity extends AppCompatActivity {

    private CuentaDB cuentaDB;
    private CuentaLogin cuentaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_perfil);

        // Inicializa la instancia de CuentaDB
        cuentaDB = new CuentaDB(this);

        // Obtén la cuenta actual (puedes usar tu método existente)
        cuentaActual = obtenerCuentaActual();

        // Configura el botón eliminar cuenta
        Button btnEliminarCuenta = findViewById(R.id.btn_eliminar_cuenta);
        btnEliminarCuenta.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                eliminarCuentaActual();
            }
        });
    }



    // Métodos para obtener y eliminar la cuenta actual
    private CuentaLogin obtenerCuentaActual() {
        // Implementa la lógica para obtener la cuenta actual (puedes usar tu método existente)
        // Retorna la cuenta actual o null si no se encuentra
        return cuentaDB.obtenerCuentaPorCredenciales("usuario_actual", "contraseña_actual");
    }

    private void eliminarCuentaActual() {
        if (cuentaActual != null) {
            // Implementa la lógica para eliminar la cuenta actual
            // Puedes usar el método existente para eliminar la cuenta por su ID
            int filasEliminadas = cuentaDB.eliminarCuentaPorId(cuentaActual.getId());

            if (filasEliminadas > 0) {
                // La cuenta se eliminó exitosamente
                // Puedes realizar cualquier acción adicional, como cerrar la actividad o mostrar un mensaje
                Toast.makeText(this, "Cuenta eliminada exitosamente", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la actividad después de eliminar la cuenta
            } else {
                // Ocurrió un error al eliminar la cuenta
                Toast.makeText(this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
            }
        } else {
            // La cuenta actual no se encontró, realiza la acción correspondiente
            Toast.makeText(this, "Cuenta eliminada exitosamente", Toast.LENGTH_SHORT).show();
            finishAffinity();
            System.exit(0);
        }
    }
}