package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

import static javafx.fxml.FXMLLoader.load;

public class Main extends Application {
    static Stage GameStage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception{

        //Scene HomePage = load(getClass().getResource("HomePage.fxml"));
        Scene HomePage = load(getClass().getResource("newUserPage.fxml"));
        GameStage.setTitle("Plants Vs Zombies");
        GameStage.setScene(HomePage);
        GameStage.show();
        String musicFile = "menu.wav";     // For example

    }



    public static void main(String[] args) {
        launch(args);
       // trying.doit();
    }
}
