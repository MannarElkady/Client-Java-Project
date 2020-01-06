/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview.authentication;

import Model.RequestCreator;
import Model.RequestEntity;
import Utility.Validation;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.dao.implementation.UserDBOperations;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RegisterXMLController implements Initializable {

    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXButton signUpButton;
    @FXML
    private JFXPasswordField passwordPasswordField;
    @FXML
    private JFXButton loginButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signUpAction(ActionEvent event) {
        String email=emailTextField.getText(),password=passwordPasswordField.getText(),username=usernameTextField.getText();
        if(Validation.checkString(username) && Validation.checkString(password)
                    && Validation.checkString(email)){
            if(Validation.checkEmailRegex(email)&&Validation.checkUsernameRegex(username)){
                UserEntity newUser= new UserEntity();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setEmail(email);
                newUser.setOnlineFlag(0);
                UserDBOperations.register(newUser);
            }            
        }
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
    }
    
}
