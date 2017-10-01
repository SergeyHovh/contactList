package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddNewTextField extends SampleFrame {
    JLabel l = new JLabel("New Text Field Name");
    JTextField t = new JTextField();
    JButton submit = new JButton("Submit");
    JButton cancel = new JButton("Cancel");

    AddNewTextField() {
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
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!t.getText().isEmpty()) {
                    Person.getTextFieldHashMap().put(t.getText(), new JTextField());
                    Person.showTextFields(Person.getTextFieldHashMap(), new Person());
                    dispose();
                } else {

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
    }
}
