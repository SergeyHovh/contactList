package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Vector;

public class CustomPanel extends JPanel implements PanelActivity {

    private JButton personalInfoButton = new JButton();
    private JButton deleteButton = new JButton();
    private JButton edit = new JButton();
    private Vector<CustomPanel> tempVector = MainFrame.getCustomPanelVector();
    private double id = 0;
    private ImageIcon icon = null;
    private String iconPath = "";
    private HashMap<String, String> labels = new HashMap<String, String>();

    CustomPanel() {
        setSize(Constants.WIDTH, 80);
        setPreferredSize(new Dimension(Constants.WIDTH, 80));
        setMinimumSize(new Dimension(Constants.WIDTH, 80));
        personalInfoButton.setBounds(0, 0, (int) (Constants.WIDTH * 0.8), getHeight());
        edit.setBounds(personalInfoButton.getWidth(), 0, (int) (Constants.WIDTH * 0.1), getHeight());
        deleteButton.setBounds(edit.getX() + edit.getWidth(), 0, (int) (Constants.WIDTH * 0.1), getHeight());
        personalInfoButton.setMinimumSize(new Dimension((int) (Constants.WIDTH * 0.8), 80));
        edit.setMinimumSize(new Dimension((int) (Constants.WIDTH * 0.1), 80));
        deleteButton.setMinimumSize(new Dimension((int) (Constants.WIDTH * 0.1), 80));
        Main.addIconToButton(edit, "icons/edit.png");
        Main.addIconToButton(deleteButton, "icons/garbage.png");
        personalInfoButton.setHorizontalAlignment(SwingConstants.LEFT);
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

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconPath() {
        return iconPath;
    }
}
