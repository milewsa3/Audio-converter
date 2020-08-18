package org.example;

import java.io.File;

public class MusicFile {
    private File path;
    private String name;
    private AudioFormat audioFormat;

    public MusicFile(File path) {
        if(!path.exists() || path.isDirectory())
            throw new IllegalArgumentException("Wrong path of the song");

        this.path = path;

        extractName();
        extractFormat();
    }

    private void extractFormat() {
        String filePath = path.getName();
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
        audioFormat = parseExtension(extension);
    }

    private AudioFormat parseExtension(String extension) {
        for(AudioFormat af : AudioFormat.values()) {
            if(af.getName().compareTo(extension) == 0)
                return af;
        }

        throw new IllegalArgumentException("Wrong extension of the file");
    }

    private void extractName() {
        String nameWithExtension = path.getName();
        name = nameWithExtension.substring(0,nameWithExtension.lastIndexOf('.'));
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public File getPath() {
        return path;
    }
}
