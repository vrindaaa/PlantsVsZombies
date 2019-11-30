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

import javafx.stage.Stage;
import sample.Zombies.*;
import sample.miscellaneous.*;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.variables.*;
import sample.plants.*;
import static javafx.fxml.FXMLLoader.load;
import static sample.variables.*;

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
    ImageView LawnMower_a, LawnMower_b, LawnMower_c, LawnMower_d, LawnMower_e;
    @FXML
    ImageView SunCard, PeaShooterCard, WallnutCard, lockWallnut;
    @FXML
    ProgressBar progressBar;
    Level currentLevel = null;
    ArrayList<ArrayList<Zombie>> lawn_zombies = new ArrayList<>();
    ArrayList<ArrayList<plant>> lawn_plants = new ArrayList<>();
    ArrayList<miscellaneous.LawnMower> lawn_mowers = new ArrayList<>();
    wallNutCard card_wallnut = null;
    ImageView GamePausedImageView = new ImageView(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/game_paused.jpg")));
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
    public  ArrayList<ImageView> getLawnMowerImageView(){
        ArrayList<ImageView> a = new ArrayList<>();
        a.add(LawnMower_a);
        a.add(LawnMower_b);
        a.add(LawnMower_c);
        a.add(LawnMower_d);
        a.add(LawnMower_e);
        return a;
    }
    public GamePageController() throws FileNotFoundException {
    }

    void start_game() throws FileNotFoundException {
        toStart = false;
        System.out.println("Game Started Again");
        for(int i=0; i<45; i++){
            getLawn().get(i).setImage(null);
        }
        //isGamePaused = false;
        currentLevel = Level.getLevel(1);
        card_wallnut = new wallNutCard(currentLevel.WallNutUnlocked, WallnutCard, true);
        setCards();
        lawn_zombies = curGame.listOflistOfZombies;
        lawn_plants = curGame.listOflistOfPlants;
        lawn_mowers = curGame.listOfLawnMowers;
        ArrayList<ImageView> lawnMowerImageView = getLawnMowerImageView();
        for(int i=0; i<5; i++){
            lawn_mowers.get(i).place = lawnMowerImageView.get(i);
            if(!lawn_mowers.get(i).used){
                lawnMowerImageView.get(i).setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/lawn_mower.gif")));
            }
            else{
                lawnMowerImageView.get(i).setImage(null);
            }
        }
        SunTokenLabel.setText(curGame.sunTokenString);
        add_plants();
        add_zombies();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(!isGamePaused)
                                generate_zombie();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        },10000,80000);
    }
    void add_zombies() throws FileNotFoundException {
        for(int i=0; i<5; i++){
            for(int j=0; j<lawn_zombies.get(i).size(); j++){
                Zombie temp = lawn_zombies.get(i).get(j);
                temp.setImage(new Image(new FileInputStream(lawn_zombies.get(i).get(j).image_path)));
                temp.setX(temp.myX);
                temp.setY(temp.myY);
                try{
                GamepagePane.getChildren().add(temp);}
                catch(Exception e){
                    //System.out.println("oops");
                }
            }
        }
    }
    void add_plants() throws FileNotFoundException {
        for(int i=0; i<5; i++){
            for(int j=0; j<lawn_plants.get(i).size(); j++){
                plant temp = lawn_plants.get(i).get(j);
                temp.setX(temp.myX);
                temp.setY(temp.myY);
                getLawn().get(temp.row*9 +temp.col).setImage(new Image(new FileInputStream(temp.image_path)));
                temp.currentImageView = getLawn().get(temp.row*9 +temp.col);
                temp.work(GamepagePane, SunTokenLabel, lawn_zombies);
            }
        }
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
                if(!isGamePaused){
                    Platform.runLater(() -> {
                        z.move(GamepagePane);
                        if(z.getHealth()<=0){
                            GamepagePane.getChildren().remove(z);
                        }
                    });
                }
            }
        },0,40);
    }
    //Main Game Start
    @FXML
    private void start_sun() throws FileNotFoundException {
        if(toStart){
            start_game();
        }
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
        if(!SunFlower.isAvailable){
            System.out.println("not available");
            return;
        }
        if(!isGamePaused){
        Dragboard db = SunCard.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString("sunflower_gif.gif");
        cb.putImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/sunflowercard_compressed.jpg")));
        db.setContent(cb);
        event.consume();
    }
    }
    @FXML
    private void HandleDragPeaShooterCard(MouseEvent event) throws FileNotFoundException {
        if(!PeaShooter.isAvailable){
            System.out.println("pea shooter not available");
            return;
        }
        if(!isGamePaused){
        Dragboard db = PeaShooterCard.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString("peashooter_gif.gif");
        cb.putImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/peashootercard_compressed.jpg")));
        db.setContent(cb);
        event.consume();
        }
    }
    @FXML
    private void HandleDragWallnutCard(MouseEvent event) throws FileNotFoundException {
        if(card_wallnut.isUnlocked && !isGamePaused){
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
        curGame.sunTokenString = SunTokenLabel.getText();
        isGamePaused = true;
        PauseMenuStage = new Stage();
        PauseMenuStage.setScene(load(getClass().getResource("PauseMenu.fxml")));
        PauseMenuStage.show();
        //GamepagePane.getChildren().add(GamePausedImageView);
        //Main.GameStage.setScene(load(getClass().getResource("PauseMenu.fxml")));
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
                        PeaShooter cur_plant = new PeaShooter(lawn.get(i), row, GamepagePane,lawn_zombies, i%9);
                        lawn_plants.get(row).add(cur_plant);
                        lawn.get(i).setImage(myPlant);
                        cur_plant.isAvailable = false;
                        Timer timer = new Timer();
                        long temp = PeaShooter.loadTime;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                PeaShooter.isAvailable = true;
                            }
                        }, temp * 1000);
                    }
                    if (db.getString().equals("sunflower_gif.gif")){
                        if(Integer.parseInt(SunTokenLabel.getText())>=25){
                            SunTokenLabel.setText(String.valueOf(Integer.parseInt(SunTokenLabel.getText())-25));
                        }else{
                            return;
                        }
                        SunFlower cur_plant = new SunFlower(lawn.get(i), row, GamepagePane, SunTokenLabel, i%9);
                        lawn_plants.get(row).add(cur_plant);
                        lawn.get(i).setImage(myPlant);
                        SunFlower.isAvailable = false;
                        //System.out.println("here");
                        Timer timer = new Timer();
                        long temp = cur_plant.loadTime;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                SunFlower.isAvailable = true;
                                //System.out.println("here2");
                            }
                        }, temp * 1000);
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
                if(!isGamePaused)
                    Platform.runLater(ok);
                try {
                    TimeUnit.SECONDS.sleep(variables.sunTokenDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                if(!isGamePaused)
                    Platform.runLater(ok2);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
