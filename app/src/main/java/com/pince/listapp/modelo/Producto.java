package com.pince.listapp.modelo;

public class Producto {

    private int idProducto;
    private int imgProducto;
    private String nombreProducto;
    private int cantidadProducto;
    private int categoriaProducto;

    public Producto(int idProducto, int imgProducto, String nombreProducto, int cantidadProducto, int categoriaProducto) {
        this.idProducto = idProducto;
        this.imgProducto = imgProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.categoriaProducto = categoriaProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(int imgProducto) {
        this.imgProducto = imgProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(int categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }
}
