/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.NotificationDBOperations;
import Model.entities.NotificationEntity;
import Model.entities.NotificationReceiversEntity;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private void addCollaboratorButtonAction() { 
        if(!itemCollaboratorsComboBox.getValue().equals(""))        
            prepareNotification();
        
    }
    
       private void prepareNotification() {

        ArrayList<Object> data = new ArrayList<>();
        NotificationEntity notification = new NotificationEntity();
        notification.setHeader("Item Assign invitation");
        notification.setText(ClientView.currentUser.getUsername() + " invited you to be collaborator on item " + TodoFormXMLController.itemSelected.getTitle()+" on todo "+TodoFormXMLController.todo.getTitle());
        notification.setNotificationType("itemInvitation" + TodoFormXMLController.itemSelected.getItemID());
        notification.setSenderID(ClientView.currentUser.getId());
        boolean checkData=false;
        NotificationReceiversEntity notificationReceiver = new NotificationReceiversEntity();
            for (int i = 0; i < todoCollaborators.size(); i++) {
                if (todoCollaborators.get(i).getUsername().equals(itemCollaboratorsComboBox.getValue()) && !itemCollaboratorsComboBox.getValue().equals(ClientView.currentUser.getUsername())) {
                    notificationReceiver.setReceiverID(todoCollaborators.get(i).getId());
                    checkData=true;
            }
        }
        if(checkData){
            ArrayList<NotificationReceiversEntity> receiversList = new ArrayList<>();
            receiversList.add(notificationReceiver);
            notification.setNotificationReceivers(receiversList);
            data.add(notification);
            NotificationDBOperations.sendNotification(data);
                                   
        }
    }
    
}
