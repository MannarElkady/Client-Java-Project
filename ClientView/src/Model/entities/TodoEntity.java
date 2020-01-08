package Model.entities;

import java.sql.Date;

/**
 *
 * @author Ibrahim
 */
public class TodoEntity {

    private int id;
    private String title;
    private String color;
    private int creatorId;
    private String status;
    private String description;
    private Date assignDate;
    private Date deadlineDate;

    public TodoEntity() {
    }

    public TodoEntity(String title, String description, String color, int creatorId, String status, Date assignDate, Date deadlineDate) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.creatorId = creatorId;
        this.status = status;
        this.assignDate = assignDate;
        this.deadlineDate = deadlineDate;
    }

    public TodoEntity(int id, String title, String color, int creatorId, String status, String description, Date assignDate, Date deadlineDate) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.creatorId = creatorId;
        this.status = status;
        this.description = description;
        this.assignDate = assignDate;
        this.deadlineDate = deadlineDate;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
