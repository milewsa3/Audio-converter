package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    public static List<File> searchForExtensions(File folder, AudioFormat format, boolean recursively) {
        List<File> files = new ArrayList<>();
        if (!folder.exists())
            throw new RuntimeException("Wrong path");

        if (folder.isDirectory() && recursively) {
            File[] inside = folder.listFiles();
            for (File f : inside) {
                if(f.isDirectory())
                    files.addAll(searchForExtensions(f,format, true));
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
}
