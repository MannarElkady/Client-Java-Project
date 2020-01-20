/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.UserDBOperations;
import Model.entities.NotificationEntity;
import Model.entities.NotificationReceiversEntity;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class removeFriendXMLController implements Initializable {
    
    private ArrayList<UserEntity> todoCollaborators=MainXMLController.test2;    
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private JFXComboBox<String> itemCollaboratorsComboBox;
    @FXML
    private JFXButton addCollaboratorButton;
    @FXML
    private JFXButton removeFriendButton;
    /**
     * Initializes the controller class.
     */
    private void showSuggestedCollaborators(){
        ArrayList<String> usernames=new ArrayList();
        if(todoCollaborators !=null){
            for(int i=0; i<todoCollaborators.size();i++){
                UserEntity collaboratorya= todoCollaborators.get(i);
                //!isAssignedUser(collaboratorya)
            //    System.out.println("\n**********Yalahwiiiiii\n"+"for collaborator "+collaboratorya.getUsername()+isAssignedUser(collaboratorya));
                if(!(collaboratorya.getUsername().equals(ClientView.currentUser.getUsername()))){
                    usernames.add(collaboratorya.getUsername());
                }
            }
            itemCollaboratorsComboBox.setItems(FXCollections.observableArrayList(usernames));
        }
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        System.out.println("/n after function");
        showSuggestedCollaborators();
        
        
    }    

    

    
    @FXML
    private void  removeFriendButtonAction(){
        if(!itemCollaboratorsComboBox.getValue().equals("")){
           if(todoCollaborators !=null){
            for(int i=0; i<todoCollaborators.size();i++){
                UserEntity collaborator= todoCollaborators.get(i);                
                if(collaborator.getUsername().equals(itemCollaboratorsComboBox.getValue())){
                    UserDBOperations.removeFriend(ClientView.currentUser.getId(),collaborator.getId());
                }
            }
            ((Stage) removeFriendButton.getScene().getWindow()).close();
           //removeFriendButton
        }
        }
    }
}
