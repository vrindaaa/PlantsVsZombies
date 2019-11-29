package sample;

import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class variables {
    static int sunTokenDelay = 30; //seconds
    static int sunTokenFallTime = 20000; //ms
    static boolean isGamePaused = false;
    static Stage PauseMenuStage;
    static GameClasses.User currentUser = null;
}
