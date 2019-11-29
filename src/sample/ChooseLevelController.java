package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChooseLevelController {
    @FXML
    ImageView HomeImage, Level1ButtonImage;
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
    @FXML
    void onMouseEnterLabel() throws FileNotFoundException {
        Level1ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton_highlighted.png")));
    }
    @FXML
    void onMouseExitLabel() throws FileNotFoundException {
        Level1ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton.png")));
    }
    @FXML
    void PlayGameButtonClicked(MouseEvent event) throws IOException {
        Scene HomePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }
    @FXML
    void OnClickGoBack() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("ChooseLevel2.fxml")));
    }
}
