package com.pince.listapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pince.listapp.db.ListaSQLite;

public class Configuraciones extends AppCompatActivity {

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
        Button btnBorrarBD = findViewById(R.id.btnBorrarBD);

        final ListaSQLite listaSQLite = (new ListaSQLite(this,"DBListaProductos",null,1));
        db = listaSQLite.getWritableDatabase();
        //Vacía la tabla completa listaProductos (CUIDADO)
        btnBorrarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("DELETE from listaProductos");
                Snackbar.make(view,"Base de datos borrada!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
