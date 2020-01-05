package Model.entities;

/**
 * @author Ibrahim
 */
public class AssignFriendTodoEntity {
    private String userName;
    private int currentUserId;
    private int todoId;

    public AssignFriendTodoEntity() {
    }

    public AssignFriendTodoEntity(String userName, int currentUserId, int todoId) {
        this.userName = userName;
        this.currentUserId = currentUserId;
        this.todoId = todoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }
    
    
    
}
