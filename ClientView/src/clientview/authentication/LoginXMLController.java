/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview.authentication;

import Model.RequestCreator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.SocketConnection;
import Model.dao.implementation.NotificationDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.NotificationEntity;
import Model.entities.UserEntity;
import Utility.Validation;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import clientview.ClientView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LoginXMLController implements Initializable {

    @FXML
    JFXTextField userNameTextField;
    @FXML
    JFXPasswordField passwordPasswordField;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton signUpButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void loginAction() {
        if (!SocketConnection.getInstance().isServerClosed()) {
            String username = userNameTextField.getText(), password = passwordPasswordField.getText();
            if (Validation.checkString(username) && Validation.checkString(password)) {
                if (Validation.checkUsernameRegex(username)) {
                UserEntity loginUser= new UserEntity();
                loginUser.setUsername(userNameTextField.getText());
                loginUser.setPassword(passwordPasswordField.getText());
                loginUser.setOnlineFlag(1);
                RequestCreator newRequest = new RequestCreator("UserDBOperations","login",loginUser);
                String newRequestJson= newRequest.getJsonObject();
                System.out.println(newRequestJson);
                    UserDBOperations.login(username, password);

                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("The  server is closed!");
            alert.showAndWait();
        }
    }

    @FXML
    private void signUpAction(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/clientview/authentication/RegisterXML.fxml"));
            Scene scene = ClientView.mainStage.getScene();
            root.translateYProperty().set(scene.getHeight());
            ClientView.mainStage.setWidth(629);
            ClientView.mainStage.setHeight(637);
            scene.setRoot(root);
            Timeline timeLine = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
            timeLine.getKeyFrames().add(kf);
            timeLine.play();
        } catch (IOException ex) {
            Logger.getLogger(UserDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  
      public void buttonAction(){
        ArrayList<Object> data = new ArrayList<>();
        NotificationEntity notification = new NotificationEntity();
        notification.setHeader("test Header");
        notification.setText("test Text");
        notification.setNotificationType("test Notification");
        notification.setSenderID(2);
        data.add(notification);
        NotificationDBOperations.sendNotification(data);
    }
}
