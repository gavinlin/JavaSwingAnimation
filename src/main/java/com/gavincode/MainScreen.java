package com.gavincode;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MainScreen {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private SpringPanel visualPanel;
    private final JFrame window;

    public JButton expandButton;

    public MainScreen() {
        expandButton = new JButton();
        expandButton.setText("Click me to spring");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        visualPanel = new SpringPanel();
        visualPanel.setDoubleBuffered(true);
        contentPanel = new JPanel(new MigLayout("align 50% 50%"));
        contentPanel.add(expandButton,"wrap");
        contentPanel.add(visualPanel, "width 100%, height 100%");
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        window = new JFrame("App");
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setPreferredSize(new Dimension(800, 500));
        window.pack();
    }

    public void show() {
        expandButton.addActionListener(actionEvent -> {
            visualPanel.stop();
            visualPanel.start();
        });
        window.setVisible(true);
    }
}
