/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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
            
        }
    }
}
