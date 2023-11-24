package com.example.comentarios_valoracion_scarletgg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ComentarioActivity extends AppCompatActivity {

    DatabaseReference ComentarioValoraciones;
    private ImageView imageViewIcono;
    private Button btnCambiarIcono;
    private int[] imagenesIcono = {
            R.drawable.user1,
            R.drawable.user2,
            R.drawable.user3,
            R.drawable.user4,
            R.drawable.user5};
    private int indiceActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        /*EditText fecha_tnp = findViewById(R.id.txtFecha);
        EditText nombre_tnp = findViewById(R.id.txtNombre);
        EditText comentario_tnp = findViewById(R.id.txtComentario);
        RatingBar valoracion_tnp = findViewById(R.id.rtValoracionNueva);

        ComentarioValoraciones = FirebaseDatabase.getInstance().getReference().child("Foro");
        ComentarioValoraciones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fecha_tnp.setText(dataSnapshot.child("fecha").getValue().toString());
                nombre_tnp.setText(dataSnapshot.child("nombre").getValue().toString());
                comentario_tnp.setText(dataSnapshot.child("comentario").getValue().toString());
                float valorCalificacion = Float.parseFloat(dataSnapshot.child("valoracion").getValue().toString());
                valoracion_tnp.setRating(valorCalificacion);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error al intentar guardar los datos", error.toException());
            }
        });*/

        imageViewIcono = findViewById(R.id.imageView);
        btnCambiarIcono = findViewById(R.id.cmdCambiarIcono);

        // Configurar el OnClickListener para el botón
        btnCambiarIcono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarIcono();
            }
        });
    }

    /*public void cmdGuardarFirebase(View v) {
        EditText fecha_tnp = findViewById(R.id.txtFecha);
        EditText nombre_tnp = findViewById(R.id.txtNombre);
        EditText comentario_tnp = findViewById(R.id.txtComentario);
        RatingBar valoracion_tnp = findViewById(R.id.rtValoracionNueva);
        //Se crea un hijo de Foro, llamdo Foro con todas las demas ramas; fecha, nombre, comentario y valoracion
        //ComentarioValoraciones.child("Foro").child("fecha").setValue(fecha_tnp.getText().toString());
        ComentarioValoraciones.child("fecha").setValue(fecha_tnp.getText().toString());
        ComentarioValoraciones.child("nombre").setValue(nombre_tnp.getText().toString());
        ComentarioValoraciones.child("comentario").setValue(comentario_tnp.getText().toString());
        ComentarioValoraciones.child("valoracion").setValue(String.valueOf(valoracion_tnp.getRating()));

    }*/

    private void cambiarIcono() {
        indiceActual = (indiceActual + 1) % imagenesIcono.length;
        imageViewIcono.setImageResource(imagenesIcono[indiceActual]);
    }


    public void cmdFecha(View v) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crea un diálogo de calendario
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                // La fecha seleccionada por el usuario
                String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;

                // Asigna la fecha seleccionada al campo de fecha
                EditText fecha_tmp = (EditText) findViewById(R.id.txtFecha);
                fecha_tmp.setText(selectedDate);
            }
        }, year, month, day);

        // Muestra el diálogo de calendario
        datePickerDialog.show();
    }

    public void cmdEnviar(View v)
    {
        CheckBox ch_politica = (CheckBox) findViewById(R.id.chkPoliticaSeguridad);
        if (ch_politica.isChecked()){
            String mensaje = "", estado = "", actcuenta = "", casaInt = "";
            EditText fecha_tmp = (EditText) findViewById(R.id.txtFecha);
            EditText nombre_tmp = (EditText) findViewById(R.id.txtNombre);
            EditText comentario_tmp = (EditText) findViewById(R.id.txtComentario);

            RatingBar rtvaloracion_tmp = findViewById(R.id.rtValoracionNueva);
            float rating = rtvaloracion_tmp.getRating();

            // Obtiene el contenedor de comentarios (lyComentarios) desde el diseño
            LinearLayout lyComentarios = findViewById(R.id.lyComentarios);

            // Crea un nuevo LinearLayout para representar el comentario
            LinearLayout nuevoComentario = new LinearLayout(this);
            nuevoComentario.setOrientation(LinearLayout.VERTICAL);


            mensaje = mensaje + fecha_tmp.getText().toString() + ",";
            mensaje = mensaje + nombre_tmp.getText().toString() + ",";
            mensaje = mensaje + comentario_tmp.getText().toString() + ",";
            mensaje = mensaje + estado.toString() + ",";
            mensaje = mensaje + actcuenta.toString() + ",";
            mensaje = mensaje + casaInt.toString() + ",";
            mensaje = mensaje + String.valueOf(rating).toString() + "\n";

            //Para almacenar en la SD
            almacenarSD(mensaje);

            //Para almacenar en la BD
            almacenarBD(
                    fecha_tmp.getText().toString(),
                    nombre_tmp.getText().toString(),
                    comentario_tmp.getText().toString(),
                    rating);


            Log.e("Registro", mensaje);
            Toast.makeText(v.getContext(), "Se han registrado correctamente", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(v.getContext(), "Debe aceptar las políticas de seguridad", Toast.LENGTH_SHORT).show();
        }
    }

    public void cmdBorrar(View v) {
        EditText fecha_tmp = (EditText) findViewById(R.id.txtFecha);
        EditText nombre_tmp = (EditText) findViewById(R.id.txtNombre);
        EditText comentario_tmp = (EditText) findViewById(R.id.txtComentario);
        RatingBar rtvaloracion_tmp = findViewById(R.id.rtValoracionNueva);

        nombre_tmp.setText("");
        comentario_tmp.setText("");
        rtvaloracion_tmp.setRating(0);
        fecha_tmp.setText("");
    }
    public void cmdCancelar(View v) {
        Intent intent = new Intent(this, ForoActivity.class);
        startActivity(intent);
    }

    public void almacenarBD(String fecha, String nombre, String comentario, float rating) {
        BaseDatosComentario bdComentario = new BaseDatosComentario(this);
        final SQLiteDatabase sqlBD = bdComentario.getWritableDatabase();

        if (sqlBD != null) {
            ContentValues cv = new ContentValues();
            cv.put("fecha", fecha);
            cv.put("nombre", nombre);
            cv.put("comentario", comentario);
            cv.put("rating", rating);

            long result = sqlBD.insert("comentario", null, cv);

            if (result != -1) {
                Toast.makeText(this, "Se grabó en la BD correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al insertar en la BD", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public void almacenarSD(String mensaje) {
        if (verificarStatusSD()) {
            try {
                File f = new File(getExternalFilesDir(null), "usuario.txt");
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f, true));
                fout.write(mensaje);
                fout.close();

            } catch (Exception IO) {
                Log.e("Error", "Error en archivo");
            }
        }
    }

    public boolean verificarStatusSD() {
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Memoria SD lista para usar", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }


    // Método para consultar el registro
    public void consultarComentario(View v) {
        EditText nombre_o = findViewById(R.id.txtNombre);
        EditText fecha_a = findViewById(R.id.txtFecha);
        EditText comentario_o = findViewById(R.id.txtComentario);
        RatingBar ratingB_t = findViewById(R.id.rtValoracionNueva);

        BaseDatosComentario bdComentario = new BaseDatosComentario(this);
        final SQLiteDatabase sqlBD = bdComentario.getReadableDatabase();

        if (sqlBD != null) {
            Cursor resultado = sqlBD.rawQuery(
                    "SELECT fecha, nombre, comentario, rating FROM comentario WHERE nombre = '"
                            + nombre_o.getText().toString() + "'", null);

            if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                @SuppressLint("Range") String fecha_encontrada = resultado.getString(resultado.getColumnIndex("fecha"));
                @SuppressLint("Range") String nombre_encontrado = resultado.getString(resultado.getColumnIndex("nombre"));
                @SuppressLint("Range") String comentario_encontrado = resultado.getString(resultado.getColumnIndex("comentario"));
                @SuppressLint("Range") float rating_encontrado = resultado.getFloat(resultado.getColumnIndex("rating"));

                fecha_a.setText(fecha_encontrada);
                nombre_o.setText(nombre_encontrado);
                comentario_o.setText(comentario_encontrado);
                ratingB_t.setRating(rating_encontrado);
            } else {
                Toast.makeText(this, "No hay comentarios con ese nombre", Toast.LENGTH_SHORT).show();
            }
        }
    }



    // Método para actualizar el registro
    public void actualizarComentario(View v) {
        EditText nombre_o = findViewById(R.id.txtNombre);
        EditText fecha_o = findViewById(R.id.txtFecha);
        EditText comentario_o = findViewById(R.id.txtComentario);
        RatingBar ratingB_t = findViewById(R.id.rtValoracionNueva);

        BaseDatosComentario bdComentario = new BaseDatosComentario(this);
        final SQLiteDatabase sqlBD = bdComentario.getWritableDatabase();

        if (sqlBD != null) {
            ContentValues cv = new ContentValues();
            cv.put("rating", ratingB_t.getRating());
            cv.put("comentario", comentario_o.getText().toString());
            cv.put("fecha", fecha_o.getText().toString());

            int rowsAffected = sqlBD.update("comentario", cv, "nombre = ?", new String[]{nombre_o.getText().toString()});

            if (rowsAffected > 0) {
                Cursor resultado = sqlBD.rawQuery(
                        "SELECT fecha FROM comentario WHERE nombre = '"
                                + nombre_o.getText().toString() + "'", null);

                if (resultado.moveToFirst()) {
                    @SuppressLint("Range") String fecha_actualizada = resultado.getString(resultado.getColumnIndex("fecha"));
                    fecha_o.setText(fecha_actualizada);
                }

                Toast.makeText(this, "Comentario actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al actualizar el comentario", Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Método para eliminar el registro
    public void eliminarComentario(View v) {
        EditText nombre_o = findViewById(R.id.txtNombre);

        BaseDatosComentario bdComentario = new BaseDatosComentario(this);
        final SQLiteDatabase sqlBD = bdComentario.getWritableDatabase();

        if (sqlBD != null) {
            int rowsAffected = sqlBD.delete("comentario", "nombre = ?", new String[]{nombre_o.getText().toString()});

            if (rowsAffected > 0) {
                Toast.makeText(this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos(); // Opcional: limpiar los campos después de eliminar
            } else {
                Toast.makeText(this, "Error al eliminar el registro", Toast.LENGTH_SHORT).show();
            }
        }
    }




    // Método opcional para limpiar los campos después de eliminar
    private void limpiarCampos() {
        EditText nombre_o = findViewById(R.id.txtNombre);
        RatingBar ratingB_t = findViewById(R.id.rtValoracionNueva);

        nombre_o.setText("");
        ratingB_t.setRating(0);
        findViewById(R.id.cmdActualizar).setEnabled(false);
        findViewById(R.id.cmdEliminar).setEnabled(false);
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