package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddNewTextField extends SampleFrame {
    private JLabel l = new JLabel("New Text Field Name");
    private JTextField t = new JTextField();
    private JButton submit = new JButton("Submit");
    private JButton cancel = new JButton("Cancel");

    AddNewTextField(final int frameID, final CustomPanel panel) {
        setTitle("Add New Text Field");
        setSize(400, 150);
        setLocationRelativeTo(null);
        l.setBounds(0, 0, getWidth(), 30);
        t.setBounds(0, l.getHeight() + 5, getWidth(), 30);
        submit.setBounds(0, t.getY() + t.getHeight(), getWidth() / 2, 50);
        cancel.setBounds(submit.getWidth(), submit.getY(), getWidth() / 2, 50);
        add(l);
        add(t);
        add(submit);
        add(cancel);
        switch (frameID) {
            case Constants.ADD_PERSON:
                submit.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!t.getText().isEmpty()) {
                            Person.getTextFieldHashMap().put(t.getText(), new JTextField());
                            Person.showTextFields(Person.getTextFieldHashMap(), new Person());
                            dispose();
                        }
                    }
                });
                cancel.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Person.showTextFields(Person.getTextFieldHashMap(), new Person());
                        dispose();
                    }
                });
                break;
            case Constants.EDIT_PERSON:
                submit.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!t.getText().isEmpty()) {
                            Edit.getTextFieldLinkedHashMap().put(t.getText(), new JTextField());
                            Person.showTextFields(Edit.getTextFieldLinkedHashMap(), new Edit(panel));
                            dispose();
                        }
                    }
                });
                cancel.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Person.showTextFields(Person.getTextFieldHashMap(), new Edit(panel));
                        dispose();
                    }
                });
                break;
            default:
        }
    }
}
