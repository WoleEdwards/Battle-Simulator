import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class BattleDemoController {


    @FXML
    MediaView battleMediaView;

    private MediaPlayer mediaPlayer;
    private Media media;


    /**
     * Method is called immediately after all the FXML elements have been loaded.
     * Plays the demo video showing the concepts of battling.
     */
    @FXML
    private void initialize() {

        // Get the path of the mp4 file
        String path = "src/main/resources/videos/battleRecording.mp4";

        // Instantiate Media object and add the file path to the constructor
        media = new Media(new File(path).toURI().toString());

        // Add media to mediaPlayer and play the media
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        // Add the mediaPlayer to the MediaView to enable the user to see video on screen
        battleMediaView.setMediaPlayer(mediaPlayer);

        // Loop through the video clip (replay clip when clip ends)
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
            
        });
    }

    /**
     * When "Back" button is pressed, stop playing the video clip and
     * switch the UI view to the help screen.
     * 
     * @throws IOException
     */
    @FXML
    private void switchToHelpScreen() throws IOException{
        mediaPlayer.stop();
        App.setRoot("helpScreen");
    }
    
}
