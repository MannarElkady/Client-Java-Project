/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.RequestCreator;
import Utility.Validation;
import Model.entities.ItemEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

    JFXButton submit;
    BorderPane newBorder;
    boolean flagPressed= false;
    JFXTextField childText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addFriendAction() {
    }

    
    @FXML
    private void addItemAction() {
        
        if(!flagPressed){
            flagPressed = true;
            newBorder= new BorderPane();
            childText = new JFXTextField();
            childText.setPromptText("Write Here Your Item");
            submit = new JFXButton();
            submit.setText("Submit");
            submit.setButtonType(JFXButton.ButtonType.RAISED);
            newBorder.setAlignment(childText, Pos.CENTER);
            BorderPane.setMargin(childText, new Insets(20,20,20,20));
            newBorder.setCenter(childText);
            BorderPane.setMargin(submit, new Insets(20,20,20,20));
            newBorder.setRight(submit);
            vBoxPane.getChildren().add(newBorder);
        }
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Validation.checkString(childText.getText())){
                    ItemEntity newItemEntity= new ItemEntity();
                    newItemEntity.setDescription(childText.getText());
                    Label newItem = new Label(childText.getText());
                    newItem.setPadding(new Insets(20,20,20,20));
                    newItem.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    newBorder.setCenter(newItem);    
                    flagPressed= false;
                    newBorder.setRight(null);
                    RequestCreator newRequest = new RequestCreator("ItemDBOperations","addItem",newItemEntity);
                    String newRequestJson= newRequest.getJsonObject();
                    System.out.println(newRequestJson);
                }
            }
        });
    }
    
    
}
