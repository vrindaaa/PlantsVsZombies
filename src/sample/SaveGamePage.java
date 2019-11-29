package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.fxml.FXMLLoader.load;

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
        Scene HomePage = load(getClass().getResource("HomePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }

    @FXML
    void onMouseEnter(){
        ArrayList<GameClasses.Game> games = variables.currentUser.savedGames;
        try{
            SavedGameSlot1.setText(games.get(0).id);
        }catch (Exception e){}
        try {
            SavedGameSlot2.setText(games.get(1).id);
        }catch (Exception e){}
        try {
            SavedGameSlot3.setText(games.get(2).id);
        }catch (Exception e){}
    }
    @FXML
    void OnClickLabel1(){
        variables.toStart = true;
        try{
            GameClasses.Game g = variables.currentUser.savedGames.get(0);
            variables.curGame = g;
            Main.GameStage.setScene(load(getClass().getResource("GamePage.fxml")));
        }catch (Exception e){
            System.out.println("No Saved game here");
        }
    }
    @FXML
    void OnClickLabel2(){
        variables.toStart = true;
        try{
            GameClasses.Game g = variables.currentUser.savedGames.get(1);
            variables.curGame = g;
            Main.GameStage.setScene(load(getClass().getResource("GamePage.fxml")));
        }catch (Exception e){
            System.out.println("No Saved game here");
        }
    }
    @FXML
    void OnClickLabel3(){
        variables.toStart = true;
        try{
            GameClasses.Game g = variables.currentUser.savedGames.get(2);
            variables.curGame = g;
            Main.GameStage.setScene(load(getClass().getResource("GamePage.fxml")));
        }catch (Exception e){
            System.out.println("No Saved game here");
        }
    }

}
