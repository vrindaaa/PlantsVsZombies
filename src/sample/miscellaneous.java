package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class miscellaneous {
    static class Animate{
        double startX = 0;
        double startY = 0;
        double endX = 0;
        double endY = 0;
        ImageView o;
        double duration;
        double incrementX, incrementY;
        public Animate(double startX, double startY, double endX, double endY, ImageView o, double duration) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.o = o;
            this.duration = duration;
            incrementX = (endX - startX)/(24*duration);
            incrementY = (endY - startY)/(24*duration);
            o.setX(startX);
            o.setY(startY);
        }
        public void start(){
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(o.getX() < endX)
                        o.setX(o.getX()+incrementX);
                    if(o.getY() < endY)
                        o.setY(o.getY()+incrementY);
                    if(o.getX() >= endX && o.getY() >= endY){
                        timer.cancel();
                        timer.purge();
                    }

                }
            },0,1000/24);
        }
        public void startLawnMower(Pane GamePagePane, ArrayList<Zombies.Zombie> rowZombies){
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(o.getX() < endX)
                                o.setX(o.getX()+incrementX);
                            if(o.getX() >= endX){
                                timer.cancel();
                                timer.purge();
                            }
                            for(int i=0; i<rowZombies.size(); i++){
                                Zombies.Zombie cur = rowZombies.get(i);
                                if(cur.getX()<=5+o.getX() && cur.getX()>= o.getX()-5){
                                    GamePagePane.getChildren().remove(cur);
                                    rowZombies.remove(cur);
                                    variables.curGame.noOfzombiesKilled+=1;
                                    cur.timer.cancel();
                                    cur.timer.purge();
                                }
                            }
                        }
                    });

                }
            },0,1000/24);
        }
    }
    static class sunToken{
        private ImageView sun;
        private int value;
        long delay;
        Pane GamepagePane;
        Label SunTokenLabel;
        sunToken(int value,Pane GamepagePane,Label SunTokenLabel,long delay,double startX, double startY){
            this.value = value;
            this.delay = delay;
            this.GamepagePane = GamepagePane;
            this.SunTokenLabel = SunTokenLabel;
            try {
                sun = new ImageView(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/sun.gif")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            sun.setX(startX);
            sun.setY(startY);
            sun.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                GamepagePane.getChildren().remove(sun);
                int old_val = Integer.parseInt(SunTokenLabel.getText());
                int new_val = old_val + value;
                SunTokenLabel.setText(Integer.toString(new_val));
                mouseEvent.consume();
            });
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GamepagePane.getChildren().remove(sun);
                        }
                    });
                }
            },delay);
            //assert sun != null;
        }

        public ImageView getSun() {
            return sun;
        }

        public int getValue() {
            return value;
        }
    }
    static class LawnMower implements Serializable {
        int rowNumber;
        boolean used;
        transient ImageView place;
        LawnMower(int row){
            rowNumber = row;
        }
        void mow(Pane GamePagePane, double x, double y, ArrayList<Zombies.Zombie> zombies) throws FileNotFoundException {
            place.setImage(null);
            ImageView val = new ImageView(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/lawn_mower.gif")));
            val.setX(x);
            val.setY(y+50);
            GamePagePane.getChildren().add(val);
            System.out.println("okay "+x+" "+y);
            Animate animation = new Animate(x, y+50, 750, y+50, val, 3);
            animation.startLawnMower(GamePagePane, zombies);
            Timer timer = new Timer();
            LawnMower temp = this;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GamePagePane.getChildren().remove(val);
                            temp.used = true;
                        }
                    });
                }
            }, 3000);

        }
    }

}
