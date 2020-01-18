/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.TodoListDBOperations;
import Model.entities.TodoCollaboratorEntity;
import Model.entities.TodoEntity;
import clientview.MainXMLController;
import clientview.TodoFormXMLController;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;

/**
 *
 * @author dell
 */
public class CollaboratorHandler implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        System.out.println("removeButton number : " + ((Control) event.getSource()).getId());
        int userID = Integer.parseInt(String.valueOf(((Control) event.getSource()).getId()));
        for (int i = 0; i < TodoFormXMLController.usersList.size(); i++) {
            if (TodoFormXMLController.usersList.get(i).getId() == userID) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation");
                alert.setContentText("Do you want to remove collaborator : " + TodoFormXMLController.usersList.get(i).getUsername());

                ButtonType buttonYes = new ButtonType("Yes");
                ButtonType buttonNo = new ButtonType("No");
                ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonYes, buttonNo, buttonCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonYes) {
                    TodoCollaboratorEntity todoCollaborator = new TodoCollaboratorEntity(TodoFormXMLController.todo.getId(), userID);
                    TodoListDBOperations.removeCollaborator(todoCollaborator);

                }

            }
        }

    }

}
