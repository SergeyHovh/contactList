package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

public class Person extends SampleFrame {
    private JButton submit = new JButton("Submit");
    private JButton addNewField = new JButton("Add new Field");
    private static LinkedHashMap<String, JTextField> textFieldHashMap = new LinkedHashMap<String, JTextField>();

    Person() {
        init();
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
                new AddNewTextField();
                dispose();
            }
        });
    }

    private void init() {
        textFieldHashMap.put("Name", new JTextField());
        textFieldHashMap.put("Age", new JTextField());
        textFieldHashMap.put("Date of birth", new JTextField());
    }

    public static void showTextFields(LinkedHashMap<String, JTextField> map, JFrame frame) {
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
        saveData(customPanelVector, Main.FILE_PATH);
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

    private static void saveData(Vector<CustomPanel> customPanel, String path) {
        try {
            OutputStream file = new FileOutputStream(path);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try {
                output.writeObject(customPanel);
            } finally {
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
