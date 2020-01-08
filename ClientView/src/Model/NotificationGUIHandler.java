/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.dao.implementation.NotificationDBOperations;
import Model.entities.NotificationEntity;
import Model.entities.NotificationReceiversEntity;
import clientview.NotificationGUI;
import clientview.authentication.LoginXMLController;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import clientview.ClientView;

/**
 *
 * @author dell
 */
public class NotificationGUIHandler implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        System.out.println(((Control) event.getSource()).getId());

        String buttonType="";
        String buttonID = String.valueOf(((Control) event.getSource()).getId());
        String itemNotificationType="";
        if (buttonID.contains("accept")) {
           itemNotificationType = buttonID.split("accept")[1];
           buttonType="accept";           
        } else if(buttonID.contains("reject")) {
            itemNotificationType = buttonID.split("reject")[1];
            buttonType="reject";
        }
        for (int i = 0; i < NotificationGUI.notificationsListForOtherClasses.size(); i++) {
            if(itemNotificationType .equals(NotificationGUI.notificationsListForOtherClasses.get(i).getNotificationType())){
                ArrayList<Object> notificationList = new ArrayList<>();
                NotificationEntity notification = NotificationGUI.notificationsListForOtherClasses.get(i);
                ArrayList<NotificationReceiversEntity> receiversList = new ArrayList<>();
                NotificationReceiversEntity receiver = new NotificationReceiversEntity();
                receiver.setReceiverID(ClientView.currentUser.getId());
                receiversList.add(receiver);
                notification.setNotificationReceivers(receiversList);
                notificationList.add(notification);
                NotificationDBOperations.sendNotificationForItemAcceptance(notificationList);
            }
        }
       
         
    }

}
