package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class PauseMenu {
    @FXML
    Button ResumeGameButton, RestartLevelButton, SaveAndExitButton;
    @FXML
    void onClickResume() throws IOException {
        variables.isGamePaused = false;
        variables.PauseMenuStage.close();
        //Main.GameStage.setScene(load(getClass().getResource("GamePage.fxml")));
    }
    @FXML
    void onClickExit() throws IOException {
        Main.GameStage.setScene(load(getClass().getResource("HomePage.fxml")));
    }
}
