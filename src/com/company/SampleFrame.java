package com.company;

import javax.swing.*;
import java.awt.*;

public class SampleFrame extends JFrame {
    SampleFrame() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setIconImage(tk.createImage(Constants.DEFAULT_ICON));
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }
}
