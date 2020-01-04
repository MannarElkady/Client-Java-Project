/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview.authentication;

import Model.RequestCreator;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signUpAction(ActionEvent event) {
        if(Validation.checkString(usernameTextField.getText()) && Validation.checkString(passwordPasswordField.getText())
                    && Validation.checkString(emailTextField.getText())){
            if(Validation.checkEmailRegex(emailTextField.getText())&&Validation.checkUsernameRegex(usernameTextField.getText())){
                UserEntity newUser= new UserEntity();
                newUser.setUsername(usernameTextField.getText());
                newUser.setPassword(passwordPasswordField.getText());
                newUser.setEmail(emailTextField.getText());
                RequestCreator newRequest = new RequestCreator("UserDBOperations","register",newUser);
                String newRequestJson= newRequest.getJsonObject();
                System.out.println(newRequestJson);
            }            
        }
    }
    
}
