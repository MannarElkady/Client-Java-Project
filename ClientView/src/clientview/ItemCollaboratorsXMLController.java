/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.entities.UserEntity;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ItemCollaboratorsXMLController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Label collaboratorLabel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXListView<?> collaboratorListView;
    private Label username;
    private static ArrayList<UserEntity> collaborators= new ArrayList();
    /**
     * Initializes the controller class.
     */
    
    public static void setCollaboratorsList(ArrayList<UserEntity> arrayList){
        collaborators=arrayList;
    }
    
    public void displayCollaboratorsList(){
        for(int i=0;i<collaborators.size();i++){
            username= new Label(collaborators.get(i).getUsername());
            username.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;");
            username.setWrapText(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    
}
