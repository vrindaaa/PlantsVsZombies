package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.fxml.FXMLLoader.load;
import static sample.variables.*;

public class Zombies {
    static class Zombie extends character{
        double speed;
        double health = 100;
        double attackPower = 25;
        double x = 750;
        double y;
        int row;
        transient Timer timer;
        miscellaneous.LawnMower rowLawnMower;
        String image_path = "out/production/PVZ/sample/Graphics/zombie_normal.gif";
        ArrayList<plants.plant> row_plants;
        ArrayList<Zombie> rowZombies;
        Zombie(double y, int row, ArrayList<plants.plant> row_plants, miscellaneous.LawnMower rowLawnMower, ArrayList<Zombie> rowZombies) throws FileNotFoundException {
            this.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/zombie_normal.gif")));
            this.setMyX(x);
            this.y = y;
            this.setMyY(y);
            this.row = row;
            this.row_plants = row_plants;
            this.rowLawnMower = rowLawnMower;
            this.rowZombies = rowZombies;

        }
        public void get_hit(double damage, ArrayList<ArrayList<Zombie>> lawn_zombie, int row, int pos){
            health-=damage;
            if(health<=0){
                curGame.noOfzombiesKilled+=1;
                lawn_zombie.get(row).remove(pos);
            }
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public void setAttackPower(double attackPower) {
            this.attackPower = attackPower;
        }

        public double getSpeed() {
            return speed;
        }

        public double getAttackPower() {
            return attackPower;
        }

        public void setHealth(double health) {
            this.health = health;
        }

        public double getHealth() {
            return health;
        }

        public void attack(){
            //TODO
        }

        public void move(AnchorPane GamePagePane) throws IOException {
            for(int i=0; i<row_plants.size(); i++){
                if(row_plants.get(i).getX()<this.getX()+5 && row_plants.get(i).getX()>this.getX()-5){
                    boolean val = row_plants.get(i).get_eaten(GamePagePane, attackPower);
                    if(val){
                        row_plants.get(i).getCurrentImageView().setImage(null);
                        row_plants.get(i).isAlive = false;
                        row_plants.remove(i);
                    }
                    return;
                }
            }
            this.setMyX(this.getX()-0.7);
            if(this.getX()<100){
                //this.setMyX(100);
                if(this.rowLawnMower.used){
                    //GameLost
                    for(int i=0; i<timerTaskZombies.size(); i++){
                        timerTaskZombies.get(i).cancel();
                        timerTaskZombies.get(i).purge();
                    }
                    timerTaskZombies = new ArrayList<>();
                    //variables.isGamePaused = false;
                    curGame = variables.Factory_New_Game.getNewGame();
                    variables.toStart = true;
                    Main.GameStage.setScene(load(getClass().getResource("GameOver.fxml")));
                    System.out.println("Game Lost");
                }
                else{
                    //GamePagePane.getChildren().remove(this);
                    rowZombies.remove(this);
                    curGame.noOfzombiesKilled+=1;
                    this.rowLawnMower.mow(GamePagePane, myX, myY, rowZombies);
                }
            }
        }
    }
    static class ConeZombie extends Zombie{
        //TODO

        public ConeZombie(double y, int row, ArrayList<plants.plant> row_plants, miscellaneous.LawnMower rowLawnMower, ArrayList<Zombie> rowZombies) throws FileNotFoundException {
            super(y, row, row_plants, rowLawnMower, rowZombies);
        }
    }
    static class FlagZombie extends Zombie{
        //TODO

        public FlagZombie(double y, int row, ArrayList<plants.plant> row_plants, miscellaneous.LawnMower rowLawnMower, ArrayList<Zombie> rowZombies) throws FileNotFoundException {
            super(y, row, row_plants, rowLawnMower, rowZombies);
        }
    }
    static class BucketZombie extends Zombie{
        //TODO

        public BucketZombie(double y, int row, ArrayList<plants.plant> row_plants, miscellaneous.LawnMower rowLawnMower, ArrayList<Zombie> rowZombies) throws FileNotFoundException {
            super(y, row, row_plants, rowLawnMower, rowZombies);
        }
    }
    static class StickZombie extends Zombie{
        //TODO

        public StickZombie(double y, int row, ArrayList<plants.plant> row_plants, miscellaneous.LawnMower rowLawnMower, ArrayList<Zombie> rowZombies) throws FileNotFoundException {
            super(y, row, row_plants, rowLawnMower, rowZombies);
        }
    }
    static class RugbyZombie extends Zombie{
        //TODO

        public RugbyZombie(double y, int row, ArrayList<plants.plant> row_plants, miscellaneous.LawnMower rowLawnMower, ArrayList<Zombie> rowZombies) throws FileNotFoundException {
            super(y, row, row_plants, rowLawnMower, rowZombies);
        }
    }


}
