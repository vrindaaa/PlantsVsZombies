package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    //hi
    boolean isdone = false;
    @FXML
    AnchorPane HomePageAnchorPane;
    @FXML
    Label UsernameLabel,LevelDisplayLabel;
    @FXML
    ImageView AdventureButton;
    @FXML
    ImageView NewUserButton, MiniGameButton, SurvivalButton;
    @FXML
    void PlayGameButtonClicked(MouseEvent event) throws IOException {
        Scene HomePage = FXMLLoader.load(getClass().getResource("NewGameMenu.fxml"));
        Main.GameStage.setScene(HomePage);
    }
    @FXML
    void UnderConstructionButtonClicked(MouseEvent event) throws IOException {
        Scene HomePage = FXMLLoader.load(getClass().getResource("UnderConstruction.fxml"));
        Main.GameStage.setScene(HomePage);
    }
    @FXML
    void onMouseEnterAdventureButton() throws FileNotFoundException {
        AdventureButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_Adventure_highlight.png")));
    }
    @FXML
    void onMouseExitAdventureButton() throws FileNotFoundException {
            AdventureButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_Adventure_button.png")));
    }
    @FXML
    void onMouseEnterNewUserButton() throws FileNotFoundException {
        NewUserButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_WoodSign2_press.png")));
    }
    @FXML
    void onMouseExitNewUserButton() throws FileNotFoundException {
        NewUserButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_WoodSign2.png")));
    }
    @FXML
    void onMouseEnterMiniGameButton() throws FileNotFoundException {
        MiniGameButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_Survival_highlight.png")));
    }
    @FXML
    void onMouseExitMiniGameButton() throws FileNotFoundException {
        MiniGameButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_Survival_button.png")));
    }
    @FXML
    void onMouseEnterSurvivalButton() throws FileNotFoundException {
        SurvivalButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_vasebreaker_highlight.png")));
    }
    @FXML
    void onMouseExitSurvivalButton() throws FileNotFoundException {
        SurvivalButton.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/SelectorScreen_Vasebreaker_button.png")));
    }
    @FXML
    void onClickHelp() throws IOException {
        AnchorPane HomePageP = FXMLLoader.load(getClass().getResource("HelpMenu.fxml"));
        Scene HomePage = new Scene(HomePageP);
        Main.GameStage.setScene(HomePage);
    }
    @FXML
    void quit(){
        Main.GameStage.close();
    }
    @FXML
    void OnMouseEnter(){
        if(variables.currentUser != null){
            LevelDisplayLabel.setText(Integer.toString(variables.currentUser.getLevel()));
            UsernameLabel.setText(variables.currentUser.getName());
        }
        if(!isdone){
        Media sound = new Media(new File("out/production/PVZ/sample/Graphics/menu.wav").toURI().toString());
        //Media sound = new Media("C:\\Users\\Shiv\\Desktop\\PlantsVsZombies-master\\PVZ\\menu.wav");
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        MediaView mv = new MediaView(mediaPlayer);
        HomePageAnchorPane.getChildren().add(mv);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(1);
        mediaPlayer.play();
        isdone = true;}

    }
}
