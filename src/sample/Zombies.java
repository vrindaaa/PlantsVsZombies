package sample;

import javafx.css.Styleable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Zombies {
    static class Zombie extends character{
        double speed;
        double health = 100;
        double attackPower = 25;
        double x = 750;
        double y;
        int row;
        ArrayList<plants.plant> row_plants;
        Zombie(double y, int row, ArrayList<plants.plant> row_plants) throws FileNotFoundException {
            this.setImage(new Image(new FileInputStream("C:\\Users\\Shiv\\Desktop\\PlantsVsZombies-master\\PVZ\\src\\sample\\Graphics\\zombie_normal.gif")));
            this.setX(x);
            this.y = y;
            this.setY(y);
            this.row = row;
            this.row_plants = row_plants;

        }
        public void get_hit(double damage, ArrayList<ArrayList<Zombie>> lawn_zombie, int row, int pos){
            health-=damage;
            if(health<=0){
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

        public void move(AnchorPane GamePagePane){
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
            this.setX(this.getX()-0.2);
            if(this.getX()<100){
                this.setX(100);
            }
        }
    }
//    static class ConeZombie extends Zombie{
//        //TODO
//    }
//    static class FlagZombie extends Zombie{
//        //TODO
//    }
//    static class BucketZombie extends Zombie{
//        //TODO
//    }
//    static class StickZombie extends Zombie{
//        //TODO
//    }
//    static class RugbyZombie extends Zombie{
//        //TODO
//    }


}
