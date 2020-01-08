/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview.authentication;

import Model.RequestCreator;
import Model.RequestEntity;
import Model.SocketConnection;
import Utility.Validation;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.dao.implementation.UserDBOperations;
import clientview.ClientView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RegisterXMLController implements Initializable {

    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXButton signUpButton;
    @FXML
    private JFXPasswordField passwordPasswordField;
    @FXML
    private JFXButton loginButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signUpAction(ActionEvent event) {
        if (!SocketConnection.getInstance().isServerClosed()) {
            String email = emailTextField.getText(), password = passwordPasswordField.getText(), username = usernameTextField.getText();
            if (Validation.checkString(username) && Validation.checkString(password) && Validation.checkString(email)) {
                if (Validation.checkEmailRegex(email) && Validation.checkUsernameRegex(username)) {
                    UserEntity newUser = new UserEntity();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.setOnlineFlag(0);
                    UserDBOperations.register(newUser);
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
    private void loginButtonAction(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/clientview/authentication/loginXML.fxml"));
            Scene scene = ClientView.mainStage.getScene();
            root.translateYProperty().set(scene.getHeight());
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

}
