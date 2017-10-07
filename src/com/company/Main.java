package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        new MainFrame();
        try {
            InputStream file = new FileInputStream(Constants.FILE_PATH);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                Vector<CustomPanel> customPanelVector = (Vector<CustomPanel>) input.readObject();
                MainFrame.setCustomPanelVector(customPanelVector);
                Person.updateList(MainFrame.getUpperPanel(), MainFrame.getCustomPanelVector());
            } finally {
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addIconToButton(JButton button, String iconPath) {
        try {
            ImageIcon addIcon = new ImageIcon(iconPath);
            Image addImage = addIcon.getImage();
            Image newImage = addImage.getScaledInstance((int) (button.getWidth() * 0.7), (int) (button.getHeight() * 0.7),
                    Image.SCALE_SMOOTH);
            addIcon = new ImageIcon(newImage);
            button.setIcon(addIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
