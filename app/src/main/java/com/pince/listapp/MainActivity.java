package com.pince.listapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pince.listapp.adapter.ProductoAdapter;
import com.pince.listapp.db.ListaSQLite;
import com.pince.listapp.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductoAdapter mAdapter;
    private List<Producto> listaProductos;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListaSQLite listaSQLite = (new ListaSQLite(this,"DBListaProductos",null,1));
        db = listaSQLite.getWritableDatabase();

        mRecyclerView = findViewById(R.id.idRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
        listaProductos= new ArrayList<>();


        Cursor cursor = db.rawQuery("SELECT * FROM listaProductos", null);
        //Este código:
        // 1. lee fila por fila de la tabla de una BD
        // 2. asigna cada campo de la tabla a cada variable(idProducto, etc...)
        // 3. instancia objetos de tipo Producto, los pone en un List para luego mostrarlos en forma de CardView.
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{

                int idProducto = cursor.getInt(0);
                int imgProducto = cursor.getInt(1);
                String nombreProducto = cursor.getString(2);
                int cantidadProducto = cursor.getInt(3);
                int categoriaProducto = cursor.getInt(4);

                System.out.println("img: "+imgProducto);
                listaProductos.add(new Producto(idProducto,imgProducto,nombreProducto,cantidadProducto,categoriaProducto));

            }while (cursor.moveToNext());
        }

        mAdapter = new ProductoAdapter(listaProductos,this);
        mRecyclerView.setAdapter(mAdapter);




    }

    //CREA EL MENU (···) EN LA ESQUINA SUPERIOR DERECHA
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_home:
                Intent intent = new Intent(MainActivity.this,GuardarProductos.class);
                startActivity(intent);
                break;
            case R.id.menu_dashboard:

                break;
            case R.id.menu_notifications:
                intent = new Intent(MainActivity.this,Configuraciones.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
