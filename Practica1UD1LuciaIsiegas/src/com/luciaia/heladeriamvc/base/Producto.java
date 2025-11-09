package com.luciaia.heladeriamvc.base;

import java.time.LocalDate;

public class Producto {
    private LocalDate fechaApertura;
    private LocalDate fechaCaducidad;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(LocalDate fechaApertura, LocalDate fechaCaducidad, String nombre, double precio) {
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
