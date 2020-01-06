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

/**
 *
 * @author dell
 */
public class NotificationDBOperations {

    public static void receiveNotifications(int userID) {

//        RequestEntity<Integer> request = new RequestEntity("NotificationDBOperations", "receiveNotifications", userID);
//        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
    }
     public static void receiveNotificationsResponse(Object value) {
         
         ArrayList<NotificationEntity> notifications =  (ArrayList<NotificationEntity>)value;
         
         System.out.println("Array list :");
         for(int i=0;i<notifications.size();i++){
             System.out.print("value of "+i +" = "+notifications.get(i).getNotificationID() +" notification type = "+notifications.get(i).getNotificationType());
         }
     }
    

}
