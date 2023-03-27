package org.calma.pig.exercices.laboSimon;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


public class SoundPlayer {
    private int         volume;
    private MidiChannel channel;

    public SoundPlayer(){
        this.volume = 600;

        try {
            Synthesizer synthetiseur = MidiSystem.getSynthesizer();
            synthetiseur.open();
            channel = synthetiseur.getChannels()[0];

            channel.programChange(79);

        }
        catch (MidiUnavailableException ex) {
            System.out.println(ex.toString());
        }
    }

    public void note_on(int note){
        channel.noteOn(note, volume);
    }
    public void note_on(int note, int intrument){
        // This selects a specific instrument from the currently selected bank of instruments.
        channel.programChange(intrument);
        channel.noteOn(note, volume);
    }

    public void note_off(int note){
        channel.noteOff(note);
    }
    public void note_off(int note, int intrument){
        // This selects a specific instrument from the currently selected bank of instruments.
        channel.programChange(intrument);
        channel.noteOff(note);
    }

    public void setInstrument(int instrument){
        channel.programChange(instrument);
    }
}

