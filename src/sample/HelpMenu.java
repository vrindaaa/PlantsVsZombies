package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class HelpMenu {
    @FXML
    void onButtonClick() throws IOException {
        Scene HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }
}
