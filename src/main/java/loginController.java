import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class loginController {

    //variable storing the text typed inside the username textfield
    public static String username;
    //variable storing the text typed inside the password textfield
    public static String password;
    //variable storing the line in the text file that matches what the user typed
    public static int matchingUserLine = -1;
    //variable storing if you are already logged in or not
    public static boolean loginStatus = false;
    // false for logging in, true for signing up
    boolean loginState = false;

    //declaring a bunch of variables

    FileReader fr;
    FileWriter fw;
    FileReader fr2;
    FileWriter fw2;
    BufferedReader br;
    BufferedReader br2;

    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    Button confirmButton;
    @FXML
    Button backButton;
    @FXML
    Button signUpButton;
    @FXML
    Label loginTitle;
    @FXML
    Label usernameWarning;
    @FXML
    Label warning2;
    @FXML
    Label signUpText;

    //making lists that store all the usernames and passwords from the text files
    List<String> usernameData = new ArrayList<String>();
    List<String> passwordData = new ArrayList<String>();

    /**
     * go back to homepage with the arrow button
     * @throws IOException
     */
    @FXML
    private void backPressed() throws IOException {
        App.setRoot("homeScreen");
    }   

    /**
     * stores all the text file info
     * @throws IOException
     */
    @FXML
    void checkFiles() throws IOException{

        String usernameLine;
        String passwordLine;
        
        //setting up the file reading and writing
        try {
            fr = new FileReader("src/main/resources/usernameInfo.txt");

            fr2 = new FileReader("src/main/resources/passwordInfo.txt");

            fw = new FileWriter("src/main/resources/usernameInfo.txt", true);
            fw2 = new FileWriter("src/main/resources/passwordInfo.txt", true);
        } 
        catch (FileNotFoundException exc) {
        }
        
        br = new BufferedReader(fr);
        br2 = new BufferedReader(fr2);

        //read through every line in the username file and store the data in a list
        while((usernameLine = br.readLine()) != null) {
            usernameData.add(usernameLine);
        }
        //read through every line in the password file and store the data in a list
        while((passwordLine = br2.readLine()) != null) {
            passwordData.add(passwordLine);
        }

        //the lists of account usernames and passwords
        System.out.println("Usernames: " + usernameData);
        System.out.println("Passwords: " + passwordData);

        fr.close();
        fr2.close();
    }

    /**
     * Sets up all of the displayed stuff when this screen is switched to 
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        //set up a bunch of labels, buttons, and text fields
        usernameField.requestFocus();
        loginTitle.setText("LOGIN");
        usernameWarning.setVisible(false);
        signUpButton.setVisible(true);
        signUpText.setVisible(true);
        warning2.setVisible(false);
        checkFiles();
    }

   /**
    * when enter is pressed after typing in username box
    * @throws IOException
    */
    @FXML
    public void switchToPass() throws IOException {
        passwordField.requestFocus();
    }

    /**
     * change the screen to prepare for signing up for a new account
     * @throws IOException
     */
    @FXML
    public void signUp() throws IOException {
        loginTitle.setText("SIGN UP");
        loginState = true;
        signUpButton.setVisible(false);
        signUpText.setVisible(false);
    }
    


    // 
    /**
     * When the confirm button is clicked, check the password information typed to see if it matches.
     * @throws IOException
     */
    @FXML
    public void checkInfo() throws IOException {

        int usernameCount = 0;
        //storing the username & password typed in the text fields into variables
        username = usernameField.getText();
        password = passwordField.getText();
        //if the user is logging in (not signing up)
        if (loginState == false) {

            //iterate through the list with username data
            for(int i = 0; i < usernameData.size(); i++){
                //increase a counter for each new index
                usernameCount += 1;
                //if username typed matches an index in the list
                if(username.equals(usernameData.get(i))){
                    //store the index that it matched at
                    matchingUserLine = usernameCount - 1;
                }
            }
            //if the password typed matches the password stored at the correct index, log in
            if (password.equals(passwordData.get(matchingUserLine))) {
                loginStatus = true;
                warning2.setVisible(false);
                //change back to home screen
                App.setRoot("homeScreen");
            } 

            else {
                usernameField.requestFocus();
                //if there is no match, reset the counter for next time for loop is used
                usernameCount = 0;
                //display a warning for wrong info
                warning2.setVisible(true);
            }
        }

        //if the user is signing up for a new account rather than loggin in 
        else if (loginState = true) {

            int checker = 0;
            boolean existingUsername = false;

            //check if the username already exists by iterating through the username data file
            for(int i = 0; i < usernameData.size(); i++){
                //increase the counter for each index
                checker += 1;
                //if there is a username match, display warning 
                if(username.equals(usernameData.get(i))){
                    usernameWarning.setVisible(true);
                    usernameField.requestFocus();
                    existingUsername = true;
                }
            }
            
            //once the system has confirmed that the username does not exist
            if((existingUsername == false) && (checker == usernameData.size())){
                loginStatus = true;
                //write the new username and password into the text files
                fw.write(username + '\n');
                fw2.write(password + '\n');
                fw.close();
                fw2.close();
                //switch back to homescreen
                App.setRoot("homeScreen");
            }

        }
    }
}