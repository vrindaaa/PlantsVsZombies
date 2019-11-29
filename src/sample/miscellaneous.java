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
                sun = new ImageView(new Image(new FileInputStream("C:\\Users\\Shiv\\Desktop\\PlantsVsZombies-master\\PVZ\\src\\sample\\Graphics\\sun.gif")));
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
    static class LawnMower{
        boolean used;
        void mow(){}
    }

}
