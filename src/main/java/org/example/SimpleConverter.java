package org.example;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimpleConverter implements Converter{
    private AudioFilesLibrary fromLibrary;
    private AudioFilesLibrary toLibrary;
    private AudioFormat fromFormat;
    private AudioFormat toFormat;

    public SimpleConverter(AudioFilesLibrary fromLibrary, AudioFormat fromFormat, AudioFormat toFormat) {
        this.fromLibrary = fromLibrary;
        toLibrary = new AudioFilesLibrary();
        this.fromFormat = fromFormat;
        this.toFormat = toFormat;
    }

    public boolean convert() {
        List<AudioFile> alreadyConvertedFiles = new ArrayList<>();

        for(AudioFile af : fromLibrary) {
            if(!wasConverted(af))
                convertSingleFile(af.getFile(),af.generateFileWithAnotherExtension(toFormat));
            else
                alreadyConvertedFiles.add(af);
        }

        if(!alreadyConvertedFiles.isEmpty())
            AlertPrinter.showAlreadyConvertedFiles(alreadyConvertedFiles);


        return true;
    }

    @Override
    public boolean wasConverted(AudioFile audioFile) {
        File desiredFile = audioFile.generateFileWithAnotherExtension(toFormat);
        return desiredFile.exists();
    }

    private boolean convertSingleFile(File source, File target) {
        try {
            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(320000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
