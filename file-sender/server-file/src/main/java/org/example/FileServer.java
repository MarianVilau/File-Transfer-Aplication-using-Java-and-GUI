package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// FileServer class implementing the Observer pattern
class FileServer implements FileSelectionListener {
    private ArrayList<MyFile> myFiles = new ArrayList<>();
    private FileSelectionPanel fileSelectionPanel;

    public void start() {
        JFrame frame = new JFrame("MV Server");
        frame.setSize(400, 400);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileSelectionPanel = new FileSelectionPanel();
        fileSelectionPanel.addFileSelectionListener(this);

        JScrollPane scrollPane = new JScrollPane(fileSelectionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel titleLabel = new JLabel("MV File Receiver");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(titleLabel);
        frame.add(scrollPane);
        frame.setVisible(true);

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                int fileNameLength = dataInputStream.readInt();

                if (fileNameLength > 0) {
                    byte[] fileNameBytes = new byte[fileNameLength];
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    String fileName = new String(fileNameBytes);

                    int fileContentLength = dataInputStream.readInt();

                    byte[] fileContentBytes = new byte[fileContentLength];
                    dataInputStream.readFully(fileContentBytes, 0, fileContentLength);

                    MyFile myFile = new MyFile(fileName, fileContentBytes);
                    myFiles.add(myFile);
                    fileSelectionPanel.addFile(myFile);
                    fileSelectionPanel.revalidate();
                    fileSelectionPanel.repaint();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fileSelected(MyFile file) {
        JFrame previewFrame = new PreviewFrame(file);
        previewFrame.setVisible(true);
    }
}
