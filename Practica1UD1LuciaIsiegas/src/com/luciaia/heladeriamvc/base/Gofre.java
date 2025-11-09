package com.luciaia.heladeriamvc.base;

import java.time.LocalDate;

public class Gofre extends Producto {
    private String topping;
    private String tipoMasa;
    private boolean nata;

    public Gofre() {
    }

    public Gofre(LocalDate fechaApertura, LocalDate fechaCaducidad, String nombre, double precio, String topping, String tipoMasa, boolean nata) {
        super(fechaApertura, fechaCaducidad, nombre, precio);
        this.topping = topping;
        this.tipoMasa = tipoMasa;
        this.nata = nata;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getTipoMasa() {
        return tipoMasa;
    }

    public void setTipoMasa(String tipoMasa) {
        this.tipoMasa = tipoMasa;
    }

    public boolean isNata() {
        return nata;
    }

    public void setNata(boolean nata) {
        this.nata = nata;
    }
}
