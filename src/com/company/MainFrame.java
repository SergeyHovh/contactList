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

    private static Vector<CustomPanel> customPanelVector = new Vector<CustomPanel>(10, 10);

    MainFrame() {
        setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setLocationRelativeTo(null);
        upperPanel.setBounds(0, 0, Constants.WIDTH, 3 * Constants.HEIGHT / 4);
        upperPanel.setMinimumSize(new Dimension(getWidth(), getHeight()));
        lowerPanel.setBounds(0, upperPanel.getHeight(), Constants.WIDTH, Constants.HEIGHT / 4);
        lowerPanel.add(addButton);
        addButton.setSize(lowerPanel.getSize());
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person.getTextFieldHashMap().clear();
                new Person(null);
            }
        });
        scrollPane.setSize(upperPanel.getSize());
        scrollPane.getHorizontalScrollBar().setEnabled(false);
//        scrollPane.getVerticalScrollBar().setEnabled(true);
        upperPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        scrollPane.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
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

    public static void setCustomPanelVector(Vector<CustomPanel> customPanelVector) {
        MainFrame.customPanelVector = customPanelVector;
    }
}