package Model.entities;

import java.sql.Date;

/**
 * @author Ibrahim
 */
public class ItemEntity {

    private int itemID;
    private String title;
    private String description;
    private int todoID;
    private int creatorID;
    private Date deadlineDate;

    public ItemEntity() {
    }
    public ItemEntity(int itemID, String title, String description, int todoID, int creatorID, Date deadlineDate) {
        this.itemID = itemID;
        this.title = title;
        this.description = description;
        this.todoID = todoID;
        this.creatorID = creatorID;
        this.deadlineDate = deadlineDate;
    }

    public ItemEntity(String title, String description, int todoID, int creatorID, Date deadlineDate) {
        this.title = title;
        this.description = description;
        this.todoID = todoID;
        this.creatorID = creatorID;
        this.deadlineDate = deadlineDate;
    }
    
    
    
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTodoID() {
        return todoID;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

}
