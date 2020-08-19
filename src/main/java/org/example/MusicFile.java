package org.example;

import java.io.File;

public class MusicFile {
    private String name;
    private AudioFormat audioFormat;
    private File path;

    public MusicFile(File path) {
        if(!path.exists() || path.isDirectory())
            throw new IllegalArgumentException("Wrong path of the song");

        this.path = path;

        extractName();
        extractFormat();
    }

    private void extractName() {
        String nameWithExtension = path.getName();
        name = nameWithExtension.substring(0,nameWithExtension.lastIndexOf('.'));
    }

    private void extractFormat() {
        String filePath = path.getName();
        String format = filePath.substring(filePath.lastIndexOf(".") + 1);
        audioFormat = parseFormat(format);
    }

    private AudioFormat parseFormat(String format) {
        AudioFormat af;
        try {
            af = AudioFormat.valueOf(format.toUpperCase());
        } catch (IllegalArgumentException exc) {
            af = AudioFormat.ERROR;
        }

        return af;
    }


    @Override
    public String toString() {
        return "Music file: " + name;
    }

    public String getName() {
        return name;
    }

    public File getPath() {
        return path;
    }

    public AudioFormat getAudioFormat() {
        return audioFormat;
    }
}
