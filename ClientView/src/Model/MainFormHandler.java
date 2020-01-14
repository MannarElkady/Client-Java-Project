/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.TodoListDBOperations;
import Model.entities.TodoEntity;
import clientview.MainXMLController;
import clientview.TodoFormXMLController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

/**
 *
 * @author Ibrahim
 */
public class MainFormHandler implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        System.out.println(((Control) event.getSource()).getId());
        int todoID = Integer.parseInt(String.valueOf(((Control) event.getSource()).getId()));
        int toDoIndex = -1;
        for (int i = 0; i < MainXMLController.data.size(); i++) {
            if (((TodoEntity)MainXMLController.data.get(i)).getId() == todoID) {
                toDoIndex = i;
            }
        }
        TodoFormXMLController.setToDoData(((TodoEntity)MainXMLController.data.get(toDoIndex)));
        TodoListDBOperations.getAllItems(((TodoEntity)MainXMLController.data.get(toDoIndex)));
    }

}
