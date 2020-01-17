/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.entities.ItemEntity;
import clientview.InsertItemXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class ItemAddingCollaboratorActionListener implements EventHandler<ActionEvent> {
    private Parent root;
    private Stage stage;
    
    public ItemAddingCollaboratorActionListener(Stage s){
        stage = s;
    }
    @Override
    public void handle(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientview/ItemAddCollaboratorXML.fxml"));
                    root = loader.load();
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(stage);
                    Scene dialogScene = new Scene(root);
                    dialog.setScene(dialogScene);
                    dialog.show();
                } catch (IOException ex) {
                    Logger.getLogger(ItemUpdatingActionListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
    }
    
}
