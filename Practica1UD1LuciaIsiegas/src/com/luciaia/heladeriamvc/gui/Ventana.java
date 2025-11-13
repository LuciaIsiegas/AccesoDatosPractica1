package com.luciaia.heladeriamvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.luciaia.heladeriamvc.base.Producto;

import javax.swing.*;
import java.awt.*;

public class Ventana {
    private JPanel panel1;
    public JRadioButton heladoRadioButton;
    public JRadioButton gofreRadioButton;
    public JTextField nombreTxt;
    public JTextField precioTxt;
    public JButton nuevoButton;
    public JButton editarButton;
    public JButton eliminarButton;
    public JButton borrarDatosButton;
    public JList list1;
    public DatePicker fechaAperturaDatePicker;
    public DatePicker fechaCaducicdadDatePicker;
    public JPanel panelCard;
    public JRadioButton batidoRadioButton;
    public JButton guardarButton;
    public JButton limpiarButton;
    public JButton cancelarButton;
    public PanelHelado panelHelado;

    public JFrame frame;
    public PanelGofre panelGofre;
    public PanelBatido panelBatido;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem menuImportar;
    public JMenuItem menuExportar;


    public DefaultListModel<Producto> dlmProducto;


    public Ventana() {
        frame = new JFrame("HeladeríaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Icono.png").getImage());

        heladoRadioButton.setSelected(true);
        crearMenu();
        crearPanelCard();
        botonesVisibles();

        CardLayout cl = (CardLayout) (panelCard.getLayout());
        cl.show(panelCard, "Helado");

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    public void crearMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Archivo");
        menuImportar = new JMenuItem("Importar");
        menuExportar = new JMenuItem("Exportar");

        menu.add(menuImportar);
        menu.add(menuExportar);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    public void crearPanelCard() {
        panelHelado = new PanelHelado();
        panelHelado.conAzucarRadioButton.setSelected(true);
        panelCard.add(panelHelado.panel1, "Helado");

        panelGofre = new PanelGofre();
        panelGofre.conGlutenRadioButton.setSelected(true);
        panelCard.add(panelGofre.panel1, "Gofre");

        panelBatido = new PanelBatido();
        panelCard.add(panelBatido.panel1, "Batido");
    }

    private void botonesVisibles() {
        cancelarButton.setVisible(false);
        guardarButton.setVisible(false);
    }

    private void initComponents() {
        dlmProducto = new DefaultListModel<Producto>();
        list1.setModel(dlmProducto);
    }
}
