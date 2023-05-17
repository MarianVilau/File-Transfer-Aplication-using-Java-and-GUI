package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// PreviewFrame class representing the Observer
class PreviewFrame extends JFrame {
    public PreviewFrame(MyFile file) {
        super("MV File Downloader");
        setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("MV File Downloader");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel promptLabel = new JLabel("Are you sure you want to download " + file.getFileName());
        promptLabel.setFont(new Font("Arial", Font.BOLD, 20));
        promptLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton yesButton = new JButton("Yes");
        yesButton.setPreferredSize(new Dimension(150, 75));
        yesButton.setFont(new Font("Arial", Font.BOLD, 20));
        yesButton.addActionListener(e -> {
            File fileToDownload = new File(file.getFileName());

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                fileOutputStream.write(file.getFileContent());
                fileOutputStream.close();
                dispose();
            } catch (IOException error) {
                error.printStackTrace();
            }
        });

        JButton noButton = new JButton("No");
        noButton.setPreferredSize(new Dimension(150, 75));
        noButton.setFont(new Font("Arial", Font.BOLD, 20));
        noButton.addActionListener(e -> dispose());

        JLabel fileContentLabel = new JLabel();
        fileContentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (file.getFileExtension().equalsIgnoreCase("text")) {
            fileContentLabel.setText("<html>" + new String(file.getFileContent()) + "</html>");
        } else {
            fileContentLabel.setIcon(new ImageIcon(file.getFileContent()));
        }

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(20, 0, 10, 0));
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);

        panel.add(titleLabel);
        panel.add(promptLabel);
        panel.add(fileContentLabel);
        panel.add(buttonsPanel);
        add(panel);
    }
}
