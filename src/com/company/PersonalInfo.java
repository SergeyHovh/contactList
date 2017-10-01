package com.company;


import javax.swing.*;
import java.awt.*;

public class PersonalInfo extends SampleFrame {


    PersonalInfo(CustomPanel customPanel) {
        int labelIndex = 0;
        int height = 30;

        setSize(400, 400);
        setResizable(true);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, getWidth(), getHeight());

        for (String currentLabelText : customPanel.getLabels().keySet()) {

            JLabel currentLabelName = new JLabel(currentLabelText);
            currentLabelName.setBounds(0, labelIndex * height, getWidth(), height);
            currentLabelName.setBackground(new Color(232, 101, 0));
            currentLabelName.setForeground(Color.BLACK);
            currentLabelName.setOpaque(true);

            JLabel currentLabel = new JLabel(customPanel.getLabels().get(currentLabelText));
            currentLabel.setBounds(0, (labelIndex + 1) * height, getWidth(), height);
            currentLabel.setBackground(new Color(23, 132, 226));
            currentLabel.setForeground(Color.BLACK);
            currentLabel.setOpaque(true);

            labelIndex += 2;
            panel.add(currentLabelName);
            panel.add(currentLabel);
        }
        add(panel);
    }

    PersonalInfo() {
        setSize(400, 400);
    }
}
