package sample;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class GameClasses {
    public static void Serialize(Game a) throws IOException, UserAlreadyExistsException {
        variables.currentUser.addGame(a);
        allUsers al = null;
        try {
            al = Deserialize();
        }catch (Exception e){
            al = new allUsers();
        }
        al.updateUser(variables.currentUser);

        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("savedUsers.txt"));
            out.writeObject(al);
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
        public int cur_level;
        String sunTokenString;
        public double progress;
        ArrayList<ArrayList<Zombies.Zombie>> listOflistOfZombies;
        ArrayList<ArrayList<plants.plant>> listOflistOfPlants;
        ArrayList<miscellaneous.LawnMower> listOfLawnMowers;
        String id;
        public double noOfzombiesGenerated=0;
        public double noOfzombiesKilled=0;

        public Game(int cur_level, ArrayList<ArrayList<Zombies.Zombie>> listOflistOfZombies, ArrayList<ArrayList<plants.plant>> listOflistOfPlants, String SunTokenString, ArrayList<miscellaneous.LawnMower> listOfLawnMowers) {
            this.listOflistOfZombies = listOflistOfZombies;
            this.cur_level = cur_level;
            this.listOflistOfPlants = listOflistOfPlants;
            this.sunTokenString = SunTokenString;
            this.listOfLawnMowers = listOfLawnMowers;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            id = dtf.format(now);
        }

        public ArrayList<ArrayList<Zombies.Zombie>> getListOflistOfZombies() {
            return listOflistOfZombies;
        }

        public ArrayList<ArrayList<plants.plant>> getListOflistOfPlants() {
            return listOflistOfPlants;
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
        public void addGame(Game g){
            savedGames.add(0,g);
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
            if(this.level < level) {
                this.level = level;
            }
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
        public void updateUser(User u) throws UserAlreadyExistsException {
            for(int i = 0;i< usersList.size();i++){
                if(usersList.get(i).getName().equals(u.getName())){
                    usersList.set(i,u);
                    return;
                }
            }
            addUser(u);
        }
        public ArrayList<User> getUsersList() {
            return usersList;
        }
    }
}
