package org.example;

//Singleton design pattern has been used
public class Settings {
    private static final Settings instance = new Settings();

    private AudioFormat from;
    private AudioFormat to;
    private boolean recursively;
    private boolean omitRepeatingTracks;

    private Settings() {
        //default options;
        recursively = true;
        omitRepeatingTracks = true;
    }

    public static Settings getInstance() {
        return instance;
    }

    public AudioFormat getFrom() {
        return from;
    }

    public void setFrom(AudioFormat from) {
        this.from = from;
    }

    public AudioFormat getTo() {
        return to;
    }

    public void setTo(AudioFormat to) {
        this.to = to;
    }

    public boolean isRecursively() {
        return recursively;
    }

    public void setRecursively(boolean recursively) {
        this.recursively = recursively;
    }

    public boolean isOmitRepeatingTracks() {
        return omitRepeatingTracks;
    }

    public void setOmitRepeatingTracks(boolean omitRepeatingTracks) {
        this.omitRepeatingTracks = omitRepeatingTracks;
    }
}
