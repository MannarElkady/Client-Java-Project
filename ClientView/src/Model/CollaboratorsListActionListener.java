/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.ItemDBOperations;
import Model.dao.implementation.UserDBOperations;
import clientview.ClientView;
import clientview.TodoFormXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        ItemDBOperations.getItemCollaborators(TodoFormXMLController.itemSelected);
    }
}
