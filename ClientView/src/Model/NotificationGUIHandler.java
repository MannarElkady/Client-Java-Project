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

        String buttonType = "";
        String buttonID = String.valueOf(((Control) event.getSource()).getId());
        String notificationType = "";
        if (buttonID.contains("accept")) {
            notificationType = buttonID.split("accept")[1];
            buttonType = "accept";
        } else if (buttonID.contains("reject")) {
            notificationType = buttonID.split("reject")[1];
            buttonType = "reject";
        }
        for (int i = 0; i < NotificationGUI.notificationsListForOtherClasses.size(); i++) {
            if (notificationType.equals(NotificationGUI.notificationsListForOtherClasses.get(i).getNotificationType())) {

                ArrayList<Object> notificationList = new ArrayList<>();
                NotificationEntity notification = NotificationGUI.notificationsListForOtherClasses.get(i);
                ArrayList<NotificationReceiversEntity> receiversList = new ArrayList<>();
                NotificationReceiversEntity receiver = new NotificationReceiversEntity();
                receiver.setReceiverID(ClientView.currentUser.getId());
                receiversList.add(receiver);
                notification.setNotificationReceivers(receiversList);
                notificationList.add(notification);
                if (buttonType.equals("accept")) {
                    if (notificationType.contains("itemInvitation")) {
                        NotificationDBOperations.sendNotificationForItemAcceptance(notificationList);
                    } else if (notificationType.contains("todoInvitation")) {
                        NotificationDBOperations.sendNotificationForTodoAcceptance(notificationList);
                    }                    
                    else if (notificationType.contains("friendInvitation")){
                        NotificationDBOperations.sendNotificationForFriendInfivationAcceptance(notificationList);
                    }
                }
            }
        }

    }

}
