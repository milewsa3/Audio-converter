package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SimpleFinder implements Finder{

    @Override
    public List<File> searchWithExtension(AudioFormat format, File folder, boolean recursively) {
        List<File> files = new ArrayList<>();
        if (!folder.exists())
            throw new RuntimeException("Wrong path");

        if (folder.isDirectory() && recursively) {
            File[] inside = folder.listFiles();
            for (File f : inside) {
                if(f.isDirectory())
                    files.addAll(searchWithExtension(format,f ,true));
                else {
                    if(f.toString().endsWith("." + format.getName()))
                        files.add(f);
                }
            }
        }
        else {
            if(folder.toString().endsWith("." + format.getName()))
                files.add(folder);
        }

        return files;
    }

    @Override
    public File searchForFile(File file) {
        return null;
    }
}
