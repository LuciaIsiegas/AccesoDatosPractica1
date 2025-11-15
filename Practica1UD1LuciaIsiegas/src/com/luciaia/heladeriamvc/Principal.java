package com.luciaia.heladeriamvc;

import com.luciaia.heladeriamvc.gui.ProductosControlador;
import com.luciaia.heladeriamvc.gui.ProductosModelo;
import com.luciaia.heladeriamvc.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        ProductosModelo modelo = new ProductosModelo();
        ProductosControlador controlador = new ProductosControlador(vista, modelo);
    }
}
