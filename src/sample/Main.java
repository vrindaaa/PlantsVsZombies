package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Thread t1 = new Thread(new SocketProgram.runSocketServer());
        Thread t2 = new Thread(new SocketProgram.runSocketClient());
        t1.start();
        t2.start();
        launch(args);
    }
}
