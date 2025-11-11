package com.luciaia.heladeriamvc.base;

import java.time.LocalDate;

public class Gofre extends Producto {
    private String topping;
    private String tipoMasa;
    private boolean gluten;

    public Gofre() {
    }

    public Gofre(LocalDate fechaApertura, LocalDate fechaCaducidad, String nombre, double precio, String topping, String tipoMasa, boolean gluten) {
        super(fechaApertura, fechaCaducidad, nombre, precio);
        this.topping = topping;
        this.tipoMasa = tipoMasa;
        this.gluten = gluten;
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

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }
}
