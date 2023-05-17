package org.example;

// Factory Method Pattern: FileSelectionPanelFactory
class FileSelectionPanelFactory {
    public static FileSelectionPanel createFileSelectionPanel() {
        return new FileSelectionPanel(file -> {

        });
    }
}
