package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Vector;

public class CustomPanel extends JPanel implements PanelActivity {

    private JButton personalInfo = new JButton();
    private JButton delete = new JButton();
    private JButton edit = new JButton();
    private Vector<CustomPanel> tempVector = MainFrame.getCustomPanelVector();
    private double id = 0;
    private HashMap<String, String> labels = new HashMap<String, String>();

    CustomPanel() {
        setSize(MainFrame.WIDTH, 80);
        personalInfo.setBounds(0, 0, ((int) (getWidth() * 0.8)), getHeight());
        edit.setBounds(personalInfo.getWidth(), 0, ((int) (getWidth() * 0.1)), getHeight());
        delete.setBounds(personalInfo.getWidth() + edit.getWidth(), 0, ((int) (getWidth() * 0.1)), getHeight());
        Main.addIconToButton(edit, "icons/edit.png");
        Main.addIconToButton(delete, "icons/garbage.png");
        add(personalInfo);
        add(delete);
        add(edit);
        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete();
            }
        });
        edit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit();
            }
        });
    }

    @Override
    public void Delete() {
        tempVector.remove(this);
        Person.updateList(MainFrame.getUpperPanel(), tempVector);
    }

    @Override
    public void Edit() {
        new Edit(this);
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
