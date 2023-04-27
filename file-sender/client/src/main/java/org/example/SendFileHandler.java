package org.example;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class SendFileHandler implements Runnable {
    private final Socket socket;
    private final File fileToSend;

    public SendFileHandler(Socket socket, File fileToSend) {
        this.socket = socket;
        this.fileToSend = fileToSend;
    }

    @Override
    public void run() {
        try {
            DataOutputStream dataOutputStream;
            byte[] fileNameBytes;
            byte[] fileContentBytes;
            try (FileInputStream fileInputStream = new FileInputStream(fileToSend.getAbsolutePath())) {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                String fileName = fileToSend.getName();
                fileNameBytes = fileName.getBytes();
                fileContentBytes = new byte[(int) fileToSend.length()];
                fileInputStream.read(fileContentBytes);
            }

            dataOutputStream.writeInt(fileNameBytes.length);
            dataOutputStream.write(fileNameBytes);
            dataOutputStream.writeInt(fileContentBytes.length);
            dataOutputStream.write(fileContentBytes);

            System.out.println("File sent successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
