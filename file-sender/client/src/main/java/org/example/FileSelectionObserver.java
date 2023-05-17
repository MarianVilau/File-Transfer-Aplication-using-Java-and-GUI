package org.example;

import java.io.File;

// Observer Pattern: FileSelectionObserver
interface FileSelectionObserver {
    void onFileSelected(File file);
}
