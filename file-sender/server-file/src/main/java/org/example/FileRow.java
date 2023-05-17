package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// FileRow class representing the UI component for each file
class FileRow extends JPanel {
    private MyFile file;

    public FileRow(MyFile file) {
        this.file = file;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel fileNameLabel = new JLabel(file.getFileName());
        fileNameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        add(fileNameLabel);
    }
}
