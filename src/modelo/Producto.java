/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class Producto {
    private String nombre;
    private String codigo;
    private String tipoProducto;
    private float precio; 
    private int cantidad;
    private boolean ExistenciaProduct;

    public Producto() {
    }

    public Producto(String nombre, String codigo,String tipo,  int cantidad, float precio, boolean disponibilidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.tipoProducto = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.ExistenciaProduct = disponibilidad;
    }
    
    public Producto(String codigo, String tipo, int cantidad, float precio, boolean disponibilidad) {
        this.codigo = codigo;
        this.precio = precio;
        this.tipoProducto = tipo;
        this.cantidad = cantidad;
        this.ExistenciaProduct = disponibilidad;
    }
    
    
    public Producto(String tipo, int cantidad, float precio, boolean disponibilidad) {
        this.precio = precio;
        this.tipoProducto = tipo;
        this.cantidad = cantidad;
        this.ExistenciaProduct = disponibilidad;
    }
    
    public Producto(int cantidad, float precio, boolean disponibilidad) {
        this.precio = precio;
        this.cantidad = cantidad;
        this.ExistenciaProduct = disponibilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isDisponibilidad() {
        return ExistenciaProduct;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.ExistenciaProduct = disponibilidad;
    }

    public String getTipo() {
        return tipoProducto;
    }

    public void setTipo(String tipo) {
        this.tipoProducto = tipo;
    }
    
}
