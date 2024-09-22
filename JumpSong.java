// 322877754 Omer Perry

import game.Game;
import songs.SongAnalyzer;

/**
 * The Ass5Game class contains the main method for running the game.
 * It initializes a Game object, sets up the game environment, and starts the
 * game loop.
 *
 * @author 322877754 Omer Perry
 */
public class JumpSong {
    /**
     * Main method to run the game.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) throws Exception{
        SongAnalyzer song = new SongAnalyzer("songs/Steins.mid");
        Game game = new Game();
        game.initialize(song);
        game.run();
    }
}
