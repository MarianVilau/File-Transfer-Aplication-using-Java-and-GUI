package org.example;

import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MV Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Factory Method Pattern: FileSelectionPanelFactory
        FileSelectionPanel fileSelectionPanel = FileSelectionPanelFactory.createFileSelectionPanel();

        // Observer Pattern: FileSelectionObserver
        fileSelectionPanel.addObserver(new SendFileObserver());

        frame.add(fileSelectionPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

