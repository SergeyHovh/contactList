package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Vector;

public class CustomPanel extends JPanel implements PanelActivity {

    private JButton personalInfo = new JButton();
    private JButton delete = new JButton("Delete");
    private Vector<CustomPanel> tempVector = MainFrame.getCustomPanelVector();
    private double id = 0;
    private HashMap<String, String> labels = new HashMap<String, String>();

    CustomPanel() {
        setSize(MainFrame.WIDTH, 80);
        personalInfo.setBounds(0, 0, ((int) (getWidth() * 0.75)), getHeight());
        delete.setBounds(personalInfo.getWidth(), 0, ((int) (getWidth() * 0.25)), getHeight());
        add(personalInfo);
        add(delete);
        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete();
            }
        });
    }

    @Override
    public void Delete() {
        tempVector.remove(this);
        Person.updateList(MainFrame.getUpperPanel(), tempVector);
    }

    public JButton getPersonalInfo() {
        return personalInfo;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getId() {
        return id;
    }

    public void setLabels(HashMap<String, String> labels) {
        this.labels = labels;
    }

    public HashMap<String, String> getLabels() {
        return labels;
    }
}
