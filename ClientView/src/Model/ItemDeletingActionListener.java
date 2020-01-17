/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.ItemDBOperations;
import Model.entities.ItemEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
        ItemDBOperations.deleteItem(itemToDelete);
    }
}
