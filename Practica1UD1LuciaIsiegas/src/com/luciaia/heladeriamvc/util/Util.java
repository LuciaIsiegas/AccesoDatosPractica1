package com.luciaia.heladeriamvc.util;

import javax.swing.*;

public class Util {
    public void mensajeError(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void mensajeConfirmación(String msg, String titulo) {
        JOptionPane.showConfirmDialog(null, msg, titulo, JOptionPane.YES_NO_OPTION);
    }
}
