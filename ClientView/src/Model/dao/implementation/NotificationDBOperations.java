/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.Handler;
import Model.RequestEntity;
import Model.entities.NotificationEntity;
import Model.entities.UserEntity;
import clientview.ClientView;
import clientview.MainXMLController;
import java.util.ArrayList;
import clientview.NotificationGUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

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
            Handler.sendRequestToServer(request);
        }
    }

    public static void receiveNotificationsResponse(ArrayList<Object> value) {

         ArrayList<NotificationEntity> notificationsList = null;
        if (value != null) {
             notificationsList = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                notificationsList.add((NotificationEntity) value.get(i));
            }                                  
        }
         NotificationGUI.loadNotificationMenu(notificationsList);
    }

    public static void sendNotification(ArrayList<Object> value) {

        if (value != null) {
            NotificationEntity notification = (NotificationEntity) value.get(0);

            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            notificationsList.add(notification);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "sendNotification", notificationsList);
            Handler.sendRequestToServer(request);
        }

    }

    public static void sendNotificationForItemAcceptance(ArrayList<Object> value) {

        if (value != null) {
            NotificationEntity notification = (NotificationEntity) value.get(0);

            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            notificationsList.add(notification);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "ItemAcceptNotification", notificationsList);
            Handler.sendRequestToServer(request);
        }

    }

    public static void sendNotificationForTodoAcceptance(ArrayList<Object> value) {

        if (value != null) {
            NotificationEntity notification = (NotificationEntity) value.get(0);

            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            notificationsList.add(notification);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "todoAcceptNotification", notificationsList);
            Handler.sendRequestToServer(request);
        }

    }

    public static void sendNotificationForFriendInfivationAcceptance(ArrayList<Object> value) {

        if (value != null) {
            NotificationEntity notification = (NotificationEntity) value.get(0);

            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            notificationsList.add(notification);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "friendAcceptNotification", notificationsList);
            Handler.sendRequestToServer(request);
        }

    }

    public static void addNotificationResponse(ArrayList<Object> value) throws FileNotFoundException {
        if (value != null) {
            NotificationGUI.receiveNotificationTray();
        }
        else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Response");
                    alert.setHeaderText(null);
                    alert.setContentText("please wait for friend response");
                    alert.showAndWait();
                }
            });
        }
    }
    
    public static void sendRejectionNotification(ArrayList<Object> value){
    
         if (value != null) {
            NotificationEntity notification = (NotificationEntity) value.get(0);

            ArrayList<NotificationEntity> notificationsList = new ArrayList<>();
            notificationsList.add(notification);
            RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "rejectInvitationNotification", notificationsList);
            Handler.sendRequestToServer(request);
        }
    }
}
