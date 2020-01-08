package Model.entities;

import java.util.ArrayList;

/**
 * @author Ibrahim
 */
enum notificationTypeData {
    itemInvitation, todoInvitation, notifyAll
}

public class NotificationEntity {

    private int notificationID;
    private String header;
    private String text;
    private int senderID;
    private String notificationType;
    private ArrayList<NotificationReceiversEntity> notificationReceivers;

    public NotificationEntity() {
    }

    public NotificationEntity(String header, String text, int senderID, String notificationType, ArrayList<NotificationReceiversEntity> notificationReceivers) {
        this.header = header;
        this.text = text;
        this.senderID = senderID;
        this.notificationType = notificationType;
        this.notificationReceivers = notificationReceivers;
    }

    public ArrayList<NotificationReceiversEntity> getNotificationReceivers() {
        return notificationReceivers;
    }

    public void setNotificationReceivers(ArrayList<NotificationReceiversEntity> notificationReceivers) {
        this.notificationReceivers = notificationReceivers;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

}
