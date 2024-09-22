package events;

import assests.Ball;
import assests.Block;
import game.Counter;
import game.Game;
import songs.SongAnalyzer;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.midi.Synthesizer;

public class NoteHitter implements HitListener {
    private Game game;
    private int note;
    private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	private MidiChannel[] channels;
	private static int INSTRUMENT = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
	private static int VOLUME = 80;
    
    public NoteHitter(Game game, int key) {
        this.note = key;
        this.game = game;
    }

    /**
     * Handles the hit event by removing the block from the game,
     * removing this listener from the block, updating the hitter's color,
     * and decreasing the remaining blocks count.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
            this.play();
            synth.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    private void play() throws InterruptedException
	{
			//* start playing a note
            this.channels[INSTRUMENT].noteOn(this.note, VOLUME);
            //Thread.sleep(80);
			// * stop playing a note
            this.channels[INSTRUMENT].noteOff(this.note);

			// * wait
            //stop();
			
	}

    private void stop() throws InterruptedException {
            Thread.sleep(1000);
			// * stop playing a note
            this.channels[INSTRUMENT].noteOff(this.note);
    }

}
