package com.luciaia.heladeriamvc.base;

import java.time.LocalDate;

public class Gofre extends Producto {
    private String topping;
    private String tipoMasa;
    private boolean gluten;

    public Gofre() {
    }

    public Gofre(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String topping, String tipoMasa, boolean gluten) {
        super(nombre, precio, fechaApertura, fechaCaducidad);
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

    @Override
    public String toString() {
        return "Gofre: \"" + getNombre() + "\"" + topping + ", " + tipoMasa + ", " + (gluten ? "con gluten" : " sin gluten");
    }
}
