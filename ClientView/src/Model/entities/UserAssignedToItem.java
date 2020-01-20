package Model.entities;

/**
 *
 * @author Ibrahim
 */
public class UserAssignedToItem {
    private int id;
    private String username;
    private int itemId;

    public UserAssignedToItem() {
    }
    
    public UserAssignedToItem(int id, String username, int itemId) {
        this.id = id;
        this.username = username;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
}
