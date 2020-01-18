/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.ItemDBOperations;
import clientview.TodoFormXMLController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;

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
