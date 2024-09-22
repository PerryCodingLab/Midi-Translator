import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
 
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
 
public class PlayMidiAudio {
 
    public static void main(String[] args) throws Exception {
 
        // Obtains the default Sequencer connected to a default device.
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.setSequence(MidiSystem.getSequence(new File("songs/AttackonTitanEnding1.mid")));
        // Opens the device, indicating that it should now acquire any
        // system resources it requires and become operational.
        sequencer.open();
        sequencer.start();
        Sequence sequence = new Sequence(Sequence.PPQ, 4);
        Track track = sequence.createTrack();
        // create a stream from a file
        InputStream is = new BufferedInputStream(new FileInputStream(new File("songs/AttackonTitanEnding1.mid")));
        byte[] s = is.readAllBytes();
        for (int i = 0; i < 100; i++) {
            System.out.print(s[i]);
            System.out.print(" ");
        }
    }
 
}