package org.example;

public class Settings {
    private AudioFormat from;
    private AudioFormat to;
    private boolean recursively;
    private boolean omitRepeatingTracks;

    public Settings() {
        //default options
        from = AudioFormat.FLAC;
        to = AudioFormat.MP3;
        recursively = true;
        omitRepeatingTracks = true;
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
