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
    
    private static ArrayList<UserEntity> itemCollaborators=TodoFormXMLController.getCollaboratorList();

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private JFXComboBox<String> itemCollaboratorsComboBox;
    @FXML
    private JFXButton addCollaboratorButton;
    /**
     * Initializes the controller class.
     */
    private void showAllCollaborators(){
        ArrayList<String> usernames=new ArrayList();
        if(itemCollaborators !=null){
            for(int i=0; i<itemCollaborators.size();i++){   
                usernames.add(itemCollaborators.get(i).getUsername());
            }
            itemCollaboratorsComboBox.setItems(FXCollections.observableArrayList(usernames));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAllCollaborators();
        
    }    

    @FXML
    private void addCollaboratorButtonAction(ActionEvent event) {
        
    }
    
}
