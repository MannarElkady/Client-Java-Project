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
    
    
}
