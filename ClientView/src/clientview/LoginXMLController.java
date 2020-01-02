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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void loginAction(){
        if(!userNameTextField.getText().isEmpty() && !passwordPasswordField.getText().isEmpty()){
            if(Validation.checkEmailRegex(userNameTextField.getText())){
                UserEntity loginUser= new UserEntity();
                loginUser.setUsername(userNameTextField.getText());
                loginUser.setPassword(passwordPasswordField.getText());
                RequestCreator newRequest = new RequestCreator("UserDBOperations","login",loginUser);
                String newRequestJson= newRequest.getJsonObject();
                System.out.println(newRequestJson);
            }            
        }
    }
}
