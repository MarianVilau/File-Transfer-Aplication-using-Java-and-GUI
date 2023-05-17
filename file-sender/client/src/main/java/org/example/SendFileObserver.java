package org.example;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

// Observer Pattern: SendFileObserver
class SendFileObserver implements FileSelectionObserver {
    @Override
    public void onFileSelected(File file) {
        // Perform any necessary actions when a file is selected
        // For example, update UI or start sending file in a separate thread
        try {
            // Socket communication and file sending code...
            Socket socket = new Socket("localhost", 1234);
            Thread sendFileThread = new Thread(new SendFileHandler(socket, file));
            sendFileThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
