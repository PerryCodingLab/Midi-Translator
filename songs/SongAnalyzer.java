package songs;
import java.io.File;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import java.awt.Color;
import java.awt.color.*;
public class SongAnalyzer {
    private long[] keyTiming;
    private int[] keys;
    private int[] strength;
    private int ppq;
    private float ppqType;
    private float bpm;
    private Sequencer seq;
    //private int[] velocity;
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
    public static final java.awt.Color[] NOTE_Color = {new Color(192, 206, 34), new Color(214, 184, 0), new Color(234, 158, 0),
    new Color(252, 128, 0), new Color(255, 90, 32), new Color(255, 54, 71),
    new Color(255, 0, 96), new Color(243, 0, 125), new Color(221, 0, 150),
    new Color(196, 0, 177), new Color(159, 0, 217), new Color(71, 25, 255)};

    public SongAnalyzer(String s) throws Exception {
        int j = 0;
        Sequence sequence = MidiSystem.getSequence(new File(s));
        Sequencer tempo = MidiSystem.getSequencer();
        tempo.setSequence(sequence);
        this.seq = tempo;
        Track track = sequence.getTracks()[0];
        long[] timeforNext = new long[track.size()];
        int[] codeForKeys = new int[track.size()];
        int[] howLong = new int[track.size()];
        this.ppq = sequence.getResolution();
        this.ppqType = sequence.getDivisionType();
        this.bpm = tempo.getTempoInBPM();
        //int[] noteStrength = new int[track.size()];
        for (int i = 0; i < track.size(); i = i + 10) {
            MidiEvent event = track.get(i);
            //System.out.print("@" + event.getTick() + " ");
            MidiMessage message = event.getMessage();
            if (message instanceof ShortMessage) {
                ShortMessage sm = (ShortMessage) message;
                //System.out.println("Command:" + sm.getCommand());
                if (sm.getCommand() == NOTE_ON) {
                    int key = sm.getData1();
                    timeforNext[j] = event.getTick();
                    codeForKeys[j] = key;
                    String noteName = NOTE_NAMES[key%12];
                    howLong[j] = sm.getData2();
                    //System.out.println("Note on, " + noteName + " key=" + key + "velocity: " + howLong[j]);
                    j++;
                } else {
                    //System.out.println("Command:" + sm.getCommand());
                }
            } else {
                //System.out.println("Other message: " + message.getClass());
            }
        }
        this.keyTiming = timeforNext;
        this.keys = codeForKeys;
        this.strength = howLong;
    }

    public long[] getTiming(){
        return this.keyTiming;
    }
    public int[] getKeys(){
        return this.keys;
    }

    public int[] getLength() {
        return this.strength;
    }

    public java.awt.Color getColor(int i){
        return NOTE_Color[i];
    }

    public int getPpq() {
        return this.ppq;
    }
    public float getPpqType() {
        return this.ppqType;
    }
    public float getbpm() {
        return this.bpm;
    }
    public Sequencer getSequence(){
        return this.seq;
    }
}
