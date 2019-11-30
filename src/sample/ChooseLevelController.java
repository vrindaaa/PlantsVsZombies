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
    ImageView HomeImage, Level1ButtonImage,Level2ButtonImage,Level3ButtonImage,Lock1,Lock2;
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
    void onMouseEnterLabel1() throws FileNotFoundException {
        Level1ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton_highlighted.png")));
    }
    @FXML
    void onMouseExitLabel1() throws FileNotFoundException {
        Level1ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton.png")));
    }
    @FXML
    void PlayGameButtonClicked(MouseEvent event) throws IOException {
        variables.currentLevel = Level.getLevel(1);
        variables.curGame.cur_level = 1;
        Scene HomePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }
    @FXML
    void OnClickGoBack() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("ChooseLevel2.fxml")));
    }

    @FXML
    void onMouseEnterLabel2() throws FileNotFoundException {
        Level2ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton_highlighted.png")));
    }
    @FXML
    void onMouseExitLabel2() throws FileNotFoundException {
        Level2ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton.png")));
    }
    @FXML
    void onMouseEnterLabel3() throws FileNotFoundException {
        Level3ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton_highlighted.png")));
    }
    @FXML
    void onMouseExitLabel3() throws FileNotFoundException {
        Level3ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton.png")));
    }

    @FXML
    void onLevel2CLick() throws IOException {
        if(variables.currentUser.level >= 2) {
            variables.curGame.cur_level = 2;
            variables.currentLevel = Level.getLevel(2);
            Scene HomePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
            Main.GameStage.setScene(HomePage);
        }
    }

    @FXML
    void onLevel3Click() throws IOException {
        if(variables.currentUser.level >= 3) {
            variables.curGame.cur_level = 3;
            variables.currentLevel = Level.getLevel(3);
            Scene HomePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
            Main.GameStage.setScene(HomePage);
        }
    }

    @FXML
    void onMouseEnter(){
        int level = variables.currentUser.level;
        if(level >= 2){
            Lock1.setOpacity(0);
        }else{
            Lock1.setOpacity(0.75);
        }
        if(level >= 3){
            Lock2.setOpacity(0);
        }else{
            Lock2.setOpacity(0.75);
        }
    }


}
