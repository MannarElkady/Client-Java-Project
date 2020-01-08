/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.entities;

/**
 *
 * @author dell
 */
public class NotificationReceiversEntity {
    private int notificationID;
    private int receiverID;
    private int readFlag;
    private int acceptanceFlag;

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }

    public int getAcceptanceFlag() {
        return acceptanceFlag;
    }

    public void setAcceptanceFlag(int acceptanceFlag) {
        this.acceptanceFlag = acceptanceFlag;
    }
    
    
}
