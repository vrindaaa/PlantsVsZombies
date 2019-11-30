package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static javafx.fxml.FXMLLoader.load;
import static sample.GameClasses.Deserialize;
import static sample.GameClasses.Serialize;
import static sample.variables.curGame;
import static sample.variables.timerTaskZombies;

public class PauseMenu {
    @FXML
    Button ResumeGameButton, RestartLevelButton, SaveAndExitButton;
    @FXML
    void onClickResume() throws IOException {
        //variables.toStart = true;
        variables.isGamePaused = false;
        variables.PauseMenuStage.close();
        //Main.GameStage.setScene(load(getClass().getResource("GamePage.fxml")));
    }
    @FXML
    void onClickRestart() throws IOException {
        for(int i=0; i<timerTaskZombies.size(); i++){
            timerTaskZombies.get(i).cancel();
            timerTaskZombies.get(i).purge();
        }
        timerTaskZombies = new ArrayList<>();
        variables.isGamePaused = false;
        curGame = variables.Factory_New_Game.getNewGame();
        variables.toStart = true;
        Main.GameStage.setScene(load(getClass().getResource("GamePage.fxml")));
        variables.PauseMenuStage.close();
        curGame.noOfzombiesKilled = 0;
    }
    @FXML
    void onClickExit() throws IOException {
        for(int i=0; i<timerTaskZombies.size(); i++){
            timerTaskZombies.get(i).cancel();
            timerTaskZombies.get(i).purge();
        }
        GamePageController.zombieGenerator.cancel();
        GamePageController.zombieGenerator.purge();
        timerTaskZombies = new ArrayList<>();
        try {
            for(ArrayList<Zombies.Zombie> uu: curGame.listOflistOfZombies){
                for(Zombies.Zombie ele : uu){
                    System.out.println(ele.myX + " " + ele.myY);
                }
            }
            Serialize(curGame);
        } catch (GameClasses.UserAlreadyExistsException e) {
            e.printStackTrace();
        }
        try{
            GameClasses.allUsers a = Deserialize();
            System.out.println(a.getUsersList().size());
            GameClasses.User u = a.usersList.get(0);
            System.out.println(u.getSavedGames().size());
            GameClasses.Game g = u.savedGames.get(0);
            System.out.println("here also");
            for(ArrayList<Zombies.Zombie> uu: g.listOflistOfZombies){
                for(Zombies.Zombie ele : uu){
                    System.out.println(ele.myX + " " + ele.myY);
                }
            }
        }catch (Exception e){
        }
        variables.isGamePaused = false;
        Main.GameStage.setScene(load(getClass().getResource("HomePage.fxml")));
        variables.PauseMenuStage.close();
    }
}
