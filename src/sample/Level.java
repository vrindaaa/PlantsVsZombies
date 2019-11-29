package sample;

import java.util.HashMap;
class InvalidLevelException extends RuntimeException{
    InvalidLevelException(){
        super("Invalid Level");
    }
}
public class Level {
    static HashMap<Integer, Level> levels = new HashMap<>();
    public boolean WallNutUnlocked = false;
    public boolean repeaterUnlocked = false;
    public boolean cherryBombUnlocked = false;
    private Level(boolean WallNutUnlocked, boolean repeaterUnlocked, boolean cherryBombUnlocked){
        this.WallNutUnlocked = WallNutUnlocked;
        this.repeaterUnlocked = repeaterUnlocked;
        this.cherryBombUnlocked = cherryBombUnlocked;
    }
    static public Level getLevel(int num){
        if(levels.containsKey(num)){
            return levels.get(num);
        }
        else{
            Level l = null;
            switch (num){
                case 1:
                    l = new Level(false, false, false);
                    levels.put(num, l); break;
                case 2:
                    l = new Level(true, false, false);
                    levels.put(num, l); break;
                case 3:
                    l = new Level(true, true, true);
                    levels.put(num,l); break;
                default:
                    throw new InvalidLevelException();
            }
            return l;
        }
    }
}
