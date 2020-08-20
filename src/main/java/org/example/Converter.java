package org.example;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.File;

public class Converter {
    private MusicFileLibrary fromLibrary;
    private MusicFileLibrary toLibrary;
    private AudioFormat fromFormat;
    private AudioFormat toFormat;

    public Converter(MusicFileLibrary fromLibrary, AudioFormat fromFormat, AudioFormat toFormat) {
        this.fromLibrary = fromLibrary;
        toLibrary = new MusicFileLibrary();
        this.fromFormat = fromFormat;
        this.toFormat = toFormat;
    }

    public boolean convert() {
        for(MusicFile mf : fromLibrary) {
            File source = mf.getFile();
            convertSingleFile(source,generateTargetFile(source));
            System.out.println(generateTargetFile(source));
        }

        return true;
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

    private File generateTargetFile(File source) {
        String name = source.getName();
        String parent = source.getParent();
        return new File(parent + "\\" + name.substring(0,name.lastIndexOf('.')) + "." + toFormat.getName());
    }
}
