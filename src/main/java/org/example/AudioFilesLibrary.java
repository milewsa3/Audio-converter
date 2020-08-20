package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AudioFilesLibrary implements Iterable<AudioFile>{
    private final ObservableList<AudioFile> audioFiles;



    public AudioFilesLibrary() {
        audioFiles = FXCollections.observableArrayList();
    }

    public ObservableList<AudioFile> getMusicFiles() {
        return audioFiles;
    }

    public List<String> getMusicFilesNames() {
        List<String> list = new ArrayList<>();

        for(AudioFile mf : audioFiles) {
            list.add(mf.toString());
        }

        return list;
    }

    public void addSong(File musicFile) {
        if(contains(musicFile))
            return;

        audioFiles.add(new AudioFile(musicFile));
    }

    public boolean contains(File path) {
        for(AudioFile mf : audioFiles) {
            if(mf.getFile().compareTo(path) == 0)
                return true;
        }

        return false;
    }

    public void addAllSongs(List<File> musicFiles) {
        for(File f : musicFiles) {
            addSong(f);
        }
    }

    public void clear() {
        audioFiles.clear();
    }

    public boolean isEmpty() {
        return audioFiles == null || audioFiles.size() == 0;
    }

    public boolean remove(AudioFile itemToRemove) {
        return audioFiles.remove(itemToRemove);
    }

    @Override
    public Iterator<AudioFile> iterator() {
        return audioFiles.iterator();
    }
}
