package com.luciaia.heladeriamvc.base;

import java.time.LocalDate;

public class Helado extends Producto {
    private String sabor;
    private boolean azucar;
    private double litros;

    public Helado() {
    }

    public Helado(LocalDate fechaApertura, LocalDate fechaCaducidad, String nombre, double precio, String sabor, boolean azucar, double litros) {
        super(fechaApertura, fechaCaducidad, nombre, precio);
        this.sabor = sabor;
        this.azucar = azucar;
        this.litros = litros;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public boolean isAzucar() {
        return azucar;
    }

    public void setAzucar(boolean azucar) {
        this.azucar = azucar;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }
}
