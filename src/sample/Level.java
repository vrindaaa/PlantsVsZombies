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
    public int max_zombies = 10;
    public int levelNo;
    private Level(boolean WallNutUnlocked, boolean repeaterUnlocked, boolean cherryBombUnlocked,int max_zombies,int levelNo){
        this.levelNo = levelNo;
        this.max_zombies = max_zombies;
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
                    l = new Level(false, false, false,10,1);
                    levels.put(num, l); break;
                case 2:
                    l = new Level(true, false, false,15,2);
                    levels.put(num, l); break;
                case 3:
                    l = new Level(true, true, false,20,3);
                    levels.put(num,l); break;
                case 4:
                    l = new Level(true, true, true,25,4);
                    levels.put(num,l); break;
                case 5:
                    l = new Level(true, true, true,30,5);
                    levels.put(num,l); break;
                case 6:
                    l = new Level(true, true, true,200,6);
                    levels.put(num,l); break;
                default:
                    throw new InvalidLevelException();
            }
            return l;
        }
    }
}
