package org.example;

import java.io.File;

public class AudioFile {
    private String name;
    private AudioFormat audioFormat;
    private File file;

    public AudioFile(File path) {
        if(!path.exists() || path.isDirectory())
            throw new IllegalArgumentException("Wrong path of the song");

        this.file = path;

        extractName();
        extractFormat();
    }

    private void extractName() {
        String nameWithExtension = file.getName();
        name = nameWithExtension.substring(0,nameWithExtension.lastIndexOf('.'));
    }

    private void extractFormat() {
        String filePath = file.getName();
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
        return "Music file: " + name +
                "\nPath: " + file;
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

    public File generateFileWithAnotherExtension(AudioFormat af) {
        if(af == audioFormat)
            throw new IllegalArgumentException("Current file have already desired format: " + af);

        return new File(file.getParent() + "\\" + name + "." + af.getName());
    }

    public AudioFormat getAudioFormat() {
        return audioFormat;
    }
}
