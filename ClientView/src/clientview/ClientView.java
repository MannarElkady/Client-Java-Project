/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.SocketConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.stage.WindowEvent;

/**
 *
 * @author DELL
 */
public class ClientView extends Application {
    SocketConnection object;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/clientview/authentication/loginXML.fxml"));
        String [] name= {"Ebrahim 1","Manar 2","yehia 3"};
     //   MainXMLBase root =new MainXMLBase(name);
        Scene scene = new Scene(root);
        stage.setScene(scene);
     //   stage.setResizable(false);
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
