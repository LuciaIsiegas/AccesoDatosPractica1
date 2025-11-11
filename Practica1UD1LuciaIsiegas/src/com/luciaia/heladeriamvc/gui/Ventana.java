package com.luciaia.heladeriamvc.gui;

import com.github.lgooddatepicker.components.DatePicker;

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
    public JList list1;
    public DatePicker fechaAperturaDatePicker;
    public DatePicker fechaCaducicdadDatePicker;
    public JPanel panelCard;
    public JRadioButton batidoRadioButton;
    public PanelHelado panelHelado;

    private JFrame frame;
    public PanelGofre panelGofre;
    public PanelBatido panelBatido;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem menuImportar;
    public JMenuItem menuExportar;
    private JButton borrarDatosButton;


    public Ventana() {
        frame = new JFrame("HeladeríaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("imageIcon.png").getImage());

        crearMenu();
        crearPanelCard();

        /**
         * Sirve para cambiar de paneles
         */
        CardLayout cl = (CardLayout) (panelCard.getLayout());
        cl.show(panelCard, "Batido");

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
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
        panelCard.add(panelHelado.panel1, "Helado");

        panelGofre = new PanelGofre();
        panelCard.add(panelGofre.panel1, "Gofre");

        panelBatido = new PanelBatido();
        panelCard.add(panelBatido.panel1, "Batido");
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
