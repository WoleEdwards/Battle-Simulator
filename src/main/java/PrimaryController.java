import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PrimaryController implements Initializable {

    //declaring things needed in the homescreen page
    @FXML
    Button playButton;
    @FXML
    Button helpButton;
    @FXML
    Button loginButton;
    @FXML
    Button createTeamButton;
    @FXML
    Button accountButton;
    @FXML
    ChoiceBox<String> gameModeButton;
    @FXML
    Button useRandTeamBtn, useTeamBtn;
    @FXML
    Label teamOfSixLabel;

    //the gamemode choices
    String[] gameModeList = { "Casual", "Ranked" };
    
    public static String teamUsed = "random";

    /**
     * switch to login screen when the corresponding button is clicked
     * @throws IOException
     */
    @FXML
    private void loginPressed() throws IOException {
        App.setRoot("loginScreen");
    }

    @FXML
    private void teambuilderPressed() throws IOException {
        App.setRoot("newTeam");
    }

    /**
     * switch to helpscreen when the corresponding button is clicked
     * @throws IOException
     */
    @FXML
    private void helpPressed() throws IOException {
        App.setRoot("helpScreen");
    }

    /**
     * switch to account info screen when the corresponding button is clicked
     * @throws IOException
     */
    @FXML
    private void accountPressed() throws IOException {
        App.setRoot("accountScreen");
    }

    @FXML
    private void changeToBattle() throws IOException {
        if (teamUsed == "created") {
            int count = 0;
            for (int i = 0; i < newTeamController.teamList.size(); i++) {
                if (newTeamController.teamList.get(i) == null) {
                    teamOfSixLabel.setVisible(true);
                    break;
                }
                count++;
            }
            if (count == 6) {
                App.setRoot("BattleScreen");
            }
        } else {
            App.setRoot("BattleScreen");
        }
        // App.setRoot("BattleScreen");
    }

    /**
     * Sets up the homescreen every time it is switch to.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String path = BattleScreenController.music.get(1);
        BattleScreenController.media = new Media(new File(path).toURI().toString());
        BattleScreenController.mediaPlayer = new MediaPlayer(BattleScreenController.media);
        BattleScreenController.mediaPlayer.play();

        if (teamUsed == "random") {
            useTeamBtn.setDisable(false);
            useRandTeamBtn.setDisable(true);
        } else {
            useTeamBtn.setDisable(true);
            useRandTeamBtn.setDisable(false);
        }
        //default text in the choicebox
        gameModeButton.setValue("Choose Gamemode");
        //add the two other options (casual or ranked) 
        gameModeButton.getItems().addAll(gameModeList);
        accountButton.setVisible(false);
        
        //check if the user has already logged in or not
        if (loginController.loginStatus == true) {
            loginPageStatus();
        }
    }

    /**
     * display an account page button instead of the login button after login is completed
     */
    public void loginPageStatus() {
        if (loginController.loginStatus == true) {
            loginButton.setVisible(false);
            accountButton.setVisible(true);
            accountButton.setText(loginController.username);
        }
        else{
            loginButton.setVisible(true);
            accountButton.setVisible(false);
        }
    }

    
    @FXML
    private void useTeam() {
        useRandTeamBtn.setDisable(false);
        useTeamBtn.setDisable(true);
        teamUsed = "created";
    }

    @FXML
    private void useRandTeam() {
        useTeamBtn.setDisable(false);
        useRandTeamBtn.setDisable(true);
        teamUsed = "random";
    }
}