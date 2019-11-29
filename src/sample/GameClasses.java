package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameClasses {
    public static void Serialize(allUsers a) throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("savedUsers.txt"));
            out.writeObject(a);
        }finally {
            out.close();
        }
    }

    public static allUsers Deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        allUsers a = null;
        try{
            in = new ObjectInputStream(new FileInputStream("savedUsers.txt"));
            a = (allUsers) in.readObject();
        }finally {
            in.close();
        }
        return a;
    }
    static class Game implements Serializable {
        int levelsUnlocked;

        public Game() {
            this.levelsUnlocked = 1;
        }

        public int getLevelsUnlocked() {
            return levelsUnlocked;
        }

        public void setLevelsUnlocked(int levelsUnlocked) {
            this.levelsUnlocked = levelsUnlocked;
        }
    }
    static class User implements Serializable {
        String name;
        ArrayList<Game> savedGames = new ArrayList<Game>();
        int level = 1;

        public User(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public ArrayList<Game> getSavedGames() {
            return savedGames;
        }

        public int getLevel() {
            return level;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSavedGames(ArrayList<Game> savedGames) {
            this.savedGames = savedGames;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    static class UserAlreadyExistsException extends Exception {
        public UserAlreadyExistsException(String name) {
            super("User with name " + name + " already exists please try a different username");
        }
    }


    static class allUsers implements Serializable {
        HashMap<User, Integer> users = new HashMap<User, Integer>();
        ArrayList<User> usersList = new ArrayList<User>();
        public void addUser(User u) throws UserAlreadyExistsException{
            if(users.containsKey(u)){
                throw new UserAlreadyExistsException(u.getName());
            }
            users.put(u,1);
            usersList.add(u);
        }

        public ArrayList<User> getUsersList() {
            return usersList;
        }
    }
}
