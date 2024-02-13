import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class accountController {

    @FXML
    Label username;
    @FXML
    Label battleHistory;
    @FXML
    Button backButton;
    @FXML
    Button passChangeButton;
    @FXML
    TextField currentPass;
    @FXML
    TextField newPass;
    @FXML
    Label passText1, passText2;
    @FXML
    Button confirmButton;
    @FXML
    Label passWarning;
    @FXML
    Label changedText;
    @FXML
    Button signOutButton;
    @FXML
    Label currentTeam;
    @FXML
    ImageView mon1view, mon2view, mon3view, mon4view, mon5view, mon6view;
    Image mon1, mon2, mon3, mon4, mon5, mon6;
    
    String space = "     ";
    public static boolean fullTeam = false;

    /**
     * Set up the proper things in the account controller.
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        //displaying username and win loss ratio
        username.setText(loginController.username);
        battleHistory.setText(BattleScreenController.winCount + "/" + BattleScreenController.lossCount);
        //hiding text boxes, buttons, and text fields user for changing password until they are needed
        currentPass.setVisible(false);
        newPass.setVisible(false);
        passText1.setVisible(false);
        passText2.setVisible(false);
        passWarning.setVisible(false);
        confirmButton.setVisible(false);
        changedText.setVisible(false);


        //display the users current team (pokemon names and pictures) if they have a full team built
        if(fullTeam == true){
            currentTeam.setText((String)newTeamController.teamList.get(0).get("name") + 
            space + (String)newTeamController.teamList.get(1).get("name") + 
            space + (String)newTeamController.teamList.get(2).get("name") + 
            space + (String)newTeamController.teamList.get(3).get("name") + 
            space + (String)newTeamController.teamList.get(4).get("name") + 
            space + (String)newTeamController.teamList.get(5).get("name"));
            mon1 = new Image((String)newTeamController.teamList.get(0).get("frontImage"));
            mon2 = new Image((String)newTeamController.teamList.get(1).get("frontImage"));
            mon3 = new Image((String)newTeamController.teamList.get(2).get("frontImage"));
            mon4 = new Image((String)newTeamController.teamList.get(3).get("frontImage"));
            mon5 = new Image((String)newTeamController.teamList.get(4).get("frontImage"));
            mon6 = new Image((String)newTeamController.teamList.get(5).get("frontImage"));
            mon1view.setImage(mon1);
            mon2view.setImage(mon2);
            mon3view.setImage(mon3);
            mon4view.setImage(mon4);
            mon5view.setImage(mon5);
            mon6view.setImage(mon6);
        }
        //if the team is not fully created
        else{
            currentTeam.setText("Finish building team first.");
        }
    
    }

    
    /**
     * go back to homepage if back arrow is pressed
     * @throws IOException
     */
    @FXML
    private void backPressed() throws IOException {
        App.setRoot("homeScreen");
    }

    /**
     * change signout status and go back to home page
     * @throws IOException
     */
    @FXML
    private void signOut() throws IOException {
        loginController.loginStatus = false;
        App.setRoot("homeScreen");
    }

    /**
     * if the user wants to change their password and presses the button to do so, display/hide all the needed stuff
     * @throws IOException
     */
    @FXML
    private void passChange() throws IOException {
        currentPass.setVisible(true);
        newPass.setVisible(true);
        passText1.setVisible(true);
        passText2.setVisible(true);
        confirmButton.setVisible(true);
        passText1.requestFocus();
        changedText.setVisible(false);
    }

    /**
     * if it is confirmed that the password is going to be changed, change the password file to match it
     * @param lineNumber - the line number of the password file that matches the accounts information
     * @param data - the new password that the user wants to change to
     * @throws IOException
     */
    public static void changePasswordFile(int lineNumber, String data) throws IOException {
        Path path = Paths.get("src/main/resources/passwordInfo.txt");
        //a list containing all the password file info
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        //change the data at the specific line number
        lines.set(lineNumber, data);
        //rewrite the entire file to match the list
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    /**
     * when the confirm button is clicked (for changing the password)
     * @throws IOException
     */
    @FXML
    private void confirm() throws IOException {
        String changedPassword = newPass.getText();
        //check if the password typed matches the current password
        if (currentPass.getText().equals(loginController.password)){
            //run the method that changes the password file
            changePasswordFile(loginController.matchingUserLine,changedPassword);
            //hide all the password changing text boxes and buttons
            currentPass.setVisible(false);
            newPass.setVisible(false);
            passText1.setVisible(false);
            passText2.setVisible(false);
            confirmButton.setVisible(false);
            passWarning.setVisible(false);
            changedText.setVisible(true);
            loginController.password = changedPassword;
        }
        //if the password typed doesn't match 
        else{
            passWarning.setVisible(true);
        }
    }
}
