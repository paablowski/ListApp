package com.pince.listapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.pince.listapp.R;
import com.pince.listapp.modelo.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>{

    private List<Producto> listaProductos;
    Context context;

    public ProductoAdapter(List<Producto> listaProductos, Context context){
        this.listaProductos = listaProductos;
        this.context = context;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_producto_cardview, parent, false);
        ProductoViewHolder pvh = new ProductoViewHolder(productView);

        return pvh;
    }


    @Override
    public void onBindViewHolder(final ProductoViewHolder holder, final int position){
        holder.imgProducto.setImageResource(listaProductos.get(position).getImgProducto());
        holder.txtNombreProducto.setText(listaProductos.get(position).getNombreProducto());

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View prompView = layoutInflater.inflate(R.layout.dialog_producto,null);

        holder.imgProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
                dialogo.setView(prompView);
                dialogo.setCancelable(false)
                        .setPositiveButton("Agregar a List", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Snackbar mensaje = Snackbar.make(view,"Ok, se agregó a tu lista!",Snackbar.LENGTH_SHORT);
                                mensaje.show();
                            }
                        })
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                AlertDialog alertD = dialogo.create();
                alertD.show();

            }
        });
    }

    @Override
    public int getItemCount(){
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProducto;
        TextView txtNombreProducto;

        public ProductoViewHolder(View view){
            super(view);
            imgProducto = view.findViewById(R.id.imgProducto);
            txtNombreProducto = view.findViewById(R.id.txtNombreProducto);

        }

    }

}
