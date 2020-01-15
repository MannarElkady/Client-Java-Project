package Model;

import Model.dao.implementation.ItemDBOperations;
import clientview.TodoFormXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TitledPane;

/**
 *
 * @author DELL
 */
public class TodoSelectedItemHandler implements ChangeListener<Boolean> {
    TitledPane itemList;
    int itemId = -1;
    public TodoSelectedItemHandler(TitledPane itemList){
        this.itemList=itemList;
    }
    @Override
    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasExpanded, Boolean isNowExpanded) {
        if (isNowExpanded) {
            itemId=Integer.parseInt(itemList.getId());
            System.out.println("\n************" + itemList.getText()+"\n************" + Integer.parseInt(itemList.getId()));
            TodoFormXMLController.itemSelected.setItemID(itemId);
        }
    }
}
