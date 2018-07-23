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
    public void onBindViewHolder(ProductoViewHolder holder, final int position){
        holder.imgProducto.setImageResource(listaProductos.get(position).getImgProducto());
        holder.txtNombreProducto.setText(listaProductos.get(position).getNombreProducto());


        holder.imgProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String nombreProducto = listaProductos.get(position).getNombreProducto();
                NumberPicker num = new NumberPicker(context);
                num.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
                num.setMinValue(0);
                num.setMaxValue(100);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(view.getContext());
                dialogo.setTitle(nombreProducto)
                        .setView(num)
                        .setMessage("¿Deseas agregar "+nombreProducto+" a tu lista de compras?")
                        .setNegativeButton("No",null) // Hará nada al clickear "no"
                        .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Snackbar mensaje = Snackbar
                                        .make(view,"Ok, se agregó "+nombreProducto.toLowerCase()+" a tu lista!",Snackbar.LENGTH_SHORT);
                                mensaje.show();
                            }
                        });
                dialogo.show();

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
