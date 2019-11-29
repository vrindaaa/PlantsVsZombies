package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ChooseLevelController2 {
    @FXML
    void OnClickGoBack() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("ChooseLevel.fxml")));
    }
}
