import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class newTeamController {
    @FXML
    HBox teamImages, monsterNames;
    // Sets FX id's for the HBoxes to access the children
    @FXML
    Button backBtn, mon1, mon2, mon3, mon4, mon5, mon6;
    // Sets FX id's for the Buttons to access and edit their values

    public static List<String> allMons = Arrays.asList("Alakazam", "Blastoise", "Breloom", "Charizard", "Crustle",
            "Electivire", "Froslass", "Gardevoir", "Gengar", "Grimmsnarl", "Lucario", "Mamoswine", "Pidgeot",
            "Sharpedo", "Snorlax", "Steelix", "Turtonator", "Tyrantrum", "Venusaur", "Vikavolt");
    // Creates a public List with all the pocket monsters currently in the game
    public static List<HashMap<String, Object>> teamList = Arrays.asList(null, null, null, null, null, null);
    // Creates a public List of HashMaps that stores the current team of the user. Originally set as null replace members rather than add
    public static Button buttonPressed;
    // Creates a public Button as the button that was pressed prior to switching FXML to remeber what position in the team is able to be edited

    /**
     * Runs while the screen is being created.
     * Updates the images and sets buttons enabled
     */
    @FXML
    private void initialize() {
        if (buttonPressed != null) {
            // Only runs if this is not the first time opened when ran
            if (buttonPressed.getId().equals(mon1.getId())) {
                // Checks if the last button pressed was button 1
                mon1.setText((String) NewMonController.pMon.get("name"));
                mon2.setDisable(false);
                // Setting the text of button as the pocket monster name and enables the next button to be pressed
                teamList.set(0, NewMonController.pMon);
                // Replaces the first slot in the team with the chosen pocket monster
                ImageView thisImageView = (ImageView) teamImages.getChildren().get(0);
                Image pokeImage = new Image(((String) NewMonController.pMon.get("frontImage")));
                thisImageView.setImage(pokeImage);
                // Sets the image of the chosen pocket monster
            } 
            else if (buttonPressed.getId().equals(mon2.getId())) {
                // Checks if the last button pressed was button 2
                mon2.setText((String) NewMonController.pMon.get("name"));
                mon3.setDisable(false);
                // Setting the text of button as the pocket monster name and enables the next button to be pressed
                teamList.set(1, NewMonController.pMon);
                // Replaces the second slot in the team with the chosen pocket monster
                ImageView thisImageView = (ImageView) teamImages.getChildren().get(1);
                Image pokeImage = new Image(((String) NewMonController.pMon.get("frontImage")));
                thisImageView.setImage(pokeImage);
                // Sets the image of the chosen pocket monster
            } 
            else if (buttonPressed.getId().equals(mon3.getId())) {
                // Checks if the last button pressed was button 3
                mon3.setText((String) NewMonController.pMon.get("name"));
                mon4.setDisable(false);
                // Setting the text of button as the pocket monster name and enables the next button to be pressed
                teamList.set(2, NewMonController.pMon);
                // Replaces the third slot in the team with the chosen pocket monster
                ImageView thisImageView = (ImageView) teamImages.getChildren().get(2);
                Image pokeImage = new Image(((String) NewMonController.pMon.get("frontImage")));
                thisImageView.setImage(pokeImage);
                // Sets the image of the chosen pocket monster
            } 
            else if (buttonPressed.getId().equals(mon4.getId())) {
                // Checks if the last button pressed was button 4
                mon4.setText((String) NewMonController.pMon.get("name"));
                mon5.setDisable(false);
                // Setting the text of button as the pocket monster name and enables the next button to be pressed
                teamList.set(3, NewMonController.pMon);
                // Replaces the fourth slot in the team with the chosen pocket monster
                ImageView thisImageView = (ImageView) teamImages.getChildren().get(3);
                Image pokeImage = new Image(((String) NewMonController.pMon.get("frontImage")));
                thisImageView.setImage(pokeImage);
                // Sets the image of the chosen pocket monster
            } 
            else if (buttonPressed.getId().equals(mon5.getId())) {
                // Checks if the last button pressed was button 5
                mon5.setText((String) NewMonController.pMon.get("name"));
                mon6.setDisable(false);
                // Setting the text of button as the pocket monster name and enables the next button to be pressed
                teamList.set(4, NewMonController.pMon);
                // Replaces the fifth slot in the team with the chosen pocket monster
                ImageView thisImageView = (ImageView) teamImages.getChildren().get(4);
                Image pokeImage = new Image(((String) NewMonController.pMon.get("frontImage")));
                thisImageView.setImage(pokeImage);
                // Sets the image of the chosen pocket monster
            } 
            else if (buttonPressed.getId().equals(mon6.getId())) {
                // Checks if the last button pressed was button 6
                mon6.setText((String) NewMonController.pMon.get("name"));
                // Sets the text of the button as the pocket monster name
                teamList.set(5, NewMonController.pMon);
                // Replaces the sixth slot in the team with the chosen pocket monster
                ImageView thisImageView = (ImageView) teamImages.getChildren().get(5);
                Image pokeImage = new Image(((String) NewMonController.pMon.get("frontImage")));
                thisImageView.setImage(pokeImage);
                // Sets the image of the chosen pocket monster
                accountController.fullTeam = true;
                // Tells the accountController that a full team has been created to display team
            }
            int count = 0;
            // Creates a counter used to take positions in the teamList
            for (Node child : monsterNames.getChildren()) {
            // Runs for the amount of children in monsterNames
                if (teamList.get(count) == null) {
                // Checks if the position has no member, breaking the loop if so
                    break;
                }
                Button temp = (Button) child;
                temp.setText((String) teamList.get(count).get("name"));
                temp.setDisable(false);
                ImageView tempView = (ImageView) teamImages.getChildren().get(count);
                Image tempImage = new Image((String) teamList.get(count).get("frontImage"));
                tempView.setImage(tempImage);
                // Sets the image and text of all the buttons
                count++;
                // Increases the counter
            }  
        }
    }

    /**
     * The randTeam method runs with the purpose to create a random team consisted of 6 members for the given player
     * 
     * @param player ; Creates a team for that specific member
     * @return List<HashMap<String, Object>> ; Returns a list of hashmaps acting as the team
     */
    public static List<HashMap<String, Object>> randTeam(String player) {
        List<HashMap<String, Object>> team = Arrays.asList(null, null, null, null, null, null);
        // Creates an empty team
        boolean duplicateMon = false;
        // Creates a boolean used to check if the pocket monster is already used
        for (int i = 0; i <= 5; i++) {
        // For loop that runs 6 times
            duplicateMon = false;
            Random rand = new Random();
            int randomitem = rand.nextInt(allMons.size());
            String randomElement = allMons.get(randomitem);
            // Takes a random member form the list
 
            if (player.equalsIgnoreCase("user")) {
            // Creates a team for the user
                for (int j = 0; j < team.size(); j++) {
                // For loop that runs through the team being created
                    if (team.get(j) != null) {
                    // Checks if there is a pocket monster in this position
                        if (((String) team.get(j).get("name")).equalsIgnoreCase(randomElement)) {
                        // If this pocket monster has already been used regenerates a random member
                            duplicateMon = true;
                            i -= 1;
                            break;

                        }
                    }
                }
            } 
            else if (player.equalsIgnoreCase("bot")) {
                // Creates a team for the bot
                for (int j = 0; j < team.size(); j++) {
                    // For loop that runs through the team being created
                    if (team.get(j) != null) {
                    // Checks if there is a pocket monster in this position
                        if (((String) team.get(j).get("name")).equalsIgnoreCase(randomElement)) {
                        // If this pocket monster has already been used regenerates a random member
                            duplicateMon = true;
                            i -= 1;
                            break;

                        }
                    }
                }
                if (duplicateMon == false) {
                // Makes sure that there is no duplicate on the team
                    for (int j = 0; j < BattleScreenController.monList.size(); j++) {
                    // For loop that runs through the created user team
                        if (BattleScreenController.monList.get(j) != null) {
                        // Checks if there is a pocket monster in this position
                            if (((String) BattleScreenController.monList.get(j).get("name")).equalsIgnoreCase(randomElement)) {
                                // If this pocket monster has already been used regenerates a random member
                                duplicateMon = true;
                                i -= 1;
                                break;
                            }
                        }
                    }
                }
            }
            if (duplicateMon == false) {
            // Makes sure that there is no duplicate on the team
                team.set(i, NewMonController.getMon(randomElement));
                // Adds the random pocket monster to the team
            }
        }
        return team;
        // Returns the team
    }

    /**
     * The lastPage method allows the user to return to the main screen whilst saving their created team
     * 
     * @throws IOException
     */
    @FXML
    private void lastPage() throws IOException {
        App.setRoot("homeScreen");
        // Calls on the setRoot method in App and changes the screen to the HomePage
    }

    @FXML
    private void editMon(ActionEvent event) throws IOException {
        buttonPressed = (Button) event.getSource();
        // Sets the button that called on this method as a variable to refrence later
        App.setRoot("newMon");
        // Calls on the setRoot method in App and changes the screen to the Choose pocket monster screen
    }
}
