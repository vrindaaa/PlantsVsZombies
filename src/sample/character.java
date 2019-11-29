package sample;


import javafx.scene.image.ImageView;

import java.io.Serializable;


public class character extends ImageView implements Serializable {
    public double myX;
    public double myY;
    public void setMyX(double x){
        super.setX(x);
        myX = x;
    }
    public void setMyY(double y){
        super.setY(y);
        myY = y;
    }
}
