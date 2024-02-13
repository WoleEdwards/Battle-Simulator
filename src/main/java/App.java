
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    //instance variables
    private static Scene scene;
    public static Stage stage;

    /**
     * Loads the first screen with inputted dimensions
     * 
     * @param the stage to be loaded
     */
    @Override
    public void start(Stage stageIn) throws IOException {
        scene = new Scene(loadFXML("homeScreen"), 640, 480);
        stage = stageIn;
        stage.setScene(scene);
        stage.show();
    }

    /**
     * changes the scene that is currently loaded
     * 
     * @param the name of the fxml to be loaded as a string
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    /**
     * Loads the fxml 
     * 
     * @param the name of the fxml to be loaded as a string
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /**
     * main function which launches the scene
     * 
     * @param string array of whatever inputted
     */
    public static void main(String[] args) {
        launch();
    }

}