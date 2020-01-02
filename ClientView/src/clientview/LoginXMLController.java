/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import Model.entities.UserEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.RequestCreator;
import Model.Validation;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LoginXMLController implements Initializable {

    @FXML
    JFXTextField userNameTextField; 
    @FXML
    JFXPasswordField passwordPasswordField;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton signUpButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void loginAction(){
        if(Validation.checkString(userNameTextField.getText()) && Validation.checkString(passwordPasswordField.getText())){
            if(Validation.checkUsernameRegex(userNameTextField.getText())){
                UserEntity loginUser= new UserEntity();
                loginUser.setUsername(userNameTextField.getText());
                loginUser.setPassword(passwordPasswordField.getText());
                loginUser.setOnlineFlag(1);
                RequestCreator newRequest = new RequestCreator("UserDBOperations","login",loginUser);
                String newRequestJson= newRequest.getJsonObject();
                System.out.println(newRequestJson);
            }            
        }
    }

    @FXML
    private void signUpAction(ActionEvent event) {
        
    }
}
