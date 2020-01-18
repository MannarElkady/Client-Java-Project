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
public class TodoCollaboratorEntity {
    private int todoID;
    private int userID;

    public TodoCollaboratorEntity(int todoID, int userID) {
        this.todoID = todoID;
        this.userID = userID;
    }

    public int getTodoID() {
        return todoID;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
