/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.ItemDBOperations;
import Model.entities.ItemEntity;
import clientview.InsertItemXMLController;
import clientview.TodoFormXMLController;
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
public class ItemUpdatingActionListener implements EventHandler<ActionEvent> {
    Parent root;
    Stage stage;
    ItemEntity item;
    public ItemUpdatingActionListener(Stage s, ItemEntity i){
        stage=s;
        item=i;
    }
    @Override
    public void handle(ActionEvent event) {
            Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    InsertItemXMLController.isUpdate=true;
                    InsertItemXMLController.setItemToUpdate(item);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientview/InsertItemXML.fxml"));
                    Parent insertItemWindow = loader.load();
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(stage);
                    Scene dialogScene = new Scene(insertItemWindow);
                    dialog.setScene(dialogScene);
                    dialog.show();
                } catch (IOException ex) {
                    Logger.getLogger(ItemUpdatingActionListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        }
    }
