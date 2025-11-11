package com.luciaia.heladeriamvc.base;

import java.time.LocalDate;

public class Batido extends Producto {
    private String sabor;
    private String tipoLeche;
    private double litros;

    public Batido() {
    }

    public Batido(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String sabor, String tipoLeche, double litros) {
        super(nombre, precio, fechaApertura, fechaCaducidad);
        this.sabor = sabor;
        this.tipoLeche = tipoLeche;
        this.litros = litros;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTipoLeche() {
        return tipoLeche;
    }

    public void setTipoLeche(String tipoLeche) {
        this.tipoLeche = tipoLeche;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    @Override
    public String toString() {
        return "Batido: \"" + getNombre() + "\"" + sabor + ", " + tipoLeche + ", " + litros + "L";
    }
}
