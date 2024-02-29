package com.gavincode;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.SwingUtilities;

public class Launcher {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.setup();
            new MainScreen().show();
        });
    }
}
