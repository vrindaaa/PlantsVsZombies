package sample;

import java.util.HashMap;

public class Level {
    static HashMap<Integer, Level> levels = new HashMap<>();
    public boolean WallNutUnlocked = false;
    private Level(boolean WallNutUnlocked){
        this.WallNutUnlocked = WallNutUnlocked;
    }
    static public Level getLevel(int num){
        if(levels.containsKey(num)){
            return levels.get(num);
        }
        else{
            Level l = new Level(false);
            levels.put(num, l);
            return l;
        }
    }
}
