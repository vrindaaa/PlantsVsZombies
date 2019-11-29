package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import sample.Zombies.*;
import sample.miscellaneous.*;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.variables.*;
import sample.plants.*;
import static javafx.fxml.FXMLLoader.load;
import static sample.variables.sunTokenFallTime;

public class GamePageController {
    @FXML
    public AnchorPane GamepagePane;
    @FXML
    ImageView a1,a2,a3,a4,a5,a6,a7,a8,a9;
    @FXML
    ImageView b1,b2,b3,b4,b5,b6,b7,b8,b9;
    @FXML
    ImageView c1,c2,c3,c4,c5,c6,c7,c8,c9;
    @FXML
    ImageView d1,d2,d3,d4,d5,d6,d7,d8,d9;
    @FXML
    ImageView e1,e2,e3,e4,e5,e6,e7,e8,e9;
    @FXML
    ImageView SunCard, PeaShooterCard, WallnutCard, lockWallnut;
    @FXML
    ProgressBar progressBar;
    Level currentLevel = null;
    ArrayList<ArrayList<Zombie>> lawn_zombies = new ArrayList<>();
    ArrayList<ArrayList<plant>> lawn_plants = new ArrayList<>();
    wallNutCard card_wallnut = null;
    @FXML
    Label SunTokenLabel;
    double[] row_coordinates = {0, 60, 130, 205, 280};
    Random rand = new Random();
    sun s = new sun();
    Thread t = new Thread(s);
    Thread ok = new Thread(new generate_sun());
    progress_1 temppp = new progress_1();
    Thread t2 = new Thread(temppp);
    Thread ok2 = new Thread(new progress_2());

    public GamePageController() throws FileNotFoundException {
    }

    void start_game() throws FileNotFoundException {
        currentLevel = Level.getLevel(1);
        card_wallnut = new wallNutCard(currentLevel.WallNutUnlocked, WallnutCard, true);
        setCards();
        for(int i=0; i<5; i++){
            lawn_zombies.add(new ArrayList<Zombie>());
            lawn_plants.add(new ArrayList<plant>());
        }
        //Generating Zombies
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            generate_zombie();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        },10000,50000);
    }
    void generate_zombie() throws FileNotFoundException {
        int pos = rand.nextInt(5);
        double val_y = row_coordinates[pos];
        Zombie z = new Zombie(val_y, pos, lawn_plants.get(pos));
        lawn_zombies.get(pos).add(z);
        GamepagePane.getChildren().add(z);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    z.move(GamepagePane);
                    if(z.getHealth()<=0){
                        GamepagePane.getChildren().remove(z);
                    }
                });
            }
        },0,20);
    }
    //Main Game Start
    @FXML
    private void start_sun() throws FileNotFoundException {
        start_game();
        if(!t.isAlive()){
            t.start();
        }
        if(!t2.isAlive()){
            t2.start();
        }
    }
    ArrayList<ImageView> getLawn(){
        ArrayList<ImageView> tiles = new ArrayList<ImageView>();
        tiles.addAll(Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8,a9));
        tiles.addAll(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9));
        tiles.addAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9));
        tiles.addAll(Arrays.asList(d1,d2,d3,d4,d5,d6,d7,d8,d9));
        tiles.addAll(Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9));
        return tiles;
    }
    public static boolean isEmpty(ImageView imageView) {
        Image image = imageView.getImage();
        return image == null || image.isError();
    }
    @FXML
    private void HandleDragSunflowerCard(MouseEvent event) throws FileNotFoundException {
        Dragboard db = SunCard.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString("sunflower_gif.gif");
        cb.putImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/sunflowercard_compressed.jpg")));
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void HandleDragPeaShooterCard(MouseEvent event) throws FileNotFoundException {
        Dragboard db = PeaShooterCard.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString("peashooter_gif.gif");
        cb.putImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/peashootercard_compressed.jpg")));
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void HandleDragWallnutCard(MouseEvent event) throws FileNotFoundException {
        if(card_wallnut.isUnlocked){
        Dragboard db = WallnutCard.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString("walnut_gif.gif");
        cb.putImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/tallnutcard_compressed.jpg")));
        db.setContent(cb);
        event.consume();
        }
    }
    @FXML
    private void onDragOver(DragEvent event){
        if(event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    void onClickPauseButton() throws IOException {
        Main.GameStage.setScene(load(getClass().getResource("PauseMenu.fxml")));
    }
    @FXML
    private void imageviewdragdropped(DragEvent event) throws FileNotFoundException {
        Dragboard db = event.getDragboard();
        Object current = event.getSource();
        ArrayList<ImageView> lawn = getLawn();
        for (int i=0; i<lawn.size(); i++) {
            if (current.equals((Object) lawn.get(i))) {
                if (!isEmpty(lawn.get(i))) {
                    System.out.println("Plant already exists!");
                    return;
                }
                if (db.hasImage()) {
                    String path = "out/production/PVZ/sample/Graphics/" + db.getString();
                    Image myPlant = new Image(new FileInputStream(path));
                    int row = i /9;
                    if (db.getString().equals("peashooter_gif.gif")) {
                        if(Integer.parseInt(SunTokenLabel.getText())>=100){
                            SunTokenLabel.setText(String.valueOf(Integer.parseInt(SunTokenLabel.getText())-100));
                        }else{
                            return;
                        }
                        PeaShooter cur_plant = new PeaShooter(lawn.get(i), row, GamepagePane,lawn_zombies);
                        lawn_plants.get(row).add(cur_plant);
                        lawn.get(i).setImage(myPlant);
                    }
                    if (db.getString().equals("sunflower_gif.gif")){
                        if(Integer.parseInt(SunTokenLabel.getText())>=25){
                            SunTokenLabel.setText(String.valueOf(Integer.parseInt(SunTokenLabel.getText())-25));
                        }else{
                            return;
                        }
                        SunFlower cur_plant = new SunFlower(lawn.get(i), row, GamepagePane, SunTokenLabel);
                        lawn_plants.get(row).add(cur_plant);
                        lawn.get(i).setImage(myPlant);
                    }
                    return;
                }
            }
        }
        event.consume();
    }

    void setCards(){
        if(!card_wallnut.isUnlocked){
            lockWallnut.setOpacity(1);
        }
    }

    class generate_sun implements Runnable{
        @Override
        public void run(){
            sunToken sun = new sunToken(25,GamepagePane,SunTokenLabel,sunTokenFallTime - 2000,0,0);
            double x = (rand.nextDouble()*400)+100;
            double y = 0;
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(sunTokenFallTime));
            translateTransition.setNode(sun.getSun());
            translateTransition.setByY(500);
            translateTransition.setCycleCount(1);
            translateTransition.setFromX(x);
            translateTransition.setFromY(y);
            translateTransition.setAutoReverse(false);
            translateTransition.play();
            GamepagePane.getChildren().add(sun.getSun());
        }
    }
    class sun extends Thread{
        boolean paused = false;
        @Override
        public void run(){
            while(true){
                Platform.runLater(ok);
                try {
                    TimeUnit.SECONDS.sleep(variables.sunTokenDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void Pause() {
            this.paused = true;
        }

        public void  Resume(){
            synchronized (t){
                paused = false;
                t.notifyAll();
            }
        }
    }
    class progress_2 implements Runnable{
        @Override
        public void run(){
            if(progressBar.getProgress()>=1){
                progressBar.setProgress(0);
            }
            else{
                progressBar.setProgress(progressBar.getProgress()+0.01);
            }
        }
    }
    class progress_1 extends Thread{
        boolean paused = false;
        @Override
        public void run(){
            while(true){
                if(paused){
                    try{
                        synchronized (t2){
                            t2.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(ok2);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void Pause() {
            this.paused = true;
        }
        public void  Resume(){
            synchronized (t2){
                paused = false;
                t2.notifyAll();
            }
        }
    }
}
