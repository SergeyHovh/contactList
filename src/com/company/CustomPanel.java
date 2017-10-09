package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Vector;

public class CustomPanel extends JPanel implements PanelActivity {

    private JButton personalInfoButton = new JButton();
    private JButton deleteButton = new JButton();
    private JButton edit = new JButton();
    private Vector<CustomPanel> tempVector = MainFrame.getCustomPanelVector();
    private double id = 0;
    private HashMap<String, String> labels = new HashMap<String, String>();

    CustomPanel() {
        setSize(Constants.WIDTH, 80);
        personalInfoButton.setBounds(0, 0, (int) (getWidth()*0.8), getHeight());
        edit.setBounds(personalInfoButton.getWidth(), 0, (int) (getWidth()*0.1), getHeight());
        deleteButton.setBounds(edit.getX() + edit.getWidth(), 0, (int) (getWidth()*0.1), getHeight());
        Main.addIconToButton(edit, "icons/edit.png");
        Main.addIconToButton(deleteButton, "icons/garbage.png");
        add(personalInfoButton);
        add(deleteButton);
        add(edit);
        deleteButton.addActionListener(new AbstractAction() {
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
        Edit.getTextFieldLinkedHashMap().clear();
        new Edit(this);
    }

    public JButton getPersonalInfoButton() {
        return personalInfoButton;
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
