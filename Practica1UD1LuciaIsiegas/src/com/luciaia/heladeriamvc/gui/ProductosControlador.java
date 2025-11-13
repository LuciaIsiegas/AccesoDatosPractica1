package com.luciaia.heladeriamvc.gui;

import com.luciaia.heladeriamvc.base.Batido;
import com.luciaia.heladeriamvc.base.Gofre;
import com.luciaia.heladeriamvc.base.Helado;
import com.luciaia.heladeriamvc.base.Producto;
import com.luciaia.heladeriamvc.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ProductosControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private ProductosModelo modelo;
    private File ultimaRutaExportada;

    public ProductosControlador(Ventana vista, ProductosModelo modelo) {
        this.modelo = modelo;
        this.vista = vista;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe fichero de configuración");
        }

        addActionListener(this);
        addWindowListener(this);
    }

    private boolean hayCamposVacios() {
        if (vista.nombreTxt.getText().isEmpty()
                || vista.precioTxt.getText().isEmpty()
                || vista.fechaAperturaDatePicker.getText().isEmpty()
                || vista.fechaCaducicdadDatePicker.getText().isEmpty()) {
            return true;
        }
        if (vista.heladoRadioButton.isSelected() && hayCamposVaciosHelado()) {
            return true;
        }
        if (vista.gofreRadioButton.isSelected() && hayCamposVaciosGofre()) {
            return true;
        }
        if (vista.batidoRadioButton.isSelected() && hayCamposVaciosBatido()) {
            return true;
        }
        return false;
    }

    private boolean hayCamposVaciosHelado() {
        return vista.panelHelado.saborTxt.getText().isEmpty()
                || vista.panelHelado.litrosHeladoTxt.getText().isEmpty();
    }

    private boolean hayCamposVaciosGofre() {
        return vista.panelGofre.toppingTxt.getText().isEmpty();
    }

    private boolean hayCamposVaciosBatido() {
        return vista.panelBatido.saborTxt.getText().isEmpty()
                || vista.panelBatido.litrosBatidoTxt.getText().isEmpty();
    }

    private String msgCamposVacios() {
        ArrayList<String> camposVacios = new ArrayList<String>();
        if (vista.nombreTxt.getText().isEmpty()) {
            camposVacios.add("Nombre");
            vista.nombreTxt.requestFocus();
        }
        if (vista.precioTxt.getText().isEmpty()) {
            camposVacios.add("Precio");
            vista.precioTxt.requestFocus();
        }
        if (vista.fechaAperturaDatePicker.getText().isEmpty()) {
            camposVacios.add("Fecha Apertura");
            vista.fechaAperturaDatePicker.requestFocus();
        }
        if (vista.fechaCaducicdadDatePicker.getText().isEmpty()) {
            camposVacios.add("Fecha Caducidad");
            vista.fechaCaducicdadDatePicker.requestFocus();
        }

        if (vista.heladoRadioButton.isSelected() && hayCamposVaciosHelado()) {
            if (vista.panelHelado.saborTxt.getText().isEmpty()) {
                camposVacios.add("Sabor");
                vista.panelHelado.saborTxt.requestFocus();
            }
            if (vista.panelHelado.litrosHeladoTxt.getText().isEmpty()) {
                camposVacios.add("Litros");
                vista.panelHelado.litrosHeladoTxt.requestFocus();
            }
        }

        if (vista.gofreRadioButton.isSelected() && hayCamposVaciosGofre()) {
            camposVacios.add("Topping");
            vista.gofreRadioButton.requestFocus();
        }

        if (vista.batidoRadioButton.isSelected() && hayCamposVaciosBatido()) {
            if (vista.panelBatido.saborTxt.getText().isEmpty()) {
                camposVacios.add("Sabor");
                vista.panelBatido.saborTxt.requestFocus();
            }
            if (vista.panelBatido.litrosBatidoTxt.getText().isEmpty()) {
                camposVacios.add("Litros");
                vista.panelBatido.litrosBatidoTxt.requestFocus();
            }
        }

        StringBuilder msg = new StringBuilder();
        for (String campo : camposVacios) {
            msg.append(campo).append("\n");
        }

        return msg.toString();
    }

    private void limpiarCampos() {
        vista.heladoRadioButton.doClick();
        vista.gofreRadioButton.setSelected(false);
        vista.batidoRadioButton.setSelected(false);
        vista.nombreTxt.setText(null);
        vista.precioTxt.setText(null);
        vista.fechaAperturaDatePicker.setText(null);
        vista.fechaCaducicdadDatePicker.setText(null);
        heladoLimpiarCampos();
        gofreLimpiarCampos();
        batidoLimpiarCampos();
    }

    private void heladoLimpiarCampos() {
        vista.panelHelado.saborTxt.setText(null);
        vista.panelHelado.conAzucarRadioButton.setSelected(true);
        vista.panelHelado.litrosHeladoTxt.setText(null);
    }

    private void gofreLimpiarCampos() {
        vista.panelGofre.toppingTxt.setText(null);
        vista.panelGofre.tipoMasaComboBox.setSelectedIndex(0);
        vista.panelGofre.conGlutenRadioButton.setSelected(true);
    }

    private void batidoLimpiarCampos() {
        vista.panelBatido.saborTxt.setText(null);
        vista.panelBatido.tipoLecheComboBox.setSelectedIndex(0);
        vista.panelBatido.litrosBatidoTxt.setText(null);
    }

    private void refrescar() {
        vista.dlmProducto.clear();
        for (Producto producto : modelo.obtenerProductos()) {
            vista.dlmProducto.addElement(producto);
        }
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("productos.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizaratosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("productos.conf"), "Datos configuracion productos");
    }

    private void addActionListener(ActionListener listener) {
        vista.menuImportar.addActionListener(listener);
        vista.menuExportar.addActionListener(listener);

        vista.heladoRadioButton.addActionListener(listener);
        vista.gofreRadioButton.addActionListener(listener);
        vista.batidoRadioButton.addActionListener(listener);

        vista.nuevoButton.addActionListener(listener);
        vista.editarButton.addActionListener(listener);
        vista.eliminarButton.addActionListener(listener);
        vista.borrarDatosButton.addActionListener(listener);
        vista.guardarButton.addActionListener(listener);
        vista.limpiarButton.addActionListener(listener);
        vista.cancelarButton.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmación("¿Desea cerrar la vetana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommmand = e.getActionCommand();
        CardLayout cl = (CardLayout) (vista.panelCard.getLayout());

        Producto producto = null;
        switch (actionCommmand) {
            case "Helado":
                batidoLimpiarCampos();
                gofreLimpiarCampos();
                cl.show(vista.panelCard, "Helado");
                break;
            case "Gofre":
                heladoLimpiarCampos();
                batidoLimpiarCampos();
                cl.show(vista.panelCard, "Gofre");
                break;
            case "Batido":
                heladoLimpiarCampos();
                gofreLimpiarCampos();
                cl.show(vista.panelCard, "Batido");
                break;
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos están vacios: \n" + msgCamposVacios(), "Campos Incompletos");
                    break;
                }

                if (modelo.existeNombre(vista.nombreTxt.getText())) {
                    Util.mensajeError("El producto " + vista.nombreTxt.getText() + " ya existe", vista.nombreTxt.getText());
                    vista.nombreTxt.setText(null);
                    break;
                }

                try {
                    if (vista.heladoRadioButton.isSelected()) {
                        modelo.altaHelado(
                                vista.nombreTxt.getText(), Double.parseDouble(vista.precioTxt.getText()),
                                vista.fechaAperturaDatePicker.getDate(), vista.fechaCaducicdadDatePicker.getDate(),
                                vista.panelHelado.saborTxt.getText(), vista.panelHelado.conAzucarRadioButton.isSelected(),
                                Double.parseDouble(vista.panelHelado.litrosHeladoTxt.getText())
                        );
                    } else if (vista.gofreRadioButton.isSelected()) {
                        modelo.altaGofre(
                                vista.nombreTxt.getText(), Double.parseDouble(vista.precioTxt.getText()),
                                vista.fechaAperturaDatePicker.getDate(), vista.fechaCaducicdadDatePicker.getDate(),
                                vista.panelGofre.toppingTxt.getText(), vista.panelGofre.tipoMasaComboBox.getSelectedItem().toString(),
                                vista.panelGofre.conGlutenRadioButton.isSelected()
                        );
                    } else if (vista.batidoRadioButton.isSelected()) {
                        modelo.altaBatido(
                                vista.nombreTxt.getText(), Double.parseDouble(vista.precioTxt.getText()),
                                vista.fechaAperturaDatePicker.getDate(), vista.fechaCaducicdadDatePicker.getDate(),
                                vista.panelBatido.saborTxt.getText(), vista.panelBatido.tipoLecheComboBox.getSelectedItem().toString(),
                                Double.parseDouble(vista.panelBatido.litrosBatidoTxt.getText())
                        );
                    }
                } catch (NumberFormatException ne) {
                    Util.mensajeError("Por favor comprueba los campos numéricos", "Formato numérico incorrecto");
                    break;
                }

                limpiarCampos();
                refrescar();
                Util.mensajeInfo("Se ha añadido un nuevo producto", "Nuevo Producto");
                vista.editarButton.setVisible(true);
                vista.eliminarButton.setVisible(true);
                vista.borrarDatosButton.setVisible(true);

                break;
            case "Editar":
                try {
                    producto = (Producto) vista.list1.getSelectedValue();
                    vista.nombreTxt.setText(producto.getNombre());
                    vista.precioTxt.setText(String.valueOf(producto.getPrecio()));
                    vista.fechaAperturaDatePicker.setDate(producto.getFechaApertura());
                    vista.fechaCaducicdadDatePicker.setDate(producto.getFechaCaducidad());

                    if (producto instanceof Helado) {
                        Helado helado = (Helado) producto;
                        vista.heladoRadioButton.doClick();
                        vista.panelHelado.saborTxt.setText(helado.getSabor());
                        if (helado.isAzucar()) {
                            vista.panelHelado.conAzucarRadioButton.setSelected(true);
                        } else {
                            vista.panelHelado.sinAzucarRadioButton.setSelected(true);
                        }
                        vista.panelHelado.litrosHeladoTxt.setText(String.valueOf(helado.getLitros()));
                    } else if (producto instanceof Gofre) {
                        Gofre gofre = (Gofre) producto;
                        vista.gofreRadioButton.doClick();
                        vista.panelGofre.toppingTxt.setText(gofre.getTopping());
                        vista.panelGofre.tipoMasaComboBox.setSelectedItem(gofre.getTipoMasa());
                        if (gofre.isGluten()) {
                            vista.panelGofre.conGlutenRadioButton.setSelected(true);
                        } else {
                            vista.panelGofre.sinGlutenRadioButton.setSelected(true);
                        }
                    } else if (producto instanceof Batido) {
                        Batido batido = (Batido) producto;
                        vista.batidoRadioButton.doClick();
                        vista.panelBatido.saborTxt.setText(batido.getSabor());
                        vista.panelBatido.tipoLecheComboBox.setSelectedItem(batido.getTipoLeche());
                        vista.panelBatido.litrosBatidoTxt.setText(String.valueOf(batido.getLitros()));
                    }
                    vista.list1.setEnabled(false);
                    vista.nombreTxt.setEditable(false);
                    vista.heladoRadioButton.setEnabled(false);
                    vista.gofreRadioButton.setEnabled(false);
                    vista.batidoRadioButton.setEnabled(false);

                    vista.guardarButton.setVisible(true);
                    vista.cancelarButton.setVisible(true);
                    vista.editarButton.setVisible(false);
                    vista.nuevoButton.setVisible(false);
                    vista.borrarDatosButton.setVisible(false);
                    vista.limpiarButton.setVisible(false);
                } catch (NullPointerException ne) {
                    Util.mensajeError("No hay ningún producto seleccionado", "Selecciona un producto");
                }

                break;
            case "Guardar":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos están vacios: \n" + msgCamposVacios(), "Campos Incompletos");
                    break;
                }

                try {
                    producto = (Producto) vista.list1.getSelectedValue();
                    if (producto instanceof Helado) {
                        modelo.editarHelado(
                                vista.nombreTxt.getText(), Double.parseDouble(vista.precioTxt.getText()),
                                vista.fechaAperturaDatePicker.getDate(), vista.fechaCaducicdadDatePicker.getDate(),
                                vista.panelHelado.saborTxt.getText(), vista.panelHelado.conAzucarRadioButton.isSelected(),
                                Double.parseDouble(vista.panelHelado.litrosHeladoTxt.getText())
                        );
                    } else if (producto instanceof Gofre) {
                        modelo.editarGofre(
                                vista.nombreTxt.getText(), Double.parseDouble(vista.precioTxt.getText()),
                                vista.fechaAperturaDatePicker.getDate(), vista.fechaCaducicdadDatePicker.getDate(),
                                vista.panelGofre.toppingTxt.getText(), vista.panelGofre.tipoMasaComboBox.getSelectedItem().toString(),
                                vista.panelGofre.conGlutenRadioButton.isSelected()
                        );
                    } else if (producto instanceof Batido) {
                        modelo.editarBatido(
                                vista.nombreTxt.getText(), Double.parseDouble(vista.precioTxt.getText()),
                                vista.fechaAperturaDatePicker.getDate(), vista.fechaCaducicdadDatePicker.getDate(),
                                vista.panelBatido.saborTxt.getText(), vista.panelBatido.tipoLecheComboBox.getSelectedItem().toString(),
                                Double.parseDouble(vista.panelBatido.litrosBatidoTxt.getText())
                        );
                    }
                } catch (NumberFormatException ne) {
                    Util.mensajeError("Por favor comprueba los campos numéricos", "Formato numérico incorrecto");
                    break;
                }

                vista.nombreTxt.setEditable(true);
                vista.heladoRadioButton.setEnabled(true);
                vista.gofreRadioButton.setEnabled(true);
                vista.batidoRadioButton.setEnabled(true);

                vista.guardarButton.setVisible(false);
                vista.cancelarButton.setVisible(false);
                vista.editarButton.setVisible(true);
                vista.nuevoButton.setVisible(true);
                vista.limpiarButton.setVisible(true);
                vista.eliminarButton.setVisible(true);
                vista.borrarDatosButton.setVisible(true);
                vista.list1.setEnabled(true);

                limpiarCampos();
                refrescar();

                break;
            case "Limpiar":
                limpiarCampos();
                break;
            case "Cancelar":
                vista.nombreTxt.setEditable(true);
                vista.heladoRadioButton.setEnabled(true);
                vista.gofreRadioButton.setEnabled(true);
                vista.batidoRadioButton.setEnabled(true);

                vista.guardarButton.setVisible(false);
                vista.cancelarButton.setVisible(false);
                vista.editarButton.setVisible(true);
                vista.nuevoButton.setVisible(true);
                vista.limpiarButton.setVisible(true);
                vista.eliminarButton.setVisible(true);
                vista.borrarDatosButton.setVisible(true);
                vista.list1.setEnabled(true);

                limpiarCampos();
                refrescar();
                break;
            case "Eliminar":
                try {
                    producto = (Producto) vista.list1.getSelectedValue();
                    int resp = Util.mensajeConfirmación("¿Desea eliminar \"" + producto.getNombre() + "\"?", "Eliminar producto");
                    if (resp == JOptionPane.OK_OPTION) {
                        modelo.eliminarProducto(producto);
                        if (!vista.nombreTxt.isEditable()) {
                            vista.cancelarButton.doClick();
                        }
                        refrescar();
                        Util.mensajeInfo("Se ha eliminado \"" + producto.getNombre() + "\"", "Producto Eliminado");
                    }


                } catch (NullPointerException ne) {
                    Util.mensajeError("No hay ningún producto seleccionado", "Selecciona un producto");
                }

                break;
            case "Borrar Datos":
                if (modelo.obtenerProductos().isEmpty()) {
                    Util.mensajeInfo("No existen elementos a borrar", "Borrar productos");
                    break;
                }
                int resp1 = Util.mensajeConfirmación("¿Desea borrar todos los datos?", "Borrar productos");
                if (resp1 == JOptionPane.OK_OPTION) {
                    modelo.borrarProductos();
                    refrescar();
                }
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada, "Archivo XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada, "Archivo XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            default:
                System.out.println("Hola");

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


}
