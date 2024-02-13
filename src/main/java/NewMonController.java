import java.io.IOException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NewMonController {
    @FXML
    Button p1, p2, p3, p4, p5, p6, p7, p, p9, p10, p12, p13, p14, p15, p16, p17, p18, p19, p20, leaveBtn;
    // Sets the FX id's of the buttons to be seen and edited
    
    private static String name;
    // Creates a private variable recordding the name of the pocket monster
    public static HashMap<String, Object> pMon;
    // Creates a public variable to store the information of the pocket monster with the availablility to be seen by other classes 

    /**
     * The previousPage method returns the user to the newTeam.FXML screen.
     * 
     * @throws IOException
     */
    @FXML
    private void previousPage() throws IOException {
        App.setRoot("newTeam");
        // Sends the screen back to the team builder screen
    }

    /**
     * The addMon method checks the button the user pressed and sets a public hashmap as the pocket monster.
     * 
     * @param event ; gets the event prior
     * @throws IOException
     */
    @FXML
    private void addMon(ActionEvent event) throws IOException {
        Button pockMon = (Button) event.getSource();
        // Sets the button that was pressed as a stored variable
        name = pockMon.getText();
        // Takes the name of pocketmonster assigned to that button
        pMon = getMon(name);
        // Calls on the getMon() function and sets whatever pocket monster the button was and stores this as a variable for the newTeam to check
        previousPage();
        // Calls on previousPage() method to return to the team builder 
    }

    /**
     * The getMon method takes the name given and creates a hashmap for the assigned name.
     * 
     * @param nameIn ; uses the name of the pocket monster chosen to create a hashmap
     * @return HashMap<String, Object> ; returns the hashmap of chosen pocket monster
     */
    public static HashMap<String, Object> getMon(String nameIn) {
        switch (nameIn) {
        // Checks each case for the name that was given
            case "Alakazam":
            // If the name was Alakazam runs the following code
                HashMap<String, Object> alakazam = new HashMap<String, Object>() {
                    {
                        put("name", "Alakazam");
                        put("type1", "Psychic");
                        put("type2", null);
                        put("fullHealth", 314);
                        put("health", 314);
                        put("originalAttack", 405.0);
                        put("originalDefence", 317.0);
                        put("originalSpeed", 372);
                        put("attack", 405.0);
                        put("defence", 317.0);
                        put("speed", 372);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/alakazamFront.png");
                        put("backImage", "images/alakazamBack.png");
                        put("moves", MoveChoices.getMoveCombo("Alakazam"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return alakazam;
                // Ends function returning this Hashmap

            case "Blastoise":
            // If the name was Blastoise runs the following code
                HashMap<String, Object> blastoise = new HashMap<String, Object>() {
                    {
                        put("name", "Blastoise");
                        put("type1", "Water");
                        put("type2", null);
                        put("fullHealth", 362);
                        put("health", 362);
                        put("originalAttack", 295.0);
                        put("originalDefence", 339.0);
                        put("originalSpeed", 280);
                        put("attack", 295.0);
                        put("defence", 339.0);
                        put("speed", 280);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/blastoiseFront.png");
                        put("backImage", "images/blastoiseBack.png");
                        put("moves", MoveChoices.getMoveCombo("Blastoise"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return blastoise;
                // Ends function returning this Hashmap

            case "Breloom":
            // If the name was Breloom runs the following code
                HashMap<String, Object> breloom = new HashMap<String, Object>() {
                    {
                        put("name", "Breloom");
                        put("type1", "Grass");
                        put("type2", "Fighting");
                        put("fullHealth", 324);
                        put("health", 324);
                        put("originalAttack", 394.0);
                        put("originalDefence", 284.0);
                        put("originalSpeed", 262);
                        put("attack", 394.0);
                        put("defence", 284.0);
                        put("speed", 262);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/breloomFront.png");
                        put("backImage", "images/breloomBack.png");
                        put("moves", MoveChoices.getMoveCombo("Breloom"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return breloom;
                // Ends function returning this Hashmap

            case "Charizard":
            // If the name was Charizard runs the following code
                HashMap<String, Object> charizard = new HashMap<String, Object>() {
                    {
                        put("name", "Charizard");
                        put("type1", "Fire");
                        put("type2", "Flying");
                        put("fullHealth", 360);
                        put("health", 360);
                        put("originalAttack", 348.0);
                        put("originalDefence", 295.0);
                        put("originalSpeed", 328);
                        put("attack", 348.0);
                        put("defence", 295.0);
                        put("speed", 328);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/charizardFront.png");
                        put("backImage", "images/charizardBack.png");
                        put("moves", MoveChoices.getMoveCombo("Charizard"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return charizard;
                // Ends function returning this Hashmap

            case "Crustle":
            // If the name was Crustle runs the following code
                HashMap<String, Object> crustle = new HashMap<String, Object>() {
                    {
                        put("name", "Crustle");
                        put("type1", "Bug");
                        put("type2", "Rock");
                        put("fullHealth", 344);
                        put("health", 344);
                        put("originalAttack", 339.0);
                        put("originalDefence", 383.0);
                        put("originalSpeed", 207);
                        put("attack", 339.0);
                        put("defence", 383.0);
                        put("speed", 207);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/crustleFront.png");
                        put("backImage", "images/crustleBack.png");
                        put("moves", MoveChoices.getMoveCombo("Crustle"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return crustle;
                // Ends function returning this Hashmap

            case "Electivire":
            // If the name was Electivire runs the following code
                HashMap<String, Object> electivire = new HashMap<String, Object>() {
                    {
                        put("name", "Electivire");
                        put("type1", "Electric");
                        put("type2", null);
                        put("fullHealth", 354);
                        put("health", 354);
                        put("originalAttack", 379.0);
                        put("originalDefence", 295.0);
                        put("originalSpeed", 317);
                        put("attack", 379.0);
                        put("defence", 295.0);
                        put("speed", 317);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/electivireFront.png");
                        put("backImage", "images/electivireBack.png");
                        put("moves", MoveChoices.getMoveCombo("Electivire"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return electivire;
                // Ends function returning this Hashmap

            case "Froslass":
            // If the name was Froslass runs the following code
                HashMap<String, Object> frosslass = new HashMap<String, Object>() {
                    {
                        put("name", "Froslass");
                        put("type1", "Ice");
                        put("type2", "Ghost");
                        put("fullHealth", 344);
                        put("health", 344);
                        put("originalAttack", 284.0);
                        put("originalDefence", 362.0);
                        put("originalSpeed", 350);
                        put("attack", 284.0);
                        put("defence", 262.0);
                        put("speed", 350);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/froslassFront.png");
                        put("backImage", "images/froslassBack.png");
                        put("moves", MoveChoices.getMoveCombo("Froslass"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return frosslass;
                // Ends function returning this Hashmap

            case "Gardevoir":
            // If the name was Gardevoir runs the following code
                HashMap<String, Object> gardevoir = new HashMap<String, Object>() {
                    {
                        put("name", "Gardevoir");
                        put("type1", "Psychic");
                        put("type2", "Fairy");
                        put("fullHealth", 340);
                        put("health", 340);
                        put("originalAttack", 383.0);
                        put("originalDefence", 361.0);
                        put("originalSpeed", 284);
                        put("attack", 383.0);
                        put("defence", 361.0);
                        put("speed", 284);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/gardevoirFront.png");
                        put("backImage", "images/gardevoirBack.png");
                        put("moves", MoveChoices.getMoveCombo("Gardevoir"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return gardevoir;
                // Ends function returning this Hashmap

            case "Gengar":
            // If the name was Gengar runs the following code
                HashMap<String, Object> gengar = new HashMap<String, Object>() {
                    {
                        put("name", "Gengar");
                        put("type1", "Ghost");
                        put("type2", "Poison");
                        put("fullHealth", 324);
                        put("health", 324);
                        put("originalAttack", 394.0);
                        put("originalDefence", 273.0);
                        put("originalSpeed", 350);
                        put("attack", 394.0);
                        put("defence", 273.0);
                        put("speed", 350);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/gengarFront.png");
                        put("backImage", "images/gengarBack.png");
                        put("moves", MoveChoices.getMoveCombo("Gengar"));

                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return gengar;
                // Ends function returning this Hashmap

            case "Grimmsnarl":
            // If the name was Grimmsnarl runs the following code
                HashMap<String, Object> grimmsnarl = new HashMap<String, Object>() {
                    {
                        put("name", "Grimmsnarl");
                        put("type1", "Dairy");
                        put("type2", "Fairy");
                        put("fullHealth", 394);
                        put("health", 394);
                        put("originalAttack", 372.0);
                        put("originalDefence", 273.0);
                        put("originalSpeed", 240);
                        put("attack", 372.0);
                        put("defence", 273.0);
                        put("speed", 240);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/grimmsnarlFront.png");
                        put("backImage", "images/grimmsnarlBack.png");
                        put("moves", MoveChoices.getMoveCombo("Grimmsnarl"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return grimmsnarl;
                // Ends function returning this Hashmap

            case "Lucario":
            // If the name was Lucario runs the following code
                HashMap<String, Object> lucario = new HashMap<String, Object>() {
                    {
                        put("name", "Lucario");
                        put("type1", "Fighting");
                        put("type2", "Steel");
                        put("fullHealth", 344);
                        put("health", 344);
                        put("originalAttack", 361.0);
                        put("originalDefence", 262.0);
                        put("originalSpeed", 306);
                        put("attack", 361.0);
                        put("defence", 262.0);
                        put("speed", 306);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/lucarioFront.png");
                        put("backImage", "images/lucarioBack.png");
                        put("moves", MoveChoices.getMoveCombo("Lucario"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return lucario;
                // Ends function returning this Hashmap

            case "Mamoswine":
            // If the name was Mamoswine runs the following code
                HashMap<String, Object> mamoswine = new HashMap<String, Object>() {
                    {
                        put("name", "Mamoswine");
                        put("type1", "Ice");
                        put("type2", "Ground");
                        put("fullHealth", 424);
                        put("health", 424);
                        put("originalAttack", 394.0);
                        put("originalDefence", 284.0);
                        put("originalSpeed", 284);
                        put("attack", 394.0);
                        put("defence", 284.0);
                        put("speed", 284);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/mamoswineFront.png");
                        put("backImage", "images/mamoswineBack.png");
                        put("moves", MoveChoices.getMoveCombo("Mamoswine"));

                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return mamoswine;
                // Ends function returning this Hashmap

            case "Pidgeot":
            // If the name was Pidgeot runs the following code
                HashMap<String, Object> pidgeot = new HashMap<String, Object>() {
                    {
                        put("name", "Pidgeot");
                        put("type1", "Normal");
                        put("type2", "Flying");
                        put("fullHealth", 370);
                        put("health", 370);
                        put("originalAttack", 284.0);
                        put("originalDefence", 273.0);
                        put("originalSpeed", 331);
                        put("attack", 284.0);
                        put("defence", 273.0);
                        put("speed", 331);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/pidgeotFront.png");
                        put("backImage", "images/pidgeotBack.png");
                        put("moves", MoveChoices.getMoveCombo("Pidgeot"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return pidgeot;
                // Ends function returning this Hashmap

            case "Sharpedo":
            // If the name was Sharpedo runs the following code
                HashMap<String, Object> sharpedo = new HashMap<String, Object>() {
                    {
                        put("name", "Sharpedo");
                        put("type1", "Water");
                        put("type2", "Dark");
                        put("fullHealth", 344);
                        put("health", 344);
                        put("originalAttack", 372.0);
                        put("originalDefence", 196.0);
                        put("originalSpeed", 317);
                        put("attack", 372.0);
                        put("defence", 196.0);
                        put("speed", 317);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/sharpedoFront.png");
                        put("backImage", "images/sharpedoBack.png");
                        put("moves", MoveChoices.getMoveCombo("Sharpedo"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return sharpedo;
                // Ends function returning this Hashmap

            case "Snorlax":
            // If the name was Snorlax runs the following code
                HashMap<String, Object> snorlax = new HashMap<String, Object>() {
                    {
                        put("name", "Snorlax");
                        put("type1", "Normal");
                        put("type2", null);
                        put("fullHealth", 524);
                        put("health", 524);
                        put("originalAttack", 350.0);
                        put("originalDefence", 350.0);
                        put("originalSpeed", 174);
                        put("attack", 350.0);
                        put("defence", 350.0);
                        put("speed", 174);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/snorlaxFront.png");
                        put("backImage", "images/snorlaxBack.png");
                        put("moves", MoveChoices.getMoveCombo("Snorlax"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return snorlax;
                // Ends function returning this Hashmap

            case "Steelix":
            // If the name was Steelix runs the following code
                HashMap<String, Object> steelix = new HashMap<String, Object>() {
                    {
                        put("name", "Steelix");
                        put("type1", "Steel");
                        put("type2", "Ground");
                        put("fullHealth", 354);
                        put("health", 354);
                        put("originalAttack", 295.0);
                        put("originalDefence", 548.0);
                        put("originalSpeed", 174);
                        put("attack", 295.0);
                        put("defence", 548.0);
                        put("speed", 174);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/steelixFront.png");
                        put("backImage", "images/steelixBack.png");
                        put("moves", MoveChoices.getMoveCombo("Steelix"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return steelix;
                // Ends function returning this Hashmap

            case "Turtonator":
            // If the name was Turtonator runs the following code
                HashMap<String, Object> turtonator = new HashMap<String, Object>() {
                    {
                        put("name", "Turtonator");
                        put("type1", "Fire");
                        put("type2", "Dragon");
                        put("fullHealth", 324);
                        put("health", 324);
                        put("originalAttack", 309.0);
                        put("originalDefence", 405.0);
                        put("originalSpeed", 188);
                        put("attack", 309.0);
                        put("defence", 405.0);
                        put("speed", 188);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/turtonatorFront.png");
                        put("backImage", "images/turtonatorBack.png");
                        put("moves", MoveChoices.getMoveCombo("Turtonator"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return turtonator;
                // Ends function returning this Hashmap

            case "Tyrantrum":
            // If the name was Tyrantrum runs the following code
                HashMap<String, Object> tyrantrum = new HashMap<String, Object>() {
                    {
                        put("name", "Tyrantrum");
                        put("type1", "Rock");
                        put("type2", "Dragon");
                        put("fullHealth", 368);
                        put("health", 368);
                        put("originalAttack", 375.0);
                        put("originalDefence", 370.0);
                        put("originalSpeed", 265);
                        put("attack", 375.0);
                        put("defence", 370.0);
                        put("speed", 265);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/tyrantrumFront.png");
                        put("backImage", "images/tyrantrumBack.png");
                        put("moves", MoveChoices.getMoveCombo("Tyrantrum"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return tyrantrum;
                // Ends function returning this Hashmap

            case "Venusaur":
            // If the name was Venusaur runs the following code
                HashMap<String, Object> venusaur = new HashMap<String, Object>() {
                    {
                        put("name", "Venusaur");
                        put("type1", "Grass");
                        put("type2", "Poison");
                        put("fullHealth", 364);
                        put("health", 364);
                        put("originalAttack", 328.0);
                        put("originalDefence", 328.0);
                        put("originalSpeed", 284);
                        put("attack", 328.0);
                        put("defence", 328.0);
                        put("speed", 284);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/venusaurFront.png");
                        put("backImage", "images/venusaurBack.png");
                        put("moves", MoveChoices.getMoveCombo("Venusaur"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return venusaur;
                // Ends function returning this Hashmap

            case "Vikavolt":
            // If the name was Vikavolt runs the following code
                HashMap<String, Object> vikavolt = new HashMap<String, Object>() {
                    {
                        put("name", "Vikavolt");
                        put("type1", "Grass");
                        put("type2", "Poison");
                        put("fullHealth", 364);
                        put("health", 364);
                        put("originalAttack", 328.0);
                        put("originalDefence", 328.0);
                        put("originalSpeed", 284);
                        put("attack", 328.0);
                        put("defence", 328.0);
                        put("speed", 284);
                        put("poison", false);
                        put("burn", false);
                        put("sleep", false);
                        put("fainted", false);
                        put("frontImage", "images/vikavoltFront.png");
                        put("backImage", "images/vikavoltBack.png");
                        put("moves", MoveChoices.getMoveCombo("Vikavolt"));
                    }
                };
                // Creates a hashmap with all the necessary stats and information of this pocket monster
                return vikavolt;
                // Ends function returning this Hashmap
        }
        return null;
        // Returns null if no case is fufilled ; Should never happen
    }
}