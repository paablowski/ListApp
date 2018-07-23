package com.pince.listapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListaSQLite extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE listaProductos (idProducto INTEGER PRIMARY KEY AUTOINCREMENT,imgProducto INTEGER, nombreProducto TEXT, cantidadProducto INTEGER, categoriaProducto INTEGER)";

    public ListaSQLite(Context context, String nombre,
                       SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS listaProductos");

        db.execSQL(sqlCreate);

    }

}
