package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveGamePage {
    @FXML
    ImageView HomeImage;
    @FXML
    Label SavedGameSlot1,SavedGameSlot2,SavedGameSlot3;
    @FXML
    void onMouseEnterHomeButton() throws FileNotFoundException {
        HomeImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/homebuttonyellow.png")));
    }
    @FXML
    void onMouseExitHomeButton() throws FileNotFoundException {
        HomeImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/homebuttongreen.png")));
    }
    @FXML
    void onButtonClick() throws IOException {
        Scene HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }
}
