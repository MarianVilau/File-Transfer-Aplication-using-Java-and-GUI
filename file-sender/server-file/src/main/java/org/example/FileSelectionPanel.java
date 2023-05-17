package org.example;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// FileSelectionPanel class representing the Observable
class FileSelectionPanel extends JPanel {
    private ArrayList<FileSelectionListener> listeners = new ArrayList<>();

    public void addFileSelectionListener(FileSelectionListener listener) {
        listeners.add(listener);
    }

    public void addFile(MyFile file) {
        FileRow fileRow = new FileRow(file);
        fileRow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (FileSelectionListener listener : listeners) {
                    listener.fileSelected(file);
                }
            }
        });
        add(fileRow);
    }
}
