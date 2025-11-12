package com.luciaia.heladeriamvc.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class ProductosControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private ProductosModelo modelo;
    private File ultimaRutaExportada;

    public ProductosControlador(Ventana vista, ProductosModelo modelo) {
        modelo = modelo;
        vista = vista;
    }

    private boolean isRadioButtonSeleccionado() {
        return vista.heladoRadioButton.isSelected()
                || vista.gofreRadioButton.isSelected()
                || vista.batidoRadioButton.isSelected();
    }

    private boolean hayCamposVacios() {
        if (!isRadioButtonSeleccionado()
                || vista.nombreTxt.getText().isEmpty()
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
                || !vista.panelHelado.conAzucarRadioButton.isSelected()
                || !vista.panelHelado.sinAzucarRadioButton.isSelected()
                || vista.panelHelado.litrosHeladoTxt.getText().isEmpty();
    }

    private boolean hayCamposVaciosGofre() {
        return vista.panelGofre.toppingTxt.getText().isEmpty()
                || vista.panelGofre.tipoMasaComboBox.getSelectedItem().toString().isEmpty()
                || !vista.panelGofre.conGlutenRadioButton.isSelected()
                || !vista.panelGofre.sinGlutenRadioButton.isSelected();
    }

    private boolean hayCamposVaciosBatido() {
        return vista.panelBatido.saborTxt.getText().isEmpty()
                || vista.panelBatido.tipoLecheComboBox.getSelectedItem().toString().isEmpty()
                || vista.panelBatido.litrosBatidoTxt.getText().isEmpty();
    }

    private void limpiarCampos() {
        vista.nombreTxt.setText(null);
        vista.precioTxt.setText(null);
        vista.fechaAperturaDatePicker.setText(null);
        vista.fechaCaducicdadDatePicker.setText(null);

        vista.panelHelado.saborTxt.setText(null);
        vista.panelHelado.litrosHeladoTxt.setText(null);

        vista.panelGofre.toppingTxt.setText(null);

        vista.panelBatido.saborTxt.setText(null);
        vista.panelBatido.litrosBatidoTxt.setText(null);
    }

    private void addActionListener(ActionListener listener) {
        vista.menuImportar.addActionListener(listener);
        vista.menuExportar.addActionListener(listener);

        vista.heladoRadioButton.addActionListener(listener);
        vista.panelHelado.conAzucarRadioButton.addActionListener(listener);
        vista.panelHelado.sinAzucarRadioButton.addActionListener(listener);

        vista.gofreRadioButton.addActionListener(listener);
        vista.panelGofre.conGlutenRadioButton.addActionListener(listener);
        vista.panelGofre.sinGlutenRadioButton.addActionListener(listener);

        vista.batidoRadioButton.addActionListener(listener);

        vista.nuevoButton.addActionListener(listener);
        vista.editarButton.addActionListener(listener);
        vista.eliminarButton.addActionListener(listener);
        vista.borrarDatosButton.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener){
        vista.list1.addListSelectionListener(listener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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
