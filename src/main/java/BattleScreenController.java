import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BattleScreenController {

    Move userMove;
    @FXML
    HBox teamBox, userMoveBox, labelBox;
    @FXML
    VBox chatBox;
    @FXML
    ImageView view1, view2, view3, view4, view5, view6, userMonView, oppMonView, moveImg1, moveImg2, moveImg3, moveImg4, pokeball1, 
    pokeball2, pokeball3, pokeball4, pokeball5, pokeball6, greyball1, greyball2, greyball3, greyball4, greyball5, greyball6;
    @FXML
    AnchorPane mainScene;
    @FXML
    Label oppNameLabel, userNameLabel, oppHealth, oppHealthPercent, userHealth, userHealthPercent, oppStatusLabel, userStatusLabel;
    @FXML
    Rectangle oppHealthBox, userHealthBox;
    @FXML
    Button backButton;

    ArrayList<ImageView> viewList = new ArrayList<ImageView>();
    Image userMonImage;
    Image oppMonImage;
    private int count = 0;
    String musicFile = "sounds/cynthiaTheme.wav";
    private int userBoostCount = 0;
    private int oppBoostCount = 0;
    private int turn = 1;

    public static int winCount = 0;
    public static int lossCount = 0;

    int oppTeamDead = 0;
    int userTeamDead = 0;
    boolean delete = false;

    int numOppAlive = 6;
    public double healthPercent = 100;
    String oppName = "";
    String userName = "";
    public static List<HashMap<String, Object>> monList;
    List<HashMap<String, Object>> oppMonList;
    HashMap<String, Object> userMon;
    HashMap<String, Object> oppMon;
    Move currentUserMove;
    Move currentOppMove;

    double previousHealth;

    private boolean userMoveFirst;
    private boolean oppMoveFirst;
    private String moveName;
    private String oppMoveName;

    private boolean userPoisoned, userBurned, userSleep, userShielded;
    private boolean oppPoisoned, oppBurned, oppSleep, oppShielded;

    private int userFullHealth;
    private int oppFullHealth;

    private double healthBoxWidth;
    public static List<String> music = Arrays.asList("src/main/resources/sounds/cynthiaTheme.mp3", 
    "src/main/resources/sounds/1-02 Opening.mp3");

    public static Media media;
    public static MediaPlayer mediaPlayer;

    /**
     * Calculates the type advantage multiplier to be used while calculating damage
     * 
     * @param The type of the move used as a string
     * @param The first type of the opposing pokemon as a string
     * @param The second type of the opposing pokemon as a string
     * @return The multiplier based on type effectiveness
     */
    private double calculateMultiplier(String moveType, String monType1, String monType2) {
        //type effectiveness multiplier variable
        double multiplier = 1;

        //if the move type is effective against the opponent mons first type multiply the multiplier by 2
        if ((Types.superEffective(moveType, monType1)) == true) {
            multiplier = multiplier * 2;
        //if move type is not effective against opponent mon first type divide multiplier by 2
        } else if ((Types.notVeryEffective(moveType, monType1))) {
            multiplier = multiplier / 2;
        //if the move type has no effect on mon's type set multiplier to 0 
        } else if ((Types.hasNoEffect(moveType, monType1))) {
            multiplier = 0;
            return multiplier;
        }

        //check if the opposing pokemon has a second type
        if (monType2 != null) {
            //if the move type is effective against the opponent mons second type multiply the multiplier by 2
            if ((Types.superEffective(moveType, monType2)) == true) {
                multiplier = multiplier * 2;
            //if move type is not effective against opponent mon second type divide the multiplier by 2
            } else if ((Types.notVeryEffective(moveType, monType2))) {
                multiplier = multiplier / 2;
            //if the move type has no effect on the mon's type set multiplier to 0
            } else if ((Types.hasNoEffect(moveType, monType2))) {
                multiplier = 0;
                return multiplier;
            }
        }
        //return the multiplier based off above calculations
        return multiplier;

    }

    /**
     * Checks the current status condition of the enemy or user and removes a
     * fraction
     * of health from the enemy or user. Health labels are updated to show the
     * newest
     * health values of the monster whose health was removed.
     *
     * @param player - String representing the player that should be focussed on
     * when checking status conditions. Possible cases are "bot" (AI enemy)
     * and "user" (main player).
     */

    private void checkStatusConditions(String player) {

        switch (player) {

            case "user":
                if (userPoisoned || userBurned) {
                    double healthRemoval = 0.125 * userFullHealth;
                    userMon.put("health", (int) userMon.get("health") - (int) healthRemoval);
                    Text text = new Text(userName + " Was Hurt By Status Effect");
                    chatBox.getChildren().add(text);
                    if ((int) userMon.get("health") < 0) {
                        userMon.put("health", 0);
                    }
                    updateHealthLabels((int) userMon.get("health"), userFullHealth, "user");
                }

                break;

            case "bot":
                if (oppPoisoned || oppBurned) {
                    double healthRemoval = 0.125 * oppFullHealth;
                    oppMon.put("health", (int) oppMon.get("health") - (int) healthRemoval);
                    Text text = new Text("Opposing " + oppName + " Was Hurt By Status Effect");
                    chatBox.getChildren().add(text);
                    if ((int) oppMon.get("health") < 0) {
                        oppMon.put("health", 0);
                    }
                    updateHealthLabels((int) oppMon.get("health"), oppFullHealth, "bot");
                }

                break;
        }
    }

    /**
     * Formally assigns the player their team and arranges the elements of the
     * BattleScreen
     * as necessary to allow for smooth user interactions. Resets all monster stats
     * to their
     * default settings and displays the correct images of the monsters on the
     * screen.
     *
     * @param monListIn - List of HashMaps representing the monsters that are apart
     * of the user's team.
     */

    private void createInitialConditions(List<HashMap<String, Object>> monListIn) {
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);
        monList = monListIn;

        for (int i = 0; i < monList.size(); i++) {
            HashMap<String, Object> tempMon = monList.get(i);
            tempMon.put("fainted", false);
            tempMon.put("health", (int) tempMon.get("fullHealth"));
            tempMon.put("burn", false);
            tempMon.put("poison", false);
            tempMon.put("sleep", false);
            tempMon.put("attack", (double) tempMon.get("originalAttack"));
            tempMon.put("defence", (double) tempMon.get("originalDefence"));
            tempMon.put("speed", (int) tempMon.get("originalSpeed"));
            
        }

        userMon = monList.get(0);
        userName = (String) userMon.get("name");
        userMonImage = new Image((String) userMon.get("backImage"));
        userMonView.setImage(userMonImage);

        updateMoves();

        for (int i = 0; i < teamBox.getChildren().size(); i++) {
            Button userMonButton = (Button) teamBox.getChildren().get(i);
            userMonButton.setText((String) monList.get(i).get("name"));

            Image pokeImage = new Image((String) monList.get(i).get("frontImage"));
            viewList.get(i).setImage(pokeImage);
        }

        for (int i = 0; i < teamBox.getChildren().size(); i++) {
            Button userMonButton = (Button) teamBox.getChildren().get(i);
            if (userMonButton.getText().equalsIgnoreCase((String) userMon.get("name"))) {
                userMonButton.setDisable(true);
                break;
            }
        }

        userFullHealth = (int) userMon.get("fullHealth");

    }

    /**
     * removeUserMon() method is called when the user's current monster runs out of
     * health (health goes to zero) and faints. This method removes the image of the
     * fainted monster and disables the attacking buttons to prevent the user from attacking until
     * a new monster is chosen. The button in the teamBox that represents this monster
     * is disabled to prevent the user from choosing a monster with no health.
     *
     * @param faintedMon - String representing the name of the monster that fainted
     */

    private void removeUserMon(String faintedMon) {
        userMon.put("fainted", true);
        userMonView.setImage(null);
        for (int i = 0; i < teamBox.getChildren().size(); i++) {
            Button temp = (Button) teamBox.getChildren().get(i);
            if (temp.getText().equalsIgnoreCase(faintedMon) && temp.isDisabled()) {
                temp.setText("fainted");
                temp.setDisable(true);
            }
        }
        for (int i = 0; i < userMoveBox.getChildren().size(); i++) {
            Button temp = (Button) userMoveBox.getChildren().get(i);
            Label tempLabel = (Label) labelBox.getChildren().get(i);
            temp.setText(null);
            temp.setDisable(true);
            tempLabel.setText(null);
        }
        moveImg1.setImage(null);
        moveImg2.setImage(null);
        moveImg3.setImage(null);
        moveImg4.setImage(null);
    }

    /**
     * Sets the move of the enemy monster by choosing a random move from the enemy
     * monster's move list.
     *
     * @return - Move object representing the new enemy move for the current turn.
     */

    private Move setOppMove() {

        Random rand = new Random();
        int temp = 0;

        temp = rand.nextInt(4);
        return ((List<Move>) oppMon.get("moves")).get(temp);

    }

    /**
     * Updates the health label of the user's monster or the enemy monster depending
     * on the String that is passed into the method. This method shows the most updated
     * value of a monster's health as both a value and a percent. The health bar also 
     * updates to display the monster's health as a fraction of their whole health.
     * 
     * @param monHealth - The monster's current health
     * @param monTotalHealth - The monster's maximum health value
     * @param player - String representing the health label that should be updated. 
     * This method can update the user's health bar (case "user") 
     * or the enemy's health bar (case "bot").
     */
    private void updateHealthLabels(int monHealth, int monTotalHealth, String player) {
        switch (player) {
            case "user":
                userHealth.setText(Integer.toString(monHealth) + "/" + Integer.toString(monTotalHealth));
                healthPercent = ((double) monHealth / (double) monTotalHealth) * 100;
                healthPercent = (int) healthPercent;
                userHealthPercent.setText((int)(healthPercent) + "%");
                userHealthBox.setWidth((healthPercent / 100) * healthBoxWidth);
                break;

            case "bot":
                oppHealth.setText(Integer.toString(monHealth) + "/" + Integer.toString(monTotalHealth));
                healthPercent = ((double) monHealth / (double) monTotalHealth) * 100;
                healthPercent = (int) healthPercent;
                oppHealthPercent.setText((int)(healthPercent) + "%");
                oppHealthBox.setWidth((healthPercent / 100) * healthBoxWidth);
                break;

        }
    }

    /**
     * Updates the user's buttons for choosing attacks. This method is called whenever
     * the user sends out a new monster. The text within the buttons changes to reflect 
     * the current monster's list of moves.
     */
    private void updateMoves() {
        for (int i = 0; i < userMoveBox.getChildren().size(); i++) {
            Button moveButton = (Button) userMoveBox.getChildren().get(i);
            moveButton.setDisable(false);
            moveButton.setText(((List<Move>) userMon.get("moves")).get(i).getMoveName());
        }
    }

    /**
     * Updates the status labels of both the user's monster and the enemy monster.
     */
    private void updateStatusLabels() {

        userPoisoned = (boolean) userMon.get("poison");
        userBurned = (boolean) userMon.get("burn");
        userSleep = (boolean) userMon.get("sleep");
        oppPoisoned = (boolean) oppMon.get("poison");
        oppBurned = (boolean) oppMon.get("burn");
        oppSleep = (boolean) oppMon.get("sleep");
        //Assigns values to boolean variables representing the user's and enemy's status conditions

        if (userPoisoned) {
            userStatusLabel.setVisible(true);
            userStatusLabel.setTextFill(Color.PURPLE);
            userStatusLabel.setText("PSN");
            //Sets label text to "PSN" is user is poisoned
        } else if (userBurned) {
            userStatusLabel.setVisible(true);
            userStatusLabel.setTextFill(Color.RED);
            userStatusLabel.setText("BRN");
            // Sets label text to "BRN" is user is burned
        } else if (userSleep) {
            userStatusLabel.setVisible(true);
            userStatusLabel.setTextFill(Color.GREY);
            userStatusLabel.setText("SLP");
            // Sets label text to "SLP" is user is asleep
        } else {
            userStatusLabel.setVisible(false);
        }

        if (oppPoisoned) {
            oppStatusLabel.setVisible(true);
            oppStatusLabel.setTextFill(Color.PURPLE);
            oppStatusLabel.setText("PSN");
            // Sets label text to "PSN" is enemy is poisoned
        } else if (oppBurned) {
            oppStatusLabel.setVisible(true);
            oppStatusLabel.setTextFill(Color.RED);
            oppStatusLabel.setText("BRN");
            // Sets label text to "BRN" is enemy is poisoned
        } else if (oppSleep) {
            oppStatusLabel.setVisible(true);
            oppStatusLabel.setTextFill(Color.GREY);
            oppStatusLabel.setText("SLP");
            // Sets label text to "SLP" is enemy is asleep
        } else {
            oppStatusLabel.setVisible(false);
        }

    }

    /**
     * Initialize() method is called right after all FXML elements have been loaded.
     * This method is responsible for setting up the UI of the BattleScreen for the 
     * beginning of a battle. Teams are created, labels are updated and battle soundtrack
     * starts to play. 
     */
    @FXML
    private void initialize() {
        mediaPlayer.stop();
        App.stage.setWidth(950);
        if (PrimaryController.teamUsed == "random") {
            createInitialConditions(newTeamController.randTeam("user"));
            //creates a random team for the user
        } else {
            createInitialConditions(newTeamController.teamList);
            //sets the user's team to the team they created in the team builder
        }

        healthBoxWidth = userHealthBox.getWidth();
        oppMonList = newTeamController.randTeam("bot");
        //creates a random team of six unique monsters for the enemy

        oppMon = oppMonList.get(0);
        //sets enemy monster to be the first monster in the enemy's team list

        oppMonImage = new Image((String) oppMon.get("frontImage"));
        oppMonView.setImage(oppMonImage);
        //displays image of the enemy's current monster

        oppName = (String) oppMon.get("name");
        oppFullHealth = (int) oppMon.get("fullHealth");
        oppNameLabel.setText(oppName);
        userNameLabel.setText(userName);
        //Sets the text of labels to display the name of the opposing monster

        userStatusLabel.setVisible(false);
        oppStatusLabel.setVisible(false);

        
        if(numOppAlive == 6){
            greyball1.setVisible(false);
            greyball2.setVisible(false);
            greyball3.setVisible(false);
            greyball4.setVisible(false);
            greyball5.setVisible(false);
            greyball6.setVisible(false);
            // initially hide all of the grey balls since all opponents are alive
        }

        checkSpeeds();
        updateHealthLabels((int) userMon.get("health"), userFullHealth, "user");
        updateHealthLabels((int) oppMon.get("health"), oppFullHealth, "bot");
        updateStatusLabels();
        //Updates the health labels and status labels of the user's monster 

        checkEffective();

        Text text = new Text("Turn 1");
        chatBox.getChildren().add(text);
        //Adds text to the chatBox that announces the first turn

        String path = music.get(0);
        //String representing the path of the music file

         media = new Media(new File(path).toURI().toString());
         mediaPlayer = new MediaPlayer(media);
         //creates new mediaPlayer with the media containing the desired music

         mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
            }
        });
        mediaPlayer.play();
        //Plays the battle music
    }

    /**
     * Swaps the user's monster to another monster in their team list depending on
     * the monster they select. This method is called whenever a button within the teamBox 
     * is pressed. Swapping a monster can result in different enemy actions depending 
     * on the circumstances of the monster swap. 
     * 
     * @param event - ActionEvent representing the action that causes this method
     * to run.
     */
    @FXML
    private void swapMonster(ActionEvent event) {
        mediaPlayer.play();
        //continues playing the battle music


        Button clickedButton = (Button) event.getSource();
        //gets the button that was used in the action (gets button that was clicked)

        boolean swappedWhenAlive = true;
        userBoostCount = 0;

        for (int i = 0; i < teamBox.getChildren().size(); i++) {
            Button tempButton = (Button) teamBox.getChildren().get(i);
            //temporary button that is one of the children of the teamBox

            if (clickedButton == tempButton) {
                if (userMonView.getImage() == null) {
                    swappedWhenAlive = false;
                    //if there is no image of a user mon on screen, the user is swapping after a monster has fainted
                }
                userMon = monList.get(i);
                //changes the user mon to the monster that corresponds to the button that was pressed

                clickedButton.setDisable(true);
                userMonImage = new Image((String) userMon.get("backImage"));
                userMonView.setImage(userMonImage);
                //displays the image of the new user mon on the screen

                userName = (String) userMon.get("name");
                userNameLabel.setText(userName);
                userFullHealth = (int) userMon.get("fullHealth");
                updateHealthLabels((int) userMon.get("health"), userFullHealth, "user");
                updateMoves();
                updateStatusLabels();
                checkEffective();
                //updates the health labels, status labels and move butons to reflect the attributes of the new monster

                Text text = new Text("You Sent Out " + userName);
                chatBox.getChildren().add(text);
                //chatBox annoucement of the user's new monster being sent out

                if (swappedWhenAlive == true) {
                    if (turn >= 4) {
                        int oldTurn = turn - 2;
                        while (delete == false) {
                           Text tempText = (Text) chatBox.getChildren().get(0);
                           if (tempText.getText().equals('\n' + "Turn " + oldTurn)) {
                               break;
                           }
                           else {
                               chatBox.getChildren().remove(tempText); 
                           }
                        }
                    }
                    currentOppMove = setOppMove();
                    checkDamage("bot");
                    //if the user swaps when their current mon is alive, the enemy gets to attack immediately after the swap

                    checkStatusConditions("user");
                    if ((int) userMon.get("health") <= 0) {
                        removeUserMon((String) userMon.get("name"));
                    }
                    //Removes health due to status effects if necessary and removes the user mon if it has no health points left

                    turn++;
                    Text nextTurn = new Text('\n' + "Turn " + turn); 
                    chatBox.getChildren().add(nextTurn);
                    //chatBox announcement representing the beginning of the next turn
                }
                for (int j = 0; j < teamBox.getChildren().size(); j++) {
                    Button temp = (Button) teamBox.getChildren().get(j);
                    if (temp != clickedButton && !temp.getText().equalsIgnoreCase("fainted")) {
                        temp.setDisable(false);
                        //Enables the team buttons of monsters that have not fainted and are not currently in battle
                    }
                }
                break;
            }
        }
    }

    /**
     * Method is called if the back button is pressed once a match is over.
     * Stops the battle music, resizes the width of the stage and returns the user to the home screen.
     */
    @FXML
    private void backPressed() throws IOException {
        mediaPlayer.stop();
        App.stage.setWidth(640);
        App.setRoot("homeScreen");
    }

    /**
     * a function that shows one more grey ball every time an opposing pocket monster is killed
     */
    @FXML
    private void ballDisplay(){
        if(numOppAlive == 5){
            pokeball1.setVisible(false);
            greyball1.setVisible(true);
        } else if (numOppAlive == 4) {
            pokeball2.setVisible(false);
            greyball2.setVisible(true);

        } else if (numOppAlive == 3) {
            pokeball3.setVisible(false);
            greyball3.setVisible(true);

        } else if (numOppAlive == 2) {
            pokeball4.setVisible(false);
            greyball4.setVisible(true);

        } else if (numOppAlive == 1) {
            pokeball5.setVisible(false);
            greyball5.setVisible(true);

        } else if (numOppAlive == 0) {
            pokeball6.setVisible(false);
            greyball6.setVisible(true);

        }
    }

    /**
     * Swaps the enemy's monster with a new monster in their team list. This method 
     * is called whenever an enemy monster faints. Necessary labels are updated to
     * show the health, status effects and name of the new enemy monster.
     */
    private void swapOppMon() {
        numOppAlive -= 1;
        // every time the opponent swaps monsters, it means that one died, so decrease the counter

        ballDisplay();
        //Changes the number of "alive" PokeBalls to represent the new number of alive enemy monsters

        Text dieText = new Text("Opposing " + oppName + " Has Fainted");
        chatBox.getChildren().add(dieText);
        //chatBox announcement stating that an enemy monster has fainted

        count++;
        oppBoostCount = 0;
        oppMon = oppMonList.get(count);
        oppMonImage = new Image((String) oppMon.get("frontImage"));
        oppMonView.setImage(oppMonImage);
        updateHealthLabels((int) oppMon.get("health"), (int) oppMon.get("fullHealth"), "bot");
        updateStatusLabels();
        checkEffective();
        //Updates health labels, status labels and effectiveness indicators to reflect the attributes of the new enemy monster
        oppName = (String) oppMon.get("name");
        oppNameLabel.setText(oppName);
        //Displays name of the new enemy mon on the screen

        Text text = new Text("Opponent Sent Out " + oppName);
        chatBox.getChildren().add(text);
        //chatBox announcement stating that the opponent has sent out a new monster
    }

    /**
     * This method is called whenever a button within the userMoveBox is pressed
     * (whenever the user chooses a move for the upcoming turn).
     * This method executes the moves of the enemy and user in order depending on
     * the speeds of the user mon and enemy mon, and whether
     * a priority move has been used. This method calls upon other methods to remove
     * necessary health from opposing monsters or inflict
     * the necessary status effects on an opposing monster.
     * 
     * @param event - ActionEvent representing the action that causes this method
     *               to run.
     */
    @FXML
    private void chooseMove(ActionEvent event) {
        if (turn >= 4) {
             int oldTurn = turn - 2;
             while (delete == false) {
                Text temp = (Text) chatBox.getChildren().get(0);
                if (temp.getText().equals('\n' + "Turn " + oldTurn)) {
                    break;
                }
                else {
                    chatBox.getChildren().remove(temp); 
                }
             }
             //Removes older announcements from the chatBox to free up space
        }

        userMoveFirst = false;
        oppMoveFirst = false;
        //boolean variables that determine which monster attacks first

        Button clickedButton = (Button) event.getSource();
        //Button variable representing the button that was clicked to cause this method to run

        boolean userJustFainted = false;
        boolean oppJustFainted = false;
        //boolean variables that state whether a user or enemy monster has fainted in the current turn

        List<Move> userMoveList = (List<Move>) userMon.get("moves");
        //List of user monster's moves

        for (int i = 0; i < userMoveList.size(); i++) {
            if (userMoveList.get(i).getMoveName().equalsIgnoreCase(clickedButton.getText())) {
                currentUserMove = userMoveList.get(i);
                break;
                //Determines the user's move based on the text within the button that was clicked
            }
        }
        moveName = currentUserMove.getMoveName();

        currentOppMove = setOppMove();
        oppMoveName = currentOppMove.getMoveName();

        if (oppSleep) {
            Random rand = new Random();
            int wakeUpNum = rand.nextInt(3);
            if (wakeUpNum == 0) {
                oppMon.put("sleep", false);
                updateStatusLabels();
                //If enemy mon is asleep, it has a 33% chance of waking up each turn
                //If random number is zero, the enemy mon will wake up
            }
        }
        if (userSleep) {
            Random rand = new Random();
            int wakeUpNum = rand.nextInt(3);
            if (wakeUpNum == 0) {
                userMon.put("sleep", false);
                updateStatusLabels();
                // If user mon is asleep, it has a 33% chance of waking up each turn
                // If random number is zero, the user mon will wake up
            } 
        }

        if (currentUserMove.attacksFirst() == true && currentOppMove.attacksFirst() == false) {
            userMoveFirst = true;
            //If user uses a priority move and the enemy does not, the user will go first
        } else if (currentOppMove.attacksFirst() == true && currentUserMove.attacksFirst() == false) {
            oppMoveFirst = true;
            // If enemy uses a priority move and the user does not, the enemy will go first
        }

        if ((userMoveFirst == true && oppMoveFirst == true) || (userMoveFirst == false && oppMoveFirst == false)) {
            //If neither players use a priority move or if both players use a priority move, compare speeds of monsters
            //to see who goes first
            checkSpeeds();
        }

        if (userMoveFirst) {
            Text userMoveText = new Text(userName + " Used " + moveName);
            chatBox.getChildren().add(userMoveText);
            //chatBox announcement stating the move of the user mon

            checkDamage("user");
            checkHealFactor("user");
            checkStatBoost("user");
            checkStatusConditions("bot");
            //Takes away necessary damage from enemy, takes away user's health due to recoil (if necessary), increases/decreases
            //user's stats if necessary, and takes away enemy's health due to status effects if necessary.
            if ((int) userMon.get("health") <= 0) {
                userTeamDead++;
                removeUserMon((String) userMon.get("name"));
                checkWinLoss();
                userJustFainted = true;
                //Removes the user's mon from battle if it's health goes to zero
                //Checks to see if the enemy has won 
            }
            if ((int) oppMon.get("health") > 0 && userJustFainted == false) {
                //Enemy's attack only lands if the user mon has not fainted in the same turn
                Text botMoveText = new Text("Opposing " + oppName + " Used " + oppMoveName);
                chatBox.getChildren().add(botMoveText);
                //chatBox announcement stating the move of the enemy monster

                checkDamage("bot");
                checkHealFactor("bot");
                checkStatBoost("bot");
                checkStatusConditions("user");
                // Takes away necessary damage from user, takes away enemy's health due to recoil (if necessary), increases/decreases
                // enemy's stats if necessary, and takes away user's health due to status effects if necessary.
            }
            if ((int) oppMon.get("health") <= 0) {
                oppTeamDead++;
                checkWinLoss();
                if (oppTeamDead < 6) {
                    swapOppMon();
                }
                //Swaps enemy's monster if their current monster's health goes to zero
                //Checks to see if the user has won
                //If all enemy monsters have fainted, no new monster is sent out
            }
            if ((int) userMon.get("health") <= 0) {
                userTeamDead++;
                removeUserMon((String) userMon.get("name"));
                checkWinLoss();
                // Removes the user's mon from battle if it's health goes to zero
                // Checks to see if the enemy has won

            }

        } else {
            Text botMoveText = new Text("Opposing " + oppName + " Used " + oppMoveName);
            chatBox.getChildren().add(botMoveText);
            //chatBox announcement stating the enemy's move

            checkDamage("bot");
            checkHealFactor("bot");
            checkStatBoost("bot");
            checkStatusConditions("user");
            // Takes away necessary damage from user, takes away enemy's health due to
            // recoil (if necessary), increases/decreases
            // enemy's stats if necessary, and takes away user's health due to status
            // effects if necessary.

            if ((int) userMon.get("health") <= 0) {
                userTeamDead++;
                removeUserMon((String) userMon.get("name"));
                checkWinLoss();
                userJustFainted = true;
                // Removes the user's mon from battle if it's health goes to zero
                // Checks to see if the enemy has won
            }
            if ((int) oppMon.get("health") <= 0) {
                oppTeamDead++;
                checkWinLoss();
                oppJustFainted = true;
                if (oppTeamDead < 6) {
                    swapOppMon();
                }
                // Swaps enemy's monster if their current monster's health goes to zero
                // Checks to see if the user has won
                // If all enemy monsters have fainted, no new monster is sent out
            }
            if ((int) userMon.get("health") > 0 && oppJustFainted == false) {
                //User's attack only lands if the enemy's mon has not fainted in the same turn
                Text userMoveText = new Text(userName + " Used " + moveName);
                chatBox.getChildren().add(userMoveText);
                //chatBox announcement stating the user's move

                checkDamage("user");
                checkHealFactor("user");
                checkStatBoost("user");
                checkStatusConditions("bot");
                // Takes away necessary damage from enemy, takes away user's health due to
                // recoil (if necessary), increases/decreases
                // user's stats if necessary, and takes away enemy's health due to status
                // effects if necessary.
            }

            if ((int) userMon.get("health") <= 0) {
                
                if (!userJustFainted){
                    userTeamDead++;
                }
                removeUserMon((String) userMon.get("name"));
                checkWinLoss();
                // Removes the user's mon from battle if it's health goes to zero
                // Checks to see if the enemy has won
            }
            if ((int) oppMon.get("health") <= 0) {
                oppTeamDead++;
                checkWinLoss();
                if (oppTeamDead < 6) {
                    swapOppMon();
                }
                // Swaps enemy's monster if their current monster's health goes to zero
                // Checks to see if the user has won
                // If all enemy monsters have fainted, no new monster is sent out
            }
        }
        userShielded = false;
        oppShielded = false;
        //Resets shielded statuses to false for both monsters since protection moves
        //only last for one turn

        turn++;
        Text text = new Text('\n' + "Turn " + turn);
        chatBox.getChildren().add(text);
        //chatBox announcement stating the beginning of a new turn

    }

    /**
     * Compares the speeds of the user's monster and the enemy's monster. The 
     * monster with the higher speed attacks first unless the slower monster uses 
     * a priority move and the faster monster does not use a priority move.
     */
    private void checkSpeeds() {
        int userSpeed = (int) userMon.get("speed");
        int oppSpeed = (int) oppMon.get("speed");
        if (userSpeed >= oppSpeed) {
            userMoveFirst = true;
            oppMoveFirst = false;
        } else {
            oppMoveFirst = true;
            userMoveFirst = false;
        }
    }

    /**
     * Checks if a stat boosting or lowering move was used and changes stats accordingly
     * 
     * @param the string of the side using the move either user or bot
     */
    private void checkStatBoost(String attacker) {
        //the amount the stats are being boosted by for user
        double userDefBoost = currentUserMove.getDefenceBoost();
        double userAtkBoost = currentUserMove.getAttackBoost();
        int userSpdBoost = (int) currentUserMove.getSpeedBoost();

        //the new stats calculated for the user
        double userDefence = (double) userMon.get("defence") * userDefBoost;
        double userAttack = (double) userMon.get("attack") * userAtkBoost;
        int userSpeed = (int) userMon.get("speed") * userSpdBoost;

        //the amount the stats are being boosted by for the bot
        double oppDefBoost = currentOppMove.getDefenceBoost();
        double oppAtkBoost = currentOppMove.getAttackBoost();
        int oppSpdBoost = (int) currentOppMove.getSpeedBoost();

        //the new stats calculated for the bot
        double oppDefence = (double) oppMon.get("defence") * oppDefBoost;
        double oppAttack = (double) oppMon.get("attack") * oppAtkBoost;
        int oppSpeed = (int) oppMon.get("speed") * oppSpdBoost;

        //switch between the player and the bot
        switch (attacker) {
            //user case
            case ("user"):
                //check if a stat was boosted and add to the count
                if (userDefBoost != 1 || userAtkBoost != 1 || userSpdBoost != 1) {
                    userBoostCount++;
                }

                //if less than 2 boosts have been used
                if (userBoostCount < 2){
                    //add the new user defence calculated to current pokemon stats
                    userMon.put("defence",userDefence);
                    
                    //add the new user attack calculated to current pokemon stats
                    userMon.put("attack", userAttack);
                        
                    //add the new user speed calculated to current pokemon stats
                    userMon.put("speed", userSpeed);
                   
                    //if more than 2 boosts have been used notify the user
                } else {
                    Text text = new Text((String)userMon.get("name") + " has reached the max boost!");
                    chatBox.getChildren().add(text);
                }
                 //end the switch case
                break;
            
             //bot case
            case ("bot"):
                //check if a stat was boosted and add to the count
                if (oppDefBoost != 1 || oppAtkBoost != 1 || oppSpdBoost != 1) {
                    oppBoostCount++;
                }

                //if less than 2 boosts have been used
                if (oppBoostCount < 2){
                    //add the new bot defence calculated to current pokemon stats
                    oppMon.put("defence",oppDefence);
                   
                    //add the new bot attack calculated to current pokemon stats
                    oppMon.put("attack",oppAttack);
                    
                    //add the new bot speed calculated to current pokemon stats
                    oppMon.put("speed",oppSpeed);
                   
                }
                //end the switch case
                break;
        }

    }
    /**
     * Checks if a healing move or recoil move was used and changes health accordingly
     * 
     * @param the string of the side using the move either user or bot
     */
    private void checkHealFactor(String attacker) {

        //variables for health
        double userHealFactor = currentUserMove.getHealFactor();
        double oppHealFactor = currentOppMove.getHealFactor();
        int currentUserHealth = (int) userMon.get("health");
        int currentOppHealth = (int) oppMon.get("health");
        userFullHealth = (int) userMon.get("fullHealth");
        oppFullHealth = (int) oppMon.get("fullHealth");

        //switch between player and bot using healing moves
        switch (attacker) {

            //player case
            case "user":
                //add a percentage of health to current health depending on the moves healfactor
                updateHealthLabels(currentUserHealth, userFullHealth, "user");
                int userOldHealth = (int) healthPercent;
                currentUserHealth = (int) (currentUserHealth + (userFullHealth * userHealFactor));

                //stops health from going above the max
                if (currentUserHealth > userFullHealth) {
                    currentUserHealth = userFullHealth;
                }
                //add the health to the current mons hashmap
                if (currentUserHealth < 0) {
                    currentUserHealth = 0;
                }
                userMon.put("health", (int) currentUserHealth);

                //change the healthbar to match health
                updateHealthLabels(currentUserHealth, userFullHealth, "user");
                if ((healthPercent - userOldHealth) > 0 ) {
                    Text userText = new Text(userName + " Healed " + (int)(healthPercent - userOldHealth) + "%");
                    chatBox.getChildren().add(userText);
                }
                break;
            
            //bot case
            case "bot":
            //add a percentage of health to current health depending on the moves healfactor
                updateHealthLabels(currentOppHealth, oppFullHealth, "bot");
                int oppOldHealth = (int) healthPercent;
                currentOppHealth = (int) (currentOppHealth + (oppFullHealth * oppHealFactor));

                //stops health from going above the max
                if (currentOppHealth > oppFullHealth) {
                    currentOppHealth = oppFullHealth;
                }
                //add the health to the current mons hashmap
                if (currentOppHealth < 0) {
                    currentOppHealth = 0;
                }
                oppMon.put("health", (int) currentOppHealth);
                //change the healthbar to match health
                updateHealthLabels(currentOppHealth, oppFullHealth, "bot");
                if ((healthPercent - oppOldHealth) > 0 ) {
                    Text oppText = new Text("Opposing " + oppName + " Healed " + (int)(healthPercent - oppOldHealth) + "%");
                    chatBox.getChildren().add(oppText);
                }
                break;
        }
    }

    /**
     * Calculates the damage to be deducted from the opposing monster using the power
     * of a move, the effectiveness against the opposing type and the monsters'
     * stats. This method also declares status effects on monsters such as burn,
     * poison and sleep, and calls upon the appropriate methods to update the 
     * status labels.
     * 
     * @param attacker - String representing the attacker. The attacker can either 
     * be the main player (case "user") or the AI enemy (case "bot")
     */
    private void checkDamage(String attacker) {

        String moveType;
        String oppMonType1;
        String oppMonType2;
        double damage = 0;
        double multiplier = 1;
        double mainAttack = 0;
        double oppDefence = 0;
        int power = 0;

        switch (attacker) {

            case "user":

                moveType = currentUserMove.getType();
                oppMonType1 = (String) (oppMon.get("type1"));
                oppMonType2 = (String) (oppMon.get("type2"));

                if (currentUserMove.getDamage() > 0 && userSleep == false && oppShielded == false) {

                    multiplier = calculateMultiplier(moveType, oppMonType1, oppMonType2);
                    //calculates the damage multiplier depending on the type effectiveness

                    power = currentUserMove.getDamage();
                    //base power of the move

                    mainAttack = (double) userMon.get("attack");
                    oppDefence = (double) oppMon.get("defence");
                    //attack stat of the user and defence stat of the enemy

                    damage = ((((42 * power * (mainAttack / oppDefence)) / 50) + 2) * multiplier);
                    damage = (int) damage;
                    //formula for calculating damage

                    previousHealth = healthPercent;
                    oppMon.put("health", (int) oppMon.get("health") - (int) damage);
                    //deducting the move damage from the opponents current health

                    if ((int) oppMon.get("health") < 0) {
                        oppMon.put("health", 0);
                        //prevents the monster's health from going below zero
                    }
                    updateHealthLabels((int) oppMon.get("health"), oppFullHealth, "bot");
                    //Updates the health labels of the enemy monster

                    if (multiplier >= 2) {
                        Text text = new Text("Opposing " + oppName + " Has Lost " + (int)Math.abs(previousHealth - Math.abs(healthPercent)) + "%");
                        Text effectiveText = new Text("It Was Super Effective");
                        chatBox.getChildren().add(text);  
                        chatBox.getChildren().add(effectiveText); 
                        
                    }
                    else if (multiplier == 1) {
                        Text text = new Text("Opposing " + oppName + " Has Lost " + (int)Math.abs(previousHealth - Math.abs(healthPercent)) + "%");
                        chatBox.getChildren().add(text);  
                    }
                    else if ((multiplier < 1) && (multiplier > 0)) {
                        Text text = new Text("Opposing " + oppName + " Has Lost " + (int)Math.abs(previousHealth - Math.abs(healthPercent)) + "%");
                        Text effectiveText = new Text("It Was Not Very Effective");
                        chatBox.getChildren().add(text);  
                        chatBox.getChildren().add(effectiveText);
                    }
                    else {
                        Text text = new Text("It Had No Effect");
                        chatBox.getChildren().add(text);  
                    }
                    //chatBox announcements stating the damage and effectiveness of the user's move

                } else if (currentUserMove.getDamage() == 0 && userSleep == false) {
                    //logic for declaring status conditions from status-affecting moves

                    multiplier = calculateMultiplier(moveType, oppMonType1, oppMonType2);
                    if (oppShielded == false) {
                        //Status affecting moves can only hit the enemy if the enemy is not shielded

                        if (currentUserMove.poisonMove() && oppBurned == false && oppSleep == false
                                && multiplier != 0.0) {
                            oppMon.put("poison", true);
                            Text poisonText = new Text("Opposing " + oppName + " Has Been Poisoned");
                            chatBox.getChildren().add(poisonText);
                            updateStatusLabels();
                            //If the enemy mon is not immune to poison-type moves, it can get poisoned
                            //Enemy can only get poisoned if it does not have other status effects
                            //Sets enemy's poison status to true and updates the status labels to display new status
                        } else if (currentUserMove.burnMove() && oppPoisoned == false && oppSleep == false
                                && multiplier != 0.0) {
                            oppMon.put("burn", true);
                            Text burnText = new Text("Opposing " + oppName + " Has Been Burnt");
                            chatBox.getChildren().add(burnText);
                            updateStatusLabels();
                            //If the enemy is not immune to fire-type moves, it can get burned
                            //Enemy can only get burned if it does not have other status effects
                            //Sets the enemy's burn status to true and updates the status labels to display new status
                        }
                    } 
                    if (currentUserMove.sleepMove()) {
                        userMon.put("sleep", true);
                        if (currentUserMove.getMoveName() == "Rest") {
                            userMon.put("health", (int) userMon.get("fullHealth"));
                            userMon.put("poison", false);
                            userMon.put("burn", false);
                        }
                        updateStatusLabels();
                        //Sleep moves will put the user of the move to sleep (in this case, the main player)
                        //Sets all status effects besides "sleep" to false
                        //Restores the user's health to maximum capacity
                    }
                    if (currentUserMove.isShielded() && userMoveFirst) {
                        userShielded = true;
                        //The user can only protect itself from attacks if they go first
                    } 
                }
                if (userSleep == true) {
                    Text sleepText = new Text(userName + " Is Sleeping");
                    chatBox.getChildren().add(sleepText);
                    // Displays in chatBox when user is sleeping
                }
                break;

            case "bot":

                moveType = currentOppMove.getType();
                oppMonType1 = (String) userMon.get("type1");
                oppMonType2 = (String) userMon.get("type2");

                if (currentOppMove.getDamage() > 0 && oppSleep == false && userShielded == false) {
                    previousHealth = healthPercent;

                    multiplier = calculateMultiplier(moveType, oppMonType1, oppMonType2);
                    //calculates the damage multiplier based on type effectiveness

                    power = currentOppMove.getDamage();
                    //base power of a move

                    mainAttack = (double) oppMon.get("attack");
                    oppDefence = (double) userMon.get("defence");
                    //attack stat of the AI enemy and defence stat of the user

                    damage = ((((42 * power * (mainAttack / oppDefence)) / 50) + 2) * multiplier);
                    damage = (int) damage;
                    //formula for calculating move damage

                    userMon.put("health", (int) userMon.get("health") - (int) damage);
                    //deducting damage from the user's current health

                    if ((int) userMon.get("health") < 0) {
                        userMon.put("health", 0);
                        //prevents the user's health from going below zero
                    }
                    updateHealthLabels((int) userMon.get("health"), userFullHealth, "user");
                    //Displays updated health values of the user on the screen
                 
                    if (multiplier >= 2) {
                        Text text = new Text(userName + " Has Lost " + (int)Math.abs(previousHealth - Math.abs(healthPercent)) + "%");
                        Text effectiveText = new Text("It Was Super Effective");
                        chatBox.getChildren().add(text);  
                        chatBox.getChildren().add(effectiveText);
                    }
                    else if (multiplier == 1) {
                        Text text = new Text(userName + " Has Lost " + (int)Math.abs(previousHealth - Math.abs(healthPercent)) + "%");
                        chatBox.getChildren().add(text);  
                    }
                    else if ((multiplier < 1) && (multiplier > 0)) {
                        Text text = new Text(userName + " Has Lost " + (int)Math.abs(previousHealth - Math.abs(healthPercent)) + "%");
                        Text effectiveText = new Text("It Was Not Very Effective");
                        chatBox.getChildren().add(text);  
                        chatBox.getChildren().add(effectiveText);  
                    }
                    else {
                        Text text = new Text("It Had No Effect");
                        chatBox.getChildren().add(text);  
                    }
                    //chatBox announcements stating the damage and effectiveness of the enemy's move

                } else if (currentOppMove.getDamage() == 0 && oppSleep == false) {
                    //logic for declaring status conditions from status-affecting moves

                    multiplier = calculateMultiplier(moveType, oppMonType1, oppMonType2);
                    if (userShielded == false) {
                        //User's monster can only get hit if it is not shielded
                        if (currentOppMove.poisonMove() && userBurned == false && userSleep == false
                                && multiplier != 0.0) {
                            userMon.put("poison", true);
                            Text poisonText = new Text(userName + " Has Been Poisoned");
                            chatBox.getChildren().add(poisonText);
                            updateStatusLabels();
                            //If the user is not immune to poison-type moves, it can get poisoned
                            // User can only get poisoned if it does not have other status effects
                            // Sets user's poison status to true and updates the status labels to display new status
                            

                        } else if (currentOppMove.burnMove() && userPoisoned == false && userSleep == false
                                && multiplier != 0.0) {
                            userMon.put("burn", true);
                            Text burnText = new Text(userName + " Has Been Burnt");
                            chatBox.getChildren().add(burnText);
                            updateStatusLabels();
                            // If the user is not immune to fire-type moves, it can get burned
                            // User can only get burned if it does not have other status effects
                            // Sets the user's burn status to true and updates the status labels to display new status
                        }
                    }
                    if (currentOppMove.sleepMove()) {
                        oppMon.put("sleep", true);
                        if (currentOppMove.getMoveName() == "Rest") {
                            oppMon.put("health", (int) oppMon.get("fullHealth"));
                            oppMon.put("poison", false);
                            oppMon.put("burn", false);
                        }
                        updateStatusLabels();
                        // Sleep moves will put the user of the move to sleep (in this case, the AI enemy)
                        // Sets all status effects besides "sleep" to false
                        // Restores the enemy's health to maximum capacity
                    }

                    if (currentOppMove.isShielded() && oppMoveFirst) {
                        oppShielded = true;
                        //the enemy can only protect itself from moves if it moves first
                    } 
                }
                if (oppSleep == true) {
                    Text sleepText = new Text("Opposing " + oppName + " Is Sleeping");
                    chatBox.getChildren().add(sleepText);
                    // Displays in chatBox when opponent is sleeping
                }

                break;

        }

    }

    /**
     * The checkEffective method is used to update the labels telling user if the move is super effective
     * Adds type image to the moves
     */
    private void checkEffective() {
        // Creates a list of image views
        ArrayList<ImageView> moveImgList = new ArrayList<ImageView>();
        moveImgList.add(moveImg1);
        moveImgList.add(moveImg2);
        moveImgList.add(moveImg3);
        moveImgList.add(moveImg4);
        // For loop that runs 4 times
        for (int i = 0; i < 4; i++) {
            // Takes the moves of the userMon and checks the moves effectiveness
            List<Move> moveList = (List<Move>) userMon.get("moves");
            String tempMove = ((List<Move>) userMon.get("moves")).get(i).getType();
            String oppMonType1 = (String) oppMon.get("type1");
            String oppMonType2 = (String) oppMon.get("type2");
            Double effectiveness = calculateMultiplier(tempMove, oppMonType1, oppMonType2);
            // Sets a variable as the label in an HBox
            Label text = (Label) labelBox.getChildren().get(i);
            // Makes sure the move is a damaging move
            if (!moveList.get(i).isShielded() && !moveList.get(i).sleepMove() && moveList.get(i).getDamage() != 0) {
                // If the effectiveness is 2 or more the move is displayed as super effective
                if (effectiveness >= 2) {
                    text.setVisible(true);
                    text.setText("Super Effective");
                } 
                // If the effectiveness is between 1 and 0 the move is displayed as not very effective
                else if (effectiveness < 1 && effectiveness > 0) {
                    text.setVisible(true);
                    text.setText("Not Very Effective");
                } 
                // If the effectiveness is 0 the move is displayed as no effect
                else if (effectiveness == 0) {
                    text.setVisible(true);
                    text.setText("No Effect");
                } 
                // The move is neutral
                else {
                    text.setVisible(false);
                }

            }
            // Adds the image of teh moves type
            String moveImage = "images/" + ((List<Move>) userMon.get("moves")).get(i).getTypeImage() + ".png";
            Image typeImage = new Image(moveImage);
            moveImgList.get(i).setImage(typeImage);
        }
    }
    /**
     * Disables the buttons used to chose moves
     */
    private void disableMoves(){
        //disable all the move buttons in the userMoveBox
        for (int i = 0; i < userMoveBox.getChildren().size(); i++) {
            Button moveButton = (Button) userMoveBox.getChildren().get(i);
            moveButton.setDisable(true);
        }
    }
    /**
     * Disables the buttons used to swap between pokemon
     */
    private void disableSwap(){
        //disable all the pokemon buttons in the teambox hbox
        for (int i = 0; i < teamBox.getChildren().size(); i++) {
            Button userMonButton = (Button) teamBox.getChildren().get(i);
            userMonButton.setDisable(true);
        }
    }

    /**
     * Checks if the player has won or lost the game.
     * checks the userTeamDead and oppTeamDead integer variables
     */
    private void checkWinLoss(){
        //check if all pokemon on the users team have fainted
        if(userTeamDead == 6){
            //if the player has logged in say the username in print
            if (loginController.loginStatus == true){
                Text text = new Text(loginController.username + " has lost the battle!");
                chatBox.getChildren().add(text);
            }
            //if the player is not logged in don't use username
            else{
                Text text = new Text("You have lost the battle!");
                chatBox.getChildren().add(text);
            }
            //disable the butotons to avoid errors
            disableMoves();
            disableSwap();
            //make back button appear
            backButton.setVisible(true);
            //add to loss count for account info
            lossCount++;
        }
         //check if all pokemon on the bots team have fainted
        if(oppTeamDead == 6){
            //if the player has logged in say the username in print
            if (loginController.loginStatus == true){
                Text text = new Text(loginController.username + " has won the battle!");
                chatBox.getChildren().add(text);
            }
            //if the player is not logged in don't use username
            else{
                Text text = new Text("User has won the battle!");
                chatBox.getChildren().add(text);
            }
            //disable the butotons to avoid errors
            disableMoves();
            disableSwap();
            //make back button appear
            backButton.setVisible(true);
            //add to win count for account info
            winCount++;
            
        }


    }
}
