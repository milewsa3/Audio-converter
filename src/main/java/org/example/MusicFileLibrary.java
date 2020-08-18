package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MusicFileLibrary {
    private final ObservableList<MusicFile> musicFiles;



    public MusicFileLibrary() {
        musicFiles = FXCollections.observableArrayList();
    }

    public ObservableList<MusicFile> getMusicFiles() {
        return musicFiles;
    }

    public List<String> getMusicFilesNames() {
        List<String> list = new ArrayList<>();

        for(MusicFile mf : musicFiles) {
            list.add(mf.toString());
        }

        return list;
    }

    public void addSong(File musicFile) {
        if(contains(musicFile))
            return;

        musicFiles.add(new MusicFile(musicFile));
    }

    private boolean contains(File path) {
        for(MusicFile mf : musicFiles) {
            if(mf.getPath().compareTo(path) == 0)
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
        musicFiles.clear();
    }

    public boolean isEmpty() {
        return musicFiles == null || musicFiles.size() == 0;
    }
}
