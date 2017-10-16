package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Vector;

public class Person extends SampleFrame {
    private static LinkedHashMap<String, JTextField> textFieldHashMap = new LinkedHashMap<String, JTextField>();
    private String picPath = "";
    private JButton photo = new JButton("Add Photo");


    Person(final CustomPanel customPanel) {
        int buttonY = getHeight() - 110;
        int buttonWidth = getWidth() / 3;
        int buttonHeight = 80;
        init();
        showTextFields(textFieldHashMap, this);
        JButton submit = new JButton("Submit");
        submit.setBounds(0, buttonY, buttonWidth, buttonHeight);

        JButton addNewField = new JButton("Add new Field");
        addNewField.setBounds(submit.getWidth(), buttonY, buttonWidth, buttonHeight);

        photo.setBounds(addNewField.getX() + addNewField.getWidth(), buttonY, buttonWidth, buttonHeight);
        add(submit);
        add(addNewField);
        add(photo);

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
                CustomPanel c = new CustomPanel();
                LinkedHashMap<String, String> labels = new LinkedHashMap<String, String>();
                for (String current : textFieldHashMap.keySet()) {
                    String temp = textFieldHashMap.get(current).getText();
                    labels.put(current, temp);
                    textFieldHashMap.get(current).setText("");
                }
                c.setLabels(labels);
                new AddNewTextField(Constants.ADD_PERSON, c);
                dispose();
            }
        });

        photo.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
                int finalResult = fc.showOpenDialog(null);
                if (finalResult == JFileChooser.APPROVE_OPTION) {
                    File pic = fc.getSelectedFile();
                    picPath = pic.getAbsolutePath();
                    photo.setIcon(Main.resizeIcon(new ImageIcon(picPath), photo.getWidth(), photo.getHeight()));
                    photo.setText("");
                }
            }
        });
    }

    private void init() {
        textFieldHashMap.put("Name", new JTextField());
        textFieldHashMap.put("Age", new JTextField());
        textFieldHashMap.put("Date of birth", new JTextField());
    }

    static void showTextFields(LinkedHashMap<String, JTextField> map, JFrame frame) {
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
        if (!picPath.equals("")) {
            c.setIcon(Main.addIconToButton(c.getPersonalInfoButton(), picPath));
            c.setIconPath(picPath);
        } else {
            c.setIcon(Main.addIconToButton(c.getPersonalInfoButton(), Constants.DEFAULT_ICON));
            c.setIconPath(Constants.DEFAULT_ICON);
        }

        c.getPersonalInfoButton().addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PersonalInfo(c);
            }
        });
        customPanels.add(c);
        updateList(panel, customPanels);
    }

    static void updateList(JComponent panel, Vector<CustomPanel> customPanelVector) {
        panel.removeAll();
        int buttonID = 0;
        saveData(customPanelVector, Constants.FILE_PATH);
        for (CustomPanel current : customPanelVector) {
            JButton currentButton = current.getPersonalInfoButton();
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

    static LinkedHashMap<String, JTextField> getTextFieldHashMap() {
        return textFieldHashMap;
    }

    public static void setTextFieldHashMap(LinkedHashMap<String, JTextField> textFieldHashMap) {
        Person.textFieldHashMap = textFieldHashMap;
    }
}
