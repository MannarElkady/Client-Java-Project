/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.RequestCreator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.entities.UserEntity;

/**
 *
 * @author DELL
 */
public class ClientView extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TodoFormXML.fxml"));
        String [] name= {"Ebrahim 1","Manar 2","yehia 3"};
     //   MainXMLBase root =new MainXMLBase(name);
        Scene scene = new Scene(root);
        stage.setScene(scene);
     //   stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
