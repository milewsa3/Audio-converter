package org.example;

import java.io.File;
import java.util.List;

public interface Finder {
    public List<File> searchWithExtension (AudioFormat format, File folder, boolean recursively);
    public File searchForFile (File file);
}
