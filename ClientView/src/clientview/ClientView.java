/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.SocketConnection;
import Model.dao.implementation.UserDBOperations;
import Model.dao.implementation.ComponentDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.ComponentEntity;
import Model.entities.UserEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;

import java.util.ArrayList;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author DELL
 */
public class ClientView extends Application {

    public static Stage mainStage;
    public static UserEntity currentUser;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        currentUser = new UserEntity();
        Parent root = FXMLLoader.load(getClass().getResource("/clientview/authentication/loginXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

        stage.setOnCloseRequest((WindowEvent event) -> {
            try {
                UserDBOperations.logout(ClientView.currentUser);
                SocketConnection.getInstance().closeSocketConnection();
                UserDBOperations.logout(ClientView.currentUser);   
            } catch (IOException ex) {
                Logger.getLogger(ClientView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Platform.exit();
            System.exit(0);
        });
        SocketConnection.getInstance();
        ArrayList<Integer> users = new ArrayList<>();        
        users.add(1);
       
        //NotificationDBOperations.receiveNotifications(users);
                
/* TodoEntity todo = new TodoEntity(2, "jljlk", "#1212", 1, "Done", "New description", Date.valueOf("2020-01-01"), Date.valueOf("2020-12-01"));
        TodoListDBOperations.deleteTodo(todo);*/
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
