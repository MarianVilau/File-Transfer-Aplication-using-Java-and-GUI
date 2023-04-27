package org.example;

import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MV Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new FileSelectionPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
