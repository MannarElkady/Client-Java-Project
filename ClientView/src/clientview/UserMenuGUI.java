/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.UserDBOperations;
import static clientview.TodoFormXMLController.todo;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author dell
 */
public class UserMenuGUI implements EventHandler<ActionEvent> {

    Stage dialog;
    public UserMenuGUI() {

        createUserMenu();
    }

    public void createUserMenu() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                VBox root = new VBox();
                Label usernameLabel = new Label("Username : " + ClientView.currentUser.getUsername());
                JFXButton logoutButton = new JFXButton("logout");
                logoutButton.setStyle("-fx-background-color: #0000ff;-fx-text-fill : white;-fx-margin :50 50 50 50;");
                logoutButton.setOnAction(UserMenuGUI.this);
                root.getChildren().add(usernameLabel);
                root.getChildren().add(logoutButton);

                root.setAlignment(Pos.CENTER);
                root.setStyle("-fx-background-color: #ccebff");
                dialog = new Stage();
                dialog.initStyle(StageStyle.UNDECORATED);
                //dialog.initModality(Modality.APPLICATION_MODAL);
                //dialog.initOwner((Stage) ClientView.mainStage.getScene().getWindow());
                Scene dialogScene = new Scene(root, 250, 150);                     
                dialog.setScene(dialogScene);
                ImageView notificationIcon =(ImageView) ClientView.mainStage.getScene().lookup("#userMenu");  
                if(notificationIcon!=null){
                    dialog.setX(ClientView.mainStage.getX() + notificationIcon.getLayoutX() -100);
                    dialog.setY(ClientView.mainStage.getY() + notificationIcon.getLayoutY() +90);               
                }
                dialog.show();
                dialog.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                    if (!isNowFocused) {
                        dialog.hide();
                    }
                });
            }
        });
    }

    @Override
    public void handle(ActionEvent event) {

        UserDBOperations.logout(ClientView.currentUser);
          try {
              
                ClientView.mainStage.setWidth(700);
                ClientView.mainStage.setHeight(480);
                ClientView.mainStage.setResizable(false);
                Parent root = FXMLLoader.load(getClass().getResource("/clientview/authentication/loginXML.fxml"));
                Scene scene = ClientView.mainStage.getScene();
                
                scene.setRoot(root);            
                dialog.hide();
            } catch (IOException ex) {
                Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
