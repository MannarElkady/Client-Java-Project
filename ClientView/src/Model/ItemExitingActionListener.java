package Model;

import Model.dao.implementation.ItemDBOperations;
import Model.entities.UserAssignedToItem;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Ibrahim
 */
public class ItemExitingActionListener implements EventHandler<ActionEvent> {
    private UserAssignedToItem itemToExit;
    
    public ItemExitingActionListener(UserAssignedToItem item){
        itemToExit= item;
    }
    
    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setContentText("Do you want to exit this item?");
        ButtonType buttonYes = new ButtonType("Exit");
        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonYes, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes) {
            ItemDBOperations.exitFromAnITem(itemToExit);
        }
    }
}