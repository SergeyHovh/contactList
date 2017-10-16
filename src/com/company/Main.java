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

    static ImageIcon addIconToButton(JButton button, String iconPath) {
        try {
            ImageIcon addIcon = resizeIcon(new ImageIcon(iconPath), (int) (button.getHeight() * 0.8));
            button.setIcon(addIcon);
            return addIcon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static ImageIcon resizeIcon (ImageIcon icon, int size) {
        Image addImage = icon.getImage();
        Image newImage = addImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    static ImageIcon resizeIcon (ImageIcon icon, int width, int height) {
        Image addImage = icon.getImage();
        Image newImage = addImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}
