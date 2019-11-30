package sample;

import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;

import static javafx.fxml.FXMLLoader.load;

public class variables {
    static int sunTokenDelay = 10; //seconds
    static int sunTokenFallTime = 20000; //ms
    static double currentProgressBar = 0;
    static ArrayList<Timer> timerTaskZombies = new ArrayList<Timer>();
    static boolean isGamePaused = false;
    static Stage PauseMenuStage;
    static GameClasses.User currentUser = null;
    static boolean toStart = true;
    static class Factory_New_Game{
        static GameClasses.Game getNewGame(){
            ArrayList<ArrayList<Zombies.Zombie>> lawn_zombies2 = new ArrayList<>();
            ArrayList<ArrayList<plants.plant>> lawn_plants2 = new ArrayList<>();
            ArrayList<miscellaneous.LawnMower> listOfLawnMowers = new ArrayList<>();
            for(int i=0; i<5; i++){
                lawn_zombies2.add(new ArrayList<Zombies.Zombie>());
                lawn_plants2.add(new ArrayList<plants.plant>());
                listOfLawnMowers.add(new miscellaneous.LawnMower(i));
            }
            return new GameClasses.Game(0,lawn_zombies2,lawn_plants2, "50", listOfLawnMowers);
        }
    }
    static Level currentLevel = Level.getLevel(1);
    static GameClasses.Game curGame = Factory_New_Game.getNewGame();
}
