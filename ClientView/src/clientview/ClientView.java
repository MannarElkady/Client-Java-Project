/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.SocketConnection;
import Model.entities.UserEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author DELL
 */
public class ClientView extends Application {

    SocketConnection object;
    
    public static Stage mainStage;
    public static UserEntity currentUser;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        currentUser= new UserEntity();
      //Parent root = FXMLLoader.load(getClass().getResource("TodoFormXML.fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("/clientview/authentication/loginXML.fxml"));
        String [] name= {"Ebrahim 1","Manar 2","yehia 3"};
        //MainXMLBase root =new MainXMLBase(name);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //   stage.setResizable(false);
       
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event) -> {
            try {
                SocketConnection.getInstance().closeSocketConnection();
            } catch (IOException ex) {
                Logger.getLogger(ClientView.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.exit();
            System.exit(0);
        });
        SocketConnection.getInstance();
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("test2");
        launch(args);

    }
    
  
}
