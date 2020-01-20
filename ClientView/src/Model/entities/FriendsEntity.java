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
public class FriendsEntity {
    private int userID;
    private int friendID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public FriendsEntity() {
    }

    public FriendsEntity(int userID, int friendID) {
        this.userID = userID;
        this.friendID = friendID;
    }
    
}
