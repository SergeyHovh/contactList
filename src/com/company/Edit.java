package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.Vector;

public class Edit extends SampleFrame {
    Edit(final CustomPanel customPanel) {
        final int indexOfCustomPanel = MainFrame.getCustomPanelVector().indexOf(customPanel);
        MainFrame.getCustomPanelVector().remove(customPanel);
        int index = 0;
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");
        final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        final LinkedHashMap<String, JTextField> textFieldLinkedHashMap = new LinkedHashMap<String, JTextField>();
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
                for(String labels : textFieldLinkedHashMap.keySet()) {
                    map.put(labels, textFieldLinkedHashMap.get(labels).getText());
                }
                customPanel.setLabels(map);
                MainFrame.getCustomPanelVector().add(indexOfCustomPanel, customPanel);
                Person.updateList(MainFrame.getUpperPanel(), MainFrame.getCustomPanelVector());
                dispose();
            }
        });

        cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        submit.setBounds(0, getHeight() - 100, getWidth() / 2, 50);
        cancel.setBounds(submit.getWidth(), getHeight() - 100, getWidth() / 2, 50);
        add(submit);
        add(cancel);
    }
}
