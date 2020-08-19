package org.example;

public enum AudioFormat {
    MP3("mp3"),WAV("wav"),
    FLAC("flac"),AIFF("aiff"),
    AAC("aac"),WMA("wma"),
    ERROR("error");

    private String name;

    private AudioFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
