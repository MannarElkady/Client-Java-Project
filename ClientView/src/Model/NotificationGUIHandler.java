/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

/**
 *
 * @author dell
 */
public class NotificationGUIHandler  implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        System.out.println(((Control) event.getSource()).getId());
    }

}

