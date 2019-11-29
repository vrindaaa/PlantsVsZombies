package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class NewGameMenu {
    @FXML
    void onNewGameClick() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("ChooseLevel.fxml")));
    }
    @FXML
    void onLoadGameClick() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("SaveGamePage.fxml")));
    }
    @FXML
    void onLeaderBoardClick() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("UnderConstruction.fxml")));
    }
    @FXML
    void onHomeGameClick() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("HomePage.fxml")));
    }
}
