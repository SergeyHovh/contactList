package com.company;


import javax.swing.*;
import java.awt.*;

public class PersonalInfo extends SampleFrame {


    PersonalInfo(CustomPanel customPanel) {
        int labelIndex = 0;
        int height = 30;

        setSize(Constants.WIDTH, Constants.HEIGHT);
        setResizable(true);
        JPanel iconPanel = new JPanel(null);
        JPanel panel = new JPanel(null);
        iconPanel.setBounds(0, 0, getWidth(), getHeight() / 5);
        panel.setBounds(0, iconPanel.getHeight(), getWidth(), 4 * getHeight() / 5);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(Main.resizeIcon(new ImageIcon(customPanel.getIconPath()), iconPanel.getHeight()));
        iconLabel.setBounds(0, 0,
                iconPanel.getWidth(), iconPanel.getHeight());
        iconPanel.add(iconLabel);

        for (String currentLabelText : customPanel.getLabels().keySet()) {
            // label
            JLabel currentLabelName = new JLabel(currentLabelText);
            currentLabelName.setBounds(0, labelIndex * height, getWidth(), height);
            currentLabelName.setBackground(new Color(232, 101, 0));
            currentLabelName.setForeground(Color.BLACK);
            currentLabelName.setOpaque(true);
            // content
            JLabel currentLabel = new JLabel(customPanel.getLabels().get(currentLabelText));
            currentLabel.setBounds(0, (labelIndex + 1) * height, getWidth(), height);
            currentLabel.setBackground(new Color(23, 132, 226));
            currentLabel.setForeground(Color.BLACK);
            currentLabel.setOpaque(true);
            // adding
            labelIndex += 2;
            panel.add(currentLabelName);
            panel.add(currentLabel);
        }
        add(panel);
        add(iconPanel);
    }
}
