package model;

import java.io.File;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * MusicPlayer class to handle the playback of random background music in the application.
 * It ensures that only one instance of the player exists using the Singleton pattern.
 * 
 * @author Grant Von Hagen
 * @version 1.0
 */
public class MusicPlayer extends Application {
    private MediaPlayer mediaPlayer;
    private String[] songs = {
        "src/model/resources/Songs/C418.mp3",
        "src/model/resources/Songs/C419.mp3",
        "src/model/resources/Songs/C240.mp3",
        "src/model/resources/Songs/C241.mp3",
        "src/model/resources/Songs/C242.mp3"
    };
    private Random random = new Random();
    
    // Keep a static reference to prevent garbage collection
    private static MusicPlayer instance;
    
    
    
    /**
     * Singleton pattern to get the instance of the MusicPlayer.
     * 
     * @return the singleton instance of MusicPlayer
     */
    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        playRandomSong();
    }
    
    /**
     * Play a random song from the available list.
     */
    public void playRandomSong() {
        try {
            // Get a random song path
            String songPath = songs[random.nextInt(songs.length)];
            Media sound = new Media(new File(songPath).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Could not load music file. Continuing without music.");
        }
    }
    
    /**
     * Stops the currently playing music and disposes of resources.
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose(); // Clean up resources
        }
    }
    
    @Override
    public void stop() throws Exception {
        stopMusic();
        super.stop();
    }
    
    /**
     * Sets the volume of the currently playing music.
     * 
     * @param volume the volume level, from 0.0 (mute) to 1.0 (full volume)
     */
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume); // 0.0 to 1.0
            System.out.println(volume);
        }
    }
}
