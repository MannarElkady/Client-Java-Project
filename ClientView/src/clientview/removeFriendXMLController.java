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
public class removeFriendXMLController implements Initializable {
    
    private ArrayList<UserEntity> todoCollaborators=MainXMLController.test2;    
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
    private void addCollaboratorButtonAction() {
         String value = itemCollaboratorsComboBox.getValue();
        System.out.println("value = "+value);
        
    }
    
       private void prepareNotification(String todoName, String todoID) {

        ArrayList<Object> data = new ArrayList<>();
        NotificationEntity notification = new NotificationEntity();
        notification.setHeader("Item Assign invitation");
        notification.setText(ClientView.currentUser.getUsername() + " invited you to be collaborator on item " + TodoFormXMLController.itemSelected.getTitle());
        notification.setNotificationType("itemInvitation" + TodoFormXMLController.itemSelected.getItemID());
        notification.setSenderID(ClientView.currentUser.getId());
       
                /*    NotificationReceiversEntity notificationReceiver = new NotificationReceiversEntity();
                for (int i = 0; i < test2.size(); i++) {
                if (test2.get(i).getUsername().equals(dragSource.get().getItem())) {
                notificationReceiver.setReceiverID(test2.get(i).getId());
                }
                }
                ArrayList<NotificationReceiversEntity> receiversList = new ArrayList<>();
                receiversList.add(notificationReceiver);
                notification.setNotificationReceivers(receiversList);
                data.add(notification);
                NotificationDBOperations.sendNotification(data);*/;
    }
    
}
