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
import clientview.ClientView;
import clientview.MainXMLController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author dell
 */
public class UserDBOperations {

    public static void login(String username, String password) {

        UserEntity user = new UserEntity();
        ArrayList<UserEntity> users = new ArrayList<>();
        user.setUsername(username);
        user.setPassword(password);
        users.add(user);
        RequestEntity<UserEntity> request = new RequestEntity("UserDBOperations", "login", users);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
    }

    public void loginResponse(ArrayList<Object> object) {
        if (object == null || object.size() == 0) {
            System.out.println("login failed");
        } else {
        
            System.out.println(((UserEntity)object.get(0)).getId());
            ClientView.currentUser = (UserEntity)object.get(0);
            getAllTodos(ClientView.currentUser);
        }
    }

    public static void register(UserEntity user) {

        ArrayList<UserEntity> users = new ArrayList<>();
        users.add(user);
        RequestEntity<UserEntity> request = new RequestEntity("UserDBOperations", "register", users);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(request));
    }

    public void registerResponse(ArrayList<Object> object) {
          if (object== null|| object.isEmpty()) {
              System.out.println("registration field");      
          } else {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/clientview/authentication/loginXML.fxml"));
                Scene scene = ClientView.mainStage.getScene();
                root.translateYProperty().set(scene.getHeight());
                scene.setRoot(root);
                /*Timeline timeLine = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
                timeLine.getKeyFrames().add(kf);
                timeLine.play();*/

            } catch (IOException ex) {
                Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
    
    public static void getAllTodos(UserEntity userID){
        ArrayList<UserEntity> list = new ArrayList<>();
        list.add(userID);
        RequestEntity<Integer> addRequest = new RequestEntity("UserDBOperations", "getAllTodos", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(addRequest));
    }
    
    public void getAllTodosResonse(ArrayList<Object> items){
        if(items.size() == 0){
            System.out.println("No Items");
        }else{
            try {
                MainXMLController.setTodos(items);
                
                Parent root = FXMLLoader.load(getClass().getResource("/clientview/mainXML.fxml"));                
                Scene scene = ClientView.mainStage.getScene();
                //root.translateYProperty().set(scene.getHeight());
                //ClientView.mainStage.setWidth(ClientView.mainStage.getScene().getWidth());            
               // ClientView.mainStage.setHeight(ClientView.mainStage.getScene().getHeight());
                scene.setRoot(root);  
                
                /*Timeline timeLine = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
                timeLine.getKeyFrames().add(kf);
                timeLine.play();*/
                
            } catch (IOException ex) {
                Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
      public static void getFrinds(UserEntity userID){
        ArrayList<UserEntity> list = new ArrayList<>();
        list.add(userID);
        RequestEntity<Integer> addRequest = new RequestEntity("UserDBOperations", "getFrinds", list);
        SocketConnection.getInstance().getPrintStreamInstance().println(GsonParser.parseToJson(addRequest));
    }
    
    public void getFrindsResonse(ArrayList<UserEntity> items){
        for(int i=0;i<items.size();i++)
        {
            System.out.println("hh"+items.get(i).getUsername());
        
        }
        
      MainXMLController.setFriendList(items);
    }
    
    

}
