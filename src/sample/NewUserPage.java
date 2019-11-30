package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static sample.GameClasses.Deserialize;

public class NewUserPage {
    @FXML
    Button LogInButton;
    @FXML
    Label ErrorLabel;
    @FXML
    TextField UsernameInputField;
    @FXML
    ImageView HomeImage;
    @FXML
    void onMouseEnterHomeButton() throws FileNotFoundException {
        HomeImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/homebuttonyellow.png")));
    }
    @FXML
    void onMouseExitHomeButton() throws FileNotFoundException {
        HomeImage.setImage(new Image(new FileInputStream("out/production/PVZ/sample/Graphics/homebuttongreen.png")));
    }
    @FXML
    void onButtonClick() throws IOException {
        Scene HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }

    @FXML
    public void onLogIn() throws IOException {
        GameClasses.allUsers a = null;
        try {
            a = Deserialize();
        }catch (Exception e){
            ErrorLabel.setText("Error: No Saved Users");
            return;
        }
        GameClasses.User curUser = null;
        for(GameClasses.User i: a.getUsersList()){
            if(i.getName().equals(UsernameInputField.getText())){
                    curUser = i;
                    break;
            }
        }
        if (curUser == null){
            ErrorLabel.setText("Error: Username Not Found");
            return;
        }
        variables.currentUser = curUser;
        Scene HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }


    @FXML
    public void onNewUser() throws IOException {
        String name = UsernameInputField.getText();
        GameClasses.User u = new GameClasses.User(name,1);
        GameClasses.allUsers a = null;
        System.out.println("Hi " + name);
        try{
            a = Deserialize();
        }catch (Exception e){
            variables.currentUser = u;
            ErrorLabel.setText("New User Created");
            Scene HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Main.GameStage.setScene(HomePage);
            //System.out.println(e.getMessage());
            return;
        }
        System.out.println("Deserialization successful");
        try{
            a.addUser(u);
        }catch (GameClasses.UserAlreadyExistsException e){
            ErrorLabel.setText("User with Username " + name + " already exists");
            return;
        }
        variables.currentUser = u;
        ErrorLabel.setText("New user Created");
        Scene HomePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Main.GameStage.setScene(HomePage);
    }
}
