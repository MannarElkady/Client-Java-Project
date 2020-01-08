/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.RequestEntity;
import Model.SocketConnection;
import Model.entities.NotificationEntity;
import Model.entities.UserEntity;
import java.util.ArrayList;
import clientview.NotificationGUI;
import java.io.FileNotFoundException;

/**
 *
 * @author dell
 */
public class NotificationDBOperations {

    public static void receiveNotifications(ArrayList<Integer> userID) {
        if (userID != null) {
            UserEntity user = new UserEntity();
            user.setId(userID.get(0));
            ArrayList<UserEntity> list = new ArrayList<>();
            list.add(user);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "receiveNotifications", list);
            SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
        }
    }

    public static void receiveNotificationsResponse(ArrayList<Object> value) {

        if (value != null) {
            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                notificationsList.add((NotificationEntity) value.get(i));
            }
            NotificationGUI.loadNotificationMenu(notificationsList);
        }
    }

    public static void sendNotification(ArrayList<Object> value) {

        if (value != null) {
            NotificationEntity notification = (NotificationEntity) value.get(0);

            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            notificationsList.add(notification);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "sendNotification", notificationsList);
            SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
        }

    }
    
      public static void sendNotificationForItemAcceptance(ArrayList<Object> value) {

        if (value != null) {
            NotificationEntity notification = (NotificationEntity) value.get(0);

            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            notificationsList.add(notification);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "ItemAcceptNotification", notificationsList);
            SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
        }

    }

    public static void addNotificationResponse(ArrayList<Object> value) throws FileNotFoundException {
        if (value != null) {
            NotificationGUI.receiveNotificationTray();
        }
    }
}
