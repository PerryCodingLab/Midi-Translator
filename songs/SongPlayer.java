package songs;

import java.sql.SQLClientInfoException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class SongPlayer {
    private SongAnalyzer song;

    public SongPlayer(SongAnalyzer s) {
        this.song = s;
    }

    public Sequence create() throws Exception {
        int[] keys = song.getKeys();
        long[] timing = song.getTiming();
        int[] length = song.getLength();


        // Create a new sequence with a resolution of 480 ticks per quarter note
        Sequence sequence = new Sequence(song.getPpqType(), song.getPpq());

        // Create a track in the sequence
        Track track = sequence.createTrack();

        setTempo(track, (int)song.getbpm());

        // Add note on and note off events to the track
        for (int i = 0; i < timing.length; i++) {
            int note = keys[i];
            long tick = timing[i];
            int velocity = length[i];

            // Note on event
            track.add(createNoteOnEvent(note, tick));

            // Note off event (note duration is assumed to be 480 ticks here, adjust as
            // necessary)
            track.add(createNoteOffEvent(note, tick + velocity));
        }

        return sequence;

    }

    private static MidiEvent createNoteOnEvent(int nKey, long lTick) {
        return createNoteEvent(ShortMessage.NOTE_ON, nKey, 80, lTick);
    }

    private static MidiEvent createNoteOffEvent(int nKey, long lTick) {
        return createNoteEvent(ShortMessage.NOTE_OFF, nKey, 80, lTick);
    }

    private static MidiEvent createNoteEvent(int nCommand, int nKey, int nVelocity, long lTick) {
        ShortMessage message = new ShortMessage();
        try {
            message.setMessage(nCommand, 0, nKey, nVelocity);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return new MidiEvent(message, lTick);
    }

    private static void setTempo(Track track, int bpm) throws InvalidMidiDataException {
        int mpq = 60000000 / bpm; // Microseconds per quarter note
        byte[] data = new byte[3];
        data[0] = (byte) (mpq >> 16);
        data[1] = (byte) (mpq >> 8);
        data[2] = (byte) (mpq);
        MetaMessage message = new MetaMessage(0x51, data, 3);
        track.add(new MidiEvent(message, 0));
    }
}
