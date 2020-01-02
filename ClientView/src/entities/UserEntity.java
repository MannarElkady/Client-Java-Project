package todolistserver.model.entities;

public class UserEntity {
    
    private int id;
    private String username;
    private String password;
    private String email;
    private int onlineFlag;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getOnlineFlag() {
        return onlineFlag;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOnlineFlag(int onlineFlag) {
        this.onlineFlag = onlineFlag;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
	
}
