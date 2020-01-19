/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.ItemDBOperations;
import Model.entities.ItemEntity;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author DELL
 */
public class ItemDeletingActionListener implements EventHandler<ActionEvent> {
    private ItemEntity itemToDelete;
    public ItemDeletingActionListener(ItemEntity item){
        itemToDelete= item;
    }
    @Override
    public void handle(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setContentText("Do you want to Delete  " + itemToDelete.getTitle().toUpperCase());
        ButtonType buttonYes = new ButtonType("Delete");
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonYes, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes) {
            ItemDBOperations.deleteItem(itemToDelete);
        }
    }
}
