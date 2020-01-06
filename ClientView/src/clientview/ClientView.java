/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.SocketConnection;
import Model.dao.implementation.NotificationDBOperations;
import Model.entities.NotificationEntity;
import com.jfoenix.controls.JFXListView;
import java.awt.MouseInfo;
import java.awt.Point;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author DELL
 */
public class ClientView extends Application {

    SocketConnection object;
    
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
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
        launch(args);

    }
    
  
}
