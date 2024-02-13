import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class helpController {

    @FXML
    Button helpBackButton, moreInfoButton;
    @FXML
    ImageView arrow1, arrow2, arrow3, arrow4, arrow5;
    @FXML
    Label text1, text2, text3, text4, text5;

    private double duration = 500;
    private int count = 0;

    /**
     * Creates a new Timeline for a new fade-in animation. A KeyValue with the 
     * necessary target and end value is created (end value being full opacity of the object),
     * and a KeyFrame is created with the KeyValue passed into its constructor. 
     * The KeyFrame is added to the Timeline and the Timeline plays.
     * 
     * @param object - object to be animated
     */
    private void animate(Node object) {
        Timeline animationTimeline = new Timeline();
        KeyValue keyValue = new KeyValue(object.opacityProperty(), 1, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(duration), keyValue);
        animationTimeline.getKeyFrames().add(keyFrame);
        animationTimeline.play();
    }


    /**
     * Initialize() method is called right after all FXML elements are fully loaded.
     * Sets all the UI elements to their initial conditions to prepare them for 
     * the animation.
     */
    @FXML
    private void initialize() {
        moreInfoButton.setDisable(false);

        //Set the opacity of the arrow images to be completely transparent at the start
        arrow1.setOpacity(0);
        arrow2.setOpacity(0);
        arrow3.setOpacity(0);
        arrow4.setOpacity(0);
        arrow5.setOpacity(0);

        //Set the opacity of the labels to be completely transparent at the start
        text1.setOpacity(0);
        text2.setOpacity(0);
        text3.setOpacity(0);
        text4.setOpacity(0);
        text5.setOpacity(0);

        //Animate the first arrow and label with a fade-in animation
        animate(arrow1);
        animate(text1);
    }

    /**
     * Method is called when the user clicks on the button titled "More Info."
     * Animates a label and arrow image with a fade-in animation.
     */
    @FXML
    private void nextAnimation() {
        count++;

        if (count == 1) {
            animate(arrow2);
            animate(text2);
        }
        else if (count == 2) {
            animate(arrow3);
            animate(text3);
        }
        else if (count == 3) {
            animate(arrow4);
            animate(text4);
        }
        else if (count == 4) {
            animate(arrow5);
            animate(text5);
            moreInfoButton.setDisable(true);
        }

    }

    /**
     * Method is called when the user clicks on the "Battle Demo Video" button.
     * Switches the UI view to the battle demo view.
     * @throws IOException
     */
    @FXML
    private void switchToBattleDemo() throws IOException {
        App.setRoot("battleDemo");
    }

    /**
     * Method is called when the user clicks on the "Team Build Demo Video" button.
     * Switches the UI view to the battle demo view.
     * 
     * @throws IOException
     */
    @FXML
    private void switchToTeamBuildDemo() throws IOException {
      App.setRoot("teamBuildDemo");
    }

    /**
     * go back to homescreen when the backarrow is pressed
     * @throws IOException
     */
    @FXML
    private void previousPage() throws IOException {
        App.setRoot("homeScreen");
    }

}
