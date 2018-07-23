package com.pince.listapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pince.listapp.db.ListaSQLite;
public class GuardarProductos extends AppCompatActivity {

    String[] img = {"palta","cerveza","jugo","naranja","pescado","pan","queso"};

    int imgProducto;
    String nombreProducto;
    int cantidadProducto;
    int categoriaProducto;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_productos);
        final Spinner spinnerProductos = findViewById(R.id.spinnerProductos);
        spinnerProductos.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,img));

        spinnerProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imgProducto = GuardarProductos.this.getResources().getIdentifier(
                        spinnerProductos.getSelectedItem().toString(),
                        "drawable",
                        GuardarProductos.this.getPackageName()
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final ListaSQLite listaSQLite = (new ListaSQLite(this,"DBListaProductos",null,1));
        db = listaSQLite.getWritableDatabase();

        final EditText txtNombreProducto = findViewById(R.id.txtNombreProducto);
        Button btnAgregar = findViewById(R.id.btnAgregar);



        //Toma los datos que hemos puesto en el EditText y los agrega a la tabla listaProductos
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombreProducto = txtNombreProducto.getText().toString();
                cantidadProducto = 1;
                categoriaProducto = 5;

                //Agrega una fila con la informacion de un producto
                ContentValues valores = new ContentValues();
                valores.put("imgProducto",imgProducto);
                valores.put("nombreProducto",nombreProducto);
                valores.put("cantidadProducto",cantidadProducto);
                valores.put("categoriaProducto",categoriaProducto);
                db.insert("listaProductos",null,valores);


                //Esconde el teclado, y limpia el campo "Nombre"
                hideSoftKeyboard();
                txtNombreProducto.setText("");
                Snackbar.make(view,nombreProducto+" guardado.",Toast.LENGTH_SHORT)
                .show();



            }
        });


    }

    //Esconde el teclado al dar click en el botón agregar
    public void hideSoftKeyboard(){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
}
