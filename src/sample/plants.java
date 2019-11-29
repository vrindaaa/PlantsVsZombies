package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class plants {
    static abstract class plantCard{
        protected String name;
        protected int loadTime;
        protected ImageView cardImageView;
        protected int cost;
        public boolean isUnlocked;
        public String getName() {
            return name;
        }

        public int getLoadTime() {
            return loadTime;
        }

        public ImageView getCardImageView() {
            return cardImageView;
        }

        public int getCost() {
            return cost;
        }
    }

    static class sunflowerCard extends plantCard{
        sunflowerCard(boolean isUnlocked, ImageView cardImageView) throws FileNotFoundException {
            name = "Sunflower";
            loadTime = 5;
            this.isUnlocked = isUnlocked;
            this.cardImageView = cardImageView;
            cost = 50;
        }
    }

    static class peaShooterCard extends plantCard{
        peaShooterCard() throws FileNotFoundException {
            name = "PeaShooter";
            loadTime = 10;
            cardImageView = new ImageView(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/card_peashooter.png")));
            cost = 100;
        }
    }

    static class wallNutCard extends plantCard{
        wallNutCard(boolean isUnlocked, ImageView cardImageView, boolean isLoaded) throws FileNotFoundException {
            name = "WallNut";
            loadTime = 15;
            this.isUnlocked = isUnlocked;
            this.cardImageView = cardImageView;
            cost = 50;
        }
    }

    static class doublePeaShooterCard extends plantCard{
        doublePeaShooterCard(){
            name = "doublePeaShooter";
            loadTime= 10;
          //  cardImageView =  //TODO;
            cost = 200;

        }
    }

    static class chillyCard extends plantCard{
        chillyCard(){
            name = "chillyCard";
            loadTime = 6;
            //cardImageView = new ImageView(new Image(new FileInputStream("TODO")));
            cost = 125;
        }
    }

    static abstract class plant extends character{
        protected double health;
        protected double attackPower;
        public transient ImageView currentImageView;
        public int row = 0;
        public int col = 0;
        public String image_path;
        public boolean isAlive = true;
        public transient static int loadTime;
        public transient static boolean isAvailable = true;
        public boolean get_eaten(AnchorPane GamePagePane, double AttackZombie){
            health-=AttackZombie;
            if(health<=0){
                return true;
            }
            return false;
        }

        public void work(Pane GamePagePane, Label SunTokenLabel, ArrayList<ArrayList<Zombies.Zombie>> lawn_zombies) throws FileNotFoundException {
        }

        public ImageView getCurrentImageView() {
            return currentImageView;
        }

        public void setHealth(double health) {
            this.health = health;
        }

        public double getAttackPower() {
            return attackPower;
        }

        public double getHealth() {
            return health;
        }
    }

    static class SunFlower extends plant {

        SunFlower(ImageView plant_place, int row,Pane GamepagePane, Label SunTokenLabel, int col) {
            currentImageView = plant_place;
            this.row = row;
            this.col = col;
            health = 1000;
            attackPower = 0;
            image_path = "out/production/PVZ/sample/Graphics/sunflower_gif.gif";
            this.setMyX(plant_place.getLocalToSceneTransform().getTx());
            this.setMyY(plant_place.getLocalToSceneTransform().getTy());
            giveSun(GamepagePane,SunTokenLabel);
            loadTime = 5;
            isAvailable = true;
        }
        @Override
        public void work(Pane GamepagePane, Label SunTokenLabel, ArrayList<ArrayList<Zombies.Zombie>> lawn_zombies){
            giveSun(GamepagePane, SunTokenLabel);
        }

        public void giveSun(Pane GamepagePane, Label SunTokenLabel) {
           double x = currentImageView.getLocalToSceneTransform().getTx();
           double y = currentImageView.getLocalToSceneTransform().getTy();
           Timer timer = new Timer();
           timer.scheduleAtFixedRate(new TimerTask() {
               @Override
               public void run() {
                   Platform.runLater(new Runnable() {
                       @Override
                       public void run() {
                           if(variables.isGamePaused)
                               return;
                           miscellaneous.sunToken s = new miscellaneous.sunToken(25, GamepagePane, SunTokenLabel, 3000,x,y);
                           GamepagePane.getChildren().add(s.getSun());
                           miscellaneous.Animate animation = new miscellaneous.Animate(x, y, x, y+50, s.getSun(), 1);
                           animation.start();
                       }
                   });
               }
           },6000,10000);
        }
    }

    static class PeaShooter extends plant{
        PeaShooter(ImageView plant_place, int row, AnchorPane GamePagePane, ArrayList<ArrayList<Zombies.Zombie>> list_zombies, int col) throws FileNotFoundException {
            health = 2500;
            attackPower = 100;
            this.col = col;
            currentImageView = plant_place;
            this.row = row;
            image_path = "out/production/PVZ/sample/Graphics/peashooter_gif.gif";
            this.setMyX(plant_place.getLocalToSceneTransform().getTx());
            this.setMyY(plant_place.getLocalToSceneTransform().getTy());
            this.shoot(GamePagePane,list_zombies);
            loadTime = 7;
            isAvailable = true;
       }
       public void work(Pane GamePagePane, Label SunTokenLabel, ArrayList<ArrayList<Zombies.Zombie>> list_zombies) throws FileNotFoundException {
            shoot(GamePagePane, list_zombies);
       }
       public void shoot(Pane GamePagePane, ArrayList<ArrayList<Zombies.Zombie>> list_zombies) throws FileNotFoundException {
            //TODO
           double x = currentImageView.getLocalToSceneTransform().getTx() + 45;
           double y = currentImageView.getLocalToSceneTransform().getTy() + 10;
           Timer timer = new Timer();
           timer.scheduleAtFixedRate(new TimerTask() {
               @Override
               public void run() {
                   Platform.runLater(new Runnable() {
                       @Override
                       public void run() {
                           if(!isAlive){
                               timer.cancel();
                               timer.purge();
                           }
                           if(variables.isGamePaused)
                               return;
                           Pea pea = null;
                           if(list_zombies.get(row).size()>0){
                               try {
                                   pea = new Pea(x);
                               } catch (FileNotFoundException e) {
                                   e.printStackTrace();
                               }
                               GamePagePane.getChildren().add(pea);
                               pea.setX(x);
                               pea.setY(y);
                               pea.shoot(GamePagePane, list_zombies, row);
                           }
                       }
                   });
               }
           },0,3000);
        }
    }

//    static class Repeater extends PeaShooter{
//        @Override
//        public void shoot(){
//            //TODO
//        }
//    }

//    static class ChillyBomb extends plant{
//        ChillyBomb(){
//        }
//        public void explode(){
//            //TODO
//        }
//    }
//
//    static class Walnut extends plant{
//        Walnut(){
//        }
//    }

    static class Pea extends ImageView{
        Pea pea = this;
        double x;
        static double damage = 20;
        Pea(double x) throws FileNotFoundException {
            this.x = x;
            this.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/pea.png")));
        }
        void shoot(Pane GamePagePane, ArrayList<ArrayList<Zombies.Zombie>> list_zombies, int row){
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(!variables.isGamePaused){
                                pea.setX(pea.getX()+5);
                                for(int i=0; i<list_zombies.get(row).size(); i++){
                                    if(pea.getX()<list_zombies.get(row).get(i).getX()+5 && pea.getX()>list_zombies.get(row).get(i).getX()-5){
                                        //Hit Zombie
                                        list_zombies.get(row).get(i).get_hit(damage, list_zombies, row, i);
                                        pea.setX(800);
                                    }
                                }
                                if(pea.getX()>750){
                                    GamePagePane.getChildren().remove(pea);
                                    timer.cancel();
                                    timer.purge();
                                }
                            }
                        }
                    });
                }
            },0,40);

        }
    }
}
