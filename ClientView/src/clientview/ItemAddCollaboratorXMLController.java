/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ItemAddCollaboratorXMLController implements Initializable {
    
    private ArrayList<UserEntity> todoCollaborators=TodoFormXMLController.getCollaboratorList();
    private ArrayList<UserEntity> currentCollaborators = ItemCollaboratorsXMLController.getItemAssignedCollaborators();
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private JFXComboBox<String> itemCollaboratorsComboBox;
    @FXML
    private JFXButton addCollaboratorButton;
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
                if(!(collaboratorya.getUsername().equals(ClientView.currentUser.getUsername()))&&!isAssignedUser(collaboratorya)){
                    usernames.add(collaboratorya.getUsername());
                }
            }
            itemCollaboratorsComboBox.setItems(FXCollections.observableArrayList(usernames));
        }
    }
    private Boolean isAssignedUser(UserEntity user){
        if(currentCollaborators!=null){
        for(int i=0; i<currentCollaborators.size();i++){
                if((currentCollaborators.get(i).getId()==user.getId())){
                    System.out.println("\n********Assigned\n"+"for collaborator "+currentCollaborators.get(i)+" is equal"+user.getUsername());
                    return true;
                }
          //  System.out.println("\n********Testttttt "+currentCollaborators.get(i).getUsername());   
            }
        }
        return false;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("/n after function");
        showSuggestedCollaborators();
        
    }    

    @FXML
    private void addCollaboratorButtonAction(ActionEvent event) {
        
    }
    
}
