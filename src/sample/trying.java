package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class trying {
    private java.util.concurrent.ExecutorService ExecutorService;

    static class generateSun implements Runnable {
        double startX,startY;
        String aa;
        //has info about parent stops when parent dies (TODO)
        Pane pane;

        public generateSun(double startX, double startY, Pane pane,String aa) {
            this.aa = aa;
            this.startX = startX;
            this.startY = startY;
            this.pane = pane;
        }
        @Override
        public void run(){
            //while(true) {

                System.out.println(aa);
                ImageView a = new ImageView();
                try {
                    //a.setImage(new Image(new FileInputStream("$Graphics\\pea.png" )));
                    a.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/pea.png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
               // a.setX(startX);
                //a.setY(startY);
                System.out.println("aaaaa");
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(7*(750-startX)));
            translateTransition.setNode(a);
            translateTransition.setByY(750-startX);
            translateTransition.setFromX(startX);
            translateTransition.setFromY(startY);
            translateTransition.setAutoReverse(false);
            translateTransition.play();
                pane.getChildren().add(a);
                System.out.println("aaaaa");
                Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                    @Override
                    public void run(){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                pane.getChildren().remove(a);
                                pane.getChildren().remove(a);
                            }
                        });
                    }
                },1000);
            //}
        }
    }

    static class runAThread extends Thread{
        Runnable inpThread;
        runAThread(Runnable inpThread){
            this.inpThread = inpThread;
        }

        @Override
        public void run(){
            new Thread(inpThread).start();
        }
    }

    static public void doit(double x, double y,Pane pane){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new generateSun(x,y,pane,"hi"));
            }
        },0,2000);
    }

}
