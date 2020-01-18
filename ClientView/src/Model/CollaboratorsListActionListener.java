/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.UserDBOperations;
import Model.entities.UserEntity;
import clientview.ItemCollaboratorsXMLController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class CollaboratorsListActionListener implements EventHandler<ActionEvent> {
    Parent root;

    @Override
    public void handle(ActionEvent event) {
       // ItemDBOperations.getItemCollaborators(TodoFormXMLController.itemSelected);
       ArrayList<UserEntity> collabotarors =ItemCollaboratorsXMLController.getItemAssignedCollaborators();
        if (collabotarors == null || collabotarors.size() == 0) {
            System.out.println("no user in this item");
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Nothing To show");
                    alert.setHeaderText("Empty List !");
                    alert.setContentText("No Collaborators Assigned to this Item");
                    alert.show();
                }
            });
        }
            else{
       Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/clientview/ItemCollaboratorsXML.fxml"));
                        Stage stage = new Stage(StageStyle.DECORATED);
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
                    }
    }
}
