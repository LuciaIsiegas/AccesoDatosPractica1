package com.luciaia.heladeriamvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.luciaia.heladeriamvc.base.Producto;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
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

    public PanelGofre panelGofre;
    public PanelBatido panelBatido;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem menuImportar;
    public JMenuItem menuExportar;


    public DefaultListModel<Producto> dlmProducto;


    public Ventana() {
        setTitle("Heladería MVC");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/Icono.png")).getImage());

        heladoRadioButton.setSelected(true);
        crearMenu();
        crearPanelCard();
        botonesVisibles();

        CardLayout cl = (CardLayout) (panelCard.getLayout());
        cl.show(panelCard, "Helado");

        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        initComponents();
    }

    public void crearMenu() {
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255,203,133));
        menu = new JMenu("Archivo");
        menuImportar = new JMenuItem("Importar");
        menuImportar.setBackground(new Color(255,203,133));
        menuExportar = new JMenuItem("Exportar");
        menuExportar.setBackground(new Color(255,203,133));

        menu.add(menuImportar);
        menu.add(menuExportar);
        menuBar.add(menu);
        setJMenuBar(menuBar);
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

