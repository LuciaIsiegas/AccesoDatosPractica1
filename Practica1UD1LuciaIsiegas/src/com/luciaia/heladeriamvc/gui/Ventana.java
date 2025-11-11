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
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public DatePicker fechaAperturaDatePicker;
    public DatePicker fechaCaducicdadDatePicker;
    public JPanel panelCard;
    public JRadioButton batidoRadioButton;

    private JFrame frame;
    public JPanel panelHelado;
    public JPanel panelGofre;
    public JPanel panelBatido;


    public Ventana() {
        frame = new JFrame("HeladeríaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelHelado = new PanelHelado().crear();
        panelCard.add(panelHelado, "Helado");

        panelGofre = new PanelGofre().crear();
        panelCard.add(panelGofre, "Gofre");

        panelBatido = new PanelBatido().crear();
        panelCard.add(panelBatido, "Batido");


        /**
         * Sirve para cambiar de paneles
         */
        CardLayout cl = (CardLayout) (panelCard.getLayout());
        cl.show(panelCard, "Gofre");


        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);



    }

}
