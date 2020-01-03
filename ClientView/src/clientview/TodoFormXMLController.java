/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TodoFormXMLController implements Initializable {

    @FXML
    private Label todoNameLabel;
    @FXML
    private ImageView appLogo;
    @FXML
    private JFXButton addFriend;
    @FXML
    private ImageView userImg;
    @FXML
    private VBox vBoxPane;
    @FXML
    private JFXButton addItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addFriendAction(ActionEvent event) {
    }

    @FXML
    private void addItemAction(ActionEvent event) {
        BorderPane newBorder= new BorderPane();
        JFXTextField child = new JFXTextField();
        JFXButton submit = new JFXButton();
        submit.setText("Submit Item");
        submit.setButtonType(JFXButton.ButtonType.RAISED);
        newBorder.setAlignment(child, Pos.CENTER);
        BorderPane.setMargin(child, new Insets(12,12,12,12));
        newBorder.setCenter(child);
        BorderPane.setMargin(submit, new Insets(12,12,12,12));
        newBorder.setRight(submit);
        vBoxPane.getChildren().add(newBorder);
       // submit.addEventHandler(EventType.ROOT, eventHandler);
    }
    
    
}
