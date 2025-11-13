package com.luciaia.heladeriamvc.gui;

import com.luciaia.heladeriamvc.base.Batido;
import com.luciaia.heladeriamvc.base.Gofre;
import com.luciaia.heladeriamvc.base.Helado;
import com.luciaia.heladeriamvc.base.Producto;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class ProductosModelo {
    public static final String HELADO = "Helado";
    public static final String GOFRE = "Gofre";
    public static final String BATIDO = "Batido";
    private ArrayList<Producto> listaProductos;

    public ProductosModelo() {
        listaProductos = new ArrayList<Producto>();
    }

    public ArrayList<Producto> obtenerProductos() {
        //       UUID.randomUUID().toString()
        return listaProductos;
    }

    public void borrarProductos() {
        listaProductos.clear();
    }

    public void eliminarProducto(Producto producto) {
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

    public Producto buscarProducto(String nombre) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public void editarHelado(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String sabor, boolean azucar, double litros) {
        Helado helado = (Helado) buscarProducto(nombre);
        helado.setPrecio(precio);
        helado.setFechaApertura(fechaApertura);
        helado.setFechaCaducidad(fechaCaducidad);
        helado.setSabor(sabor);
        helado.setAzucar(azucar);
        helado.setLitros(litros);
    }

    public void editarGofre(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String topping, String tipoMasa, boolean gluten) {
        Gofre gofre = (Gofre) buscarProducto(nombre);
        gofre.setPrecio(precio);
        gofre.setFechaApertura(fechaApertura);
        gofre.setFechaCaducidad(fechaCaducidad);
        gofre.setTopping(topping);
        gofre.setTipoMasa(tipoMasa);
        gofre.setGluten(gluten);
    }

    public void editarBatido(String nombre, double precio, LocalDate fechaApertura, LocalDate fechaCaducidad, String sabor, String tipoLeche, double litros) {
        Batido batido = (Batido) buscarProducto(nombre);
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

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document document = dom.createDocument(null, "xml", null);

        Element raiz = document.createElement("Productos");
        document.getDocumentElement().appendChild(raiz);

        Element nodoProducto = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Producto producto : listaProductos) {
            if (producto instanceof Helado) {
                nodoProducto = document.createElement(HELADO);
            } else if (producto instanceof Gofre) {
                nodoProducto = document.createElement(GOFRE);
            } else {
                nodoProducto = document.createElement(BATIDO);
            }

            nodoDatos = document.createElement("Nombre");
            nodoProducto.appendChild(nodoDatos);
            texto = document.createTextNode(producto.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("Precio");
            nodoProducto.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(producto.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("FechaApertura");
            nodoProducto.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(producto.getFechaApertura()));
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("FechaCaducidad");
            nodoProducto.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(producto.getFechaCaducidad()));
            nodoDatos.appendChild(texto);

            if (producto instanceof Helado) {
                nodoDatos = document.createElement("Sabor");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(((Helado) producto).getSabor());
                nodoDatos.appendChild(texto);

                nodoDatos = document.createElement("Azucar");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(String.valueOf(((Helado) producto).isAzucar()));
                nodoDatos.appendChild(texto);

                nodoDatos = document.createElement("Litros");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(String.valueOf(((Helado) producto).getLitros()));
                nodoDatos.appendChild(texto);

            } else if (producto instanceof Gofre) {
                nodoDatos = document.createElement("Topping");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(((Gofre) producto).getTopping());
                nodoDatos.appendChild(texto);

                nodoDatos = document.createElement("TipoMasa");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(((Gofre) producto).getTipoMasa());
                nodoDatos.appendChild(texto);

                nodoDatos = document.createElement("Gluten");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(String.valueOf(((Gofre) producto).isGluten()));
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos = document.createElement("Sabor");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(((Batido) producto).getSabor());
                nodoDatos.appendChild(texto);

                nodoDatos = document.createElement("TipoLeche");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(((Batido) producto).getTipoLeche());
                nodoDatos.appendChild(texto);

                nodoDatos = document.createElement("Litros");
                nodoProducto.appendChild(nodoDatos);
                texto = document.createTextNode(String.valueOf(((Batido) producto).getLitros()));
                nodoDatos.appendChild(texto);
            }
        }

        Source source = new DOMSource(document);
        Result result = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        borrarProductos();
        Helado helado = null;
        Gofre gofre = null;
        Batido batido = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fichero);

        NodeList listaElementos = document.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoProducto = (Element) listaElementos.item(i);

            if (nodoProducto.getTagName().equals(HELADO)) {
                helado = new Helado();
                helado.setNombre(nodoProducto.getChildNodes().item(0).getTextContent());
                helado.setPrecio(Double.parseDouble(nodoProducto.getChildNodes().item(1).getTextContent()));
                helado.setFechaApertura(LocalDate.parse(nodoProducto.getChildNodes().item(2).getTextContent()));
                helado.setFechaCaducidad(LocalDate.parse(nodoProducto.getChildNodes().item(3).getTextContent()));
                helado.setSabor(nodoProducto.getChildNodes().item(4).getTextContent());
                helado.setAzucar(Boolean.parseBoolean(nodoProducto.getChildNodes().item(5).getTextContent()));
                helado.setLitros(Double.parseDouble(nodoProducto.getChildNodes().item(6).getTextContent()));

                listaProductos.add(helado);
            } else if (nodoProducto.getTagName().equals(GOFRE)) {
                gofre = new Gofre();
                gofre.setNombre(nodoProducto.getChildNodes().item(0).getTextContent());
                gofre.setPrecio(Double.parseDouble(nodoProducto.getChildNodes().item(1).getTextContent()));
                gofre.setFechaApertura(LocalDate.parse(nodoProducto.getChildNodes().item(2).getTextContent()));
                gofre.setFechaCaducidad(LocalDate.parse(nodoProducto.getChildNodes().item(3).getTextContent()));
                gofre.setTopping(nodoProducto.getChildNodes().item(4).getTextContent());
                gofre.setTipoMasa(nodoProducto.getChildNodes().item(5).getTextContent());
                gofre.setGluten(Boolean.parseBoolean(nodoProducto.getChildNodes().item(6).getTextContent()));

                listaProductos.add(gofre);
            } else if (nodoProducto.getTagName().equals(BATIDO)) {
                batido = new Batido();
                batido.setNombre(nodoProducto.getChildNodes().item(0).getTextContent());
                batido.setPrecio(Double.parseDouble(nodoProducto.getChildNodes().item(1).getTextContent()));
                batido.setFechaApertura(LocalDate.parse(nodoProducto.getChildNodes().item(2).getTextContent()));
                batido.setFechaCaducidad(LocalDate.parse(nodoProducto.getChildNodes().item(3).getTextContent()));
                batido.setSabor(nodoProducto.getChildNodes().item(4).getTextContent());
                batido.setTipoLeche(nodoProducto.getChildNodes().item(5).getTextContent());
                batido.setLitros(Double.parseDouble(nodoProducto.getChildNodes().item(6).getTextContent()));

                listaProductos.add(batido);
            }
        }


    }
}
