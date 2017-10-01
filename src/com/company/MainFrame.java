package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class MainFrame extends SampleFrame {
    private static JPanel upperPanel = new JPanel(null);
    private static JScrollPane scrollPane = new JScrollPane(upperPanel);
    private static JPanel lowerPanel = new JPanel(null);
    private static JButton addButton = new JButton("Add");
    final static public int WIDTH = 800;
    final static public int HEIGHT = 600;
    private static Vector<CustomPanel> customPanelVector = new Vector<CustomPanel>(10, 10);

    MainFrame() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        upperPanel.setBounds(0, 0, getWidth(), 3 * getHeight() / 4);
        upperPanel.setMinimumSize(new Dimension(getWidth(), 3 * getHeight()));
        lowerPanel.setBounds(0, upperPanel.getHeight(), getWidth(), getHeight() / 4);
        lowerPanel.add(addButton);
        addButton.setSize(lowerPanel.getSize());
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person();
            }
        });
        scrollPane.setSize(upperPanel.getSize());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setEnabled(true);
        add(scrollPane);
        add(lowerPanel);
        exit();
    }



    private void exit() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static JPanel getUpperPanel() {
        return upperPanel;
    }

    public static JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static Vector<CustomPanel> getCustomPanelVector() {
        return customPanelVector;
    }

}