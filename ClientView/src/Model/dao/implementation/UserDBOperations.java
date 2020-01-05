/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.RequestEntity;
import Model.SocketConnection;
import Model.entities.UserEntity;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class UserDBOperations {
    

    public static void login(String  username,String password) {
        UserEntity user = new UserEntity();
        ArrayList<UserEntity> users = new ArrayList<>();
        user.setUsername(username);
        user.setPassword(password);
        users.add(user);
        RequestEntity<UserEntity> request = new RequestEntity("UserDBOperations", "login", users);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));        
    }

    public void loginResponse(ArrayList<Object> object){
        if(object == null){
            System.out.println("login failed");
        }
        else{   
            System.out.println("login success");
        }
    }
    public int register(Object user) {

        return -1;
    }
}
