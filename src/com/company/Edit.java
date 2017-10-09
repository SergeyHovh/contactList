package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;


public class Edit extends SampleFrame {
    private static LinkedHashMap<String, JTextField> textFieldLinkedHashMap = new LinkedHashMap<String, JTextField>();

    Edit(final CustomPanel customPanel) {
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");
        JButton addNewField = new JButton("Add New Field");
        final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        final int indexOfCustomPanel = MainFrame.getCustomPanelVector().indexOf(customPanel);
        int index = 0;
        for (String labels : customPanel.getLabels().keySet()) {
            JLabel label = new JLabel(labels);
            label.setBounds(0, index * 30, getWidth(), 30);
            JTextField jTextField = new JTextField(customPanel.getLabels().get(labels));
            jTextField.setBounds(0, (index + 1) * 30, getWidth(), 30);
            index += 2;
            textFieldLinkedHashMap.put(labels, jTextField);
            add(label);
            add(jTextField);
        }

        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getCustomPanelVector().remove(customPanel);
                for (String labels : textFieldLinkedHashMap.keySet()) {
                    map.put(labels, textFieldLinkedHashMap.get(labels).getText());
                }
                customPanel.setLabels(map);
                MainFrame.getCustomPanelVector().add(indexOfCustomPanel, customPanel);
                Person.updateList(MainFrame.getUpperPanel(), MainFrame.getCustomPanelVector());
                dispose();
            }
        });

        addNewField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewTextField(Constants.EDIT_PERSON, customPanel);
                dispose();
            }
        });

        cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        submit.setBounds(0, getHeight() - 130, getWidth() / 2, 50);
        addNewField.setBounds(submit.getWidth(), submit.getY(), getWidth() / 2, 50);
        cancel.setBounds(0, getHeight() - 80, getWidth(), 50);
        add(submit);
        add(cancel);
        add(addNewField);
    }

    static LinkedHashMap<String, JTextField> getTextFieldLinkedHashMap() {
        return textFieldLinkedHashMap;
    }

    public static void setTextFieldLinkedHashMap(LinkedHashMap<String, JTextField> textFieldLinkedHashMap) {
        Edit.textFieldLinkedHashMap = textFieldLinkedHashMap;
    }
}