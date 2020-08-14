package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MusicFileLibrary {
    private List<MusicFile> musicFiles;


    public MusicFileLibrary(List<File> musicFiles) {
        this();

        addAllSongs(musicFiles);
    }

    public MusicFileLibrary() {
        musicFiles = new ArrayList<>();
    }

    public List<MusicFile> getMusicFiles() {
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
        musicFiles.add(new MusicFile(musicFile));
    }

    public void addAllSongs(List<File> musicFiles) {
        for(File f : musicFiles) {
            addSong(f);
        }
    }

    public void clear() {
        musicFiles.clear();
    }
}
