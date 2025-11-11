package com.luciaia.heladeriamvc.gui;

import com.luciaia.heladeriamvc.base.Batido;
import com.luciaia.heladeriamvc.base.Gofre;
import com.luciaia.heladeriamvc.base.Helado;
import com.luciaia.heladeriamvc.base.Producto;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductosModelo {
    private ArrayList<Producto> listaProductos;

    public ProductosModelo() {
        listaProductos = new ArrayList<Producto>();
    }

    public ArrayList<Producto> obtenerProductos() {
        return listaProductos;
    }

    public void borrarProductos(){
        listaProductos.clear();
    }

    public void eliminarProducto(Producto producto){
        listaProductos.remove(producto);
    }

    public void altaHelado(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String sabor, boolean azucar, double litros) {
        Helado helado = new Helado(nombre, precio, fechaApertura, fechaCaducidad, sabor, azucar, litros);
        listaProductos.add(helado);
    }

    public void altaGofre(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String topping, String tipoMasa, boolean gluten) {
        Gofre gofre = new Gofre(nombre, precio, fechaApertura, fechaCaducidad, topping, tipoMasa, gluten);
        listaProductos.add(gofre);
    }

    public void altaBatido(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String sabor, String tipoLeche, double litros) {
        Batido batido = new Batido(nombre, precio, fechaApertura, fechaCaducidad, sabor, tipoLeche, litros);
        listaProductos.add(batido);
    }

    public void editarHelado(Helado helado, String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String sabor, boolean azucar, double litros) {
        helado.setNombre(nombre);
        helado.setPrecio(precio);
        helado.setFechaApertura(fechaApertura);
        helado.setFechaCaducidad(fechaCaducidad);
        helado.setSabor(sabor);
        helado.setAzucar(azucar);
        helado.setLitros(litros);
    }

    public void editarGofre(Gofre gofre, String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String topping, String tipoMasa, boolean gluten) {
        gofre.setNombre(nombre);
        gofre.setPrecio(precio);
        gofre.setFechaApertura(fechaApertura);
        gofre.setFechaCaducidad(fechaCaducidad);
        gofre.setTopping(topping);
        gofre.setTipoMasa(tipoMasa);
        gofre.setGluten(gluten);
    }

    public void editarBatido(Batido batido, String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String sabor, String tipoLeche, double litros) {
        batido.setNombre(nombre);
        batido.setPrecio(precio);
        batido.setFechaApertura(fechaApertura);
        batido.setFechaCaducidad(fechaCaducidad);
        batido.setSabor(sabor);
        batido.setTipoLeche(tipoLeche);
        batido.setLitros(litros);
    }

    public boolean existeNombre(String nombre) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        borrarProductos();

        Helado helado = null;
        Gofre gofre = null;
        Batido batido = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fichero);




    }
}
