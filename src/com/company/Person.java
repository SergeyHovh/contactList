package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

public class Person extends SampleFrame {
    private JButton submit = new JButton("Submit");
    private JButton addNewField = new JButton("Add new Field");
    private static LinkedHashMap<String, JTextField> textFieldHashMap = new LinkedHashMap<String, JTextField>();
    final JFrame f = this;

    Person() {
        textFieldHashMap.put("Name", new JTextField());
        textFieldHashMap.put("Age", new JTextField());
        textFieldHashMap.put("Date of birth", new JTextField());
        showTextFields(textFieldHashMap, this);
        submit.setBounds(0, getHeight() - 100, getWidth() / 2, 50);
        addNewField.setBounds(submit.getWidth(), getHeight() - 100, getWidth() / 2, 50);
        add(submit);
        add(addNewField);

        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewButton(MainFrame.getUpperPanel(), MainFrame.getCustomPanelVector());
                dispose();
            }
        });

        addNewField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewTextField addNewTextField = new AddNewTextField();
                dispose();
            }
        });
    }

    public static void showTextFields(HashMap<String, JTextField> map, JFrame frame) {
        int fieldIndex = 0;
        int height = 30;

        for (String textFieldName : map.keySet()) {
            JLabel currentFieldName = new JLabel(textFieldName);
            JTextField tempField = map.get(textFieldName);
            currentFieldName.setBounds(0, fieldIndex * height, frame.getWidth(), height);
            tempField.setBounds(0, (fieldIndex + 1) * height, frame.getWidth(), height);
            fieldIndex += 2;
            frame.add(currentFieldName);
            frame.add(tempField);
        }
    }

    private void addNewButton(JComponent panel, Vector<CustomPanel> customPanels) {
        final CustomPanel c = new CustomPanel();
        LinkedHashMap<String, String> labels = new LinkedHashMap<String, String>();
        for (String current : textFieldHashMap.keySet()) {
            String temp = textFieldHashMap.get(current).getText();
            labels.put(current, temp);
            textFieldHashMap.get(current).setText("");
        }
        c.setId(Math.random());
        c.setLabels(labels);
        c.getPersonalInfo().addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonalInfo p = new PersonalInfo(c);
            }
        });
        customPanels.add(c);
        updateList(panel, customPanels);
    }

    static void updateList(JComponent panel, Vector<CustomPanel> customPanelVector) {
        panel.removeAll();
        int buttonID = 0;
        for (CustomPanel current : customPanelVector) {
            JButton currentButton = current.getPersonalInfo();
            currentButton.setText(current.getLabels().get("Name"));
            // button configs
            current.setSize(panel.getWidth(), current.getHeight());
            current.setLocation(0, current.getHeight() * buttonID);
            buttonID++;
            panel.add(current);
        }
        panel.repaint();
    }

    public static LinkedHashMap<String, JTextField> getTextFieldHashMap() {
        return textFieldHashMap;
    }

    public static void setTextFieldHashMap(LinkedHashMap<String, JTextField> textFieldHashMap) {
        Person.textFieldHashMap = textFieldHashMap;
    }

    public JButton getAddNewField() {
        return addNewField;
    }
}
