package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    public void onLogIn(){
        GameClasses.allUsers a = null;
        try {
            a = Deserialize();
        }catch (IOException e){
            ErrorLabel.setText("Error: No saved Users");
            return;
        }catch (Exception e){
            ErrorLabel.setText(e.getMessage());
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
    }


    @FXML
    public void onNewUser(){
        String name = UsernameInputField.getText();
        GameClasses.User u = new GameClasses.User(name,1);
        GameClasses.allUsers a = null;
        try{
            a = Deserialize();
        }catch (IOException e){
//            curUser = u;
            return;
        }catch (Exception e){
            ErrorLabel.setText(e.getMessage());
            return;
        }
        try{
            a.addUser(u);
        }catch (GameClasses.UserAlreadyExistsException e){
            ErrorLabel.setText("User with Username " + name + " already exists");
            return;
        }
    }
}
