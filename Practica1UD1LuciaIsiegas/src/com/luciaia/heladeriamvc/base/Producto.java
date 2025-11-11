package com.luciaia.heladeriamvc.base;

import java.time.LocalDate;

public class Producto {
    private String nombre;
    private double precio;
    private LocalDate fechaApertura;
    private LocalDate fechaCaducidad;

    public Producto() {
    }

    public Producto(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad) {
        this.fechaApertura = fechaApertura;
        this.fechaCaducidad = fechaCaducidad;
        this.nombre = nombre;
        this.precio = precio;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
