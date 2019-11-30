package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChooseLevelController2 {
    @FXML
    ImageView Level4ButtonImage,Level5ButtonImage,Level6ButtonImage,Lock4,Lock5,Lock6;
    @FXML
    void OnClickGoBack() throws IOException {
        Main.GameStage.setScene(FXMLLoader.load(getClass().getResource("ChooseLevel.fxml")));
    }
    @FXML
    void onMouseEnterLabel4() throws FileNotFoundException {
        Level4ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton_highlighted.png")));
    }
    @FXML
    void onMouseExitLabel4() throws FileNotFoundException {
        Level4ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton.png")));
    }
    @FXML
    void onMouseEnterLabel5() throws FileNotFoundException {
        Level5ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton_highlighted.png")));
    }
    @FXML
    void onMouseExitLabel5() throws FileNotFoundException {
        Level5ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton.png")));
    }
    @FXML
    void onMouseEnterLabel6() throws FileNotFoundException {
        Level6ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton_highlighted.png")));
    }
    @FXML
    void onMouseExitLabel6() throws FileNotFoundException {
        Level6ButtonImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/NormalButton.png")));
    }
    @FXML
    void onLevel4CLick() throws IOException {
        if(variables.currentUser.level >= 4) {
            variables.curGame.cur_level = 4;
            variables.currentLevel = Level.getLevel(4);
            Scene HomePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
            Main.GameStage.setScene(HomePage);
        }
    }
    @FXML
    void onLevel5CLick() throws IOException {
        if(variables.currentUser.level >= 5) {
            variables.curGame.cur_level = 5;
            variables.currentLevel = Level.getLevel(5);
            Scene HomePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
            Main.GameStage.setScene(HomePage);
        }
    }
    @FXML
    void onLevel6CLick() throws IOException {
        if(variables.currentUser.level >= 6) {
            variables.curGame.cur_level = 6;
            variables.currentLevel = Level.getLevel(6);
            Scene HomePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
            Main.GameStage.setScene(HomePage);
        }
    }
    @FXML
    void onMouseEnter(){
        int level = variables.currentUser.level;
        if(level >= 4){
            Lock4.setOpacity(0);
        }else{
            Lock4.setOpacity(0.75);
        }
        if(level >= 5){
            Lock5.setOpacity(0);
        }else{
            Lock5.setOpacity(0.75);
        }
        if(level >= 6){
            Lock6.setOpacity(0);
        }else{
            Lock6.setOpacity(0.75);
        }
    }
}
