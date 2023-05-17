package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class FileSelectionPanel extends JPanel {
    private File fileToSend;
    private final List<FileSelectionObserver> observers;

    public FileSelectionPanel(FileSelectionObserver observer) {
        observers = new ArrayList<>();
        observers.add(observer);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("MV File Sender");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fileNameLabel = new JLabel("Choose a file to send.");
        fileNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fileNameLabel.setBorder(new EmptyBorder(50, 0, 0, 0));
        fileNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(75, 0, 10, 0));

        JButton sendButton = new JButton("Send File");
        sendButton.setPreferredSize(new Dimension(150, 75));
        sendButton.setFont(new Font("Arial", Font.BOLD, 20));
        sendButton.addActionListener(new SendFileHandler());

        JButton chooseButton = new JButton("Choose File");
        chooseButton.setPreferredSize(new Dimension(150, 75));
        chooseButton.setFont(new Font("Arial", Font.BOLD, 20));
        chooseButton.addActionListener(new ChooseFileHandler());

        buttonPanel.add(sendButton);
        buttonPanel.add(chooseButton);

        add(title);
        add(fileNameLabel);
        add(buttonPanel);
    }

    public void addObserver(FileSelectionObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (FileSelectionObserver observer : observers) {
            observer.onFileSelected(fileToSend);
        }
    }

    private class ChooseFileHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to send");

            if (fileChooser.showOpenDialog(FileSelectionPanel.this) == JFileChooser.APPROVE_OPTION) {
                fileToSend = fileChooser.getSelectedFile();
                JLabel fileNameLabel = (JLabel) FileSelectionPanel.this.getComponent(1);
                fileNameLabel.setText("The file you want to send is: " + fileToSend.getName());
                notifyObservers();
            }
        }
    }

    private class SendFileHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (fileToSend == null) {
                JLabel fileNameLabel = (JLabel) FileSelectionPanel.this.getComponent(1);
                fileNameLabel.setText("Please choose a file first.");
            } else {
                try {
                    byte[] fileNameBytes;
                    byte[] fileContentBytes;
                    try (FileInputStream fileInputStream = new FileInputStream(fileToSend.getAbsolutePath())) {
                        Socket socket = new Socket("localhost", 1234);
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                        String fileName = fileToSend.getName();
                        fileNameBytes = fileName.getBytes();
                        fileContentBytes = new byte[(int) fileToSend.length()];
                        fileInputStream.read(fileContentBytes);

                        dataOutputStream.writeInt(fileNameBytes.length);
                        dataOutputStream.write(fileNameBytes);
                        dataOutputStream.writeInt(fileContentBytes.length);
                        dataOutputStream.write(fileContentBytes);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
