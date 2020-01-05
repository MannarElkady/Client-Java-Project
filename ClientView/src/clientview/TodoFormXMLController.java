/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.RequestCreator;
import Utility.Validation;
import Model.entities.ItemEntity;
import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    
    private Image img=null;
    private ImageView imgView=null;
    private Label todoName=null;
    private Label userLabel=null;

    
    // for Dummy Testing
    ArrayList <TodoEntity>test=new ArrayList();
    ArrayList <UserEntity>test2=new ArrayList();
    ArrayList <HBox> hBoxPane =new ArrayList();
    HBox child=null;
    
    
    @FXML
    private JFXListView<HBox> collaboratorsList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCollaboratorsDummy();
        setCollaboratorsPanes(test2);
        generateCollaboratorListUI();
        
    }    

    @FXML
    private void addFriendAction() {
    }
    
    
    public void setCollaboratorsDummy(){
        UserEntity useraya= new UserEntity();
        useraya.setUsername("Userayaa");
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        
    }
    public void setCollaboratorsPanes(ArrayList <UserEntity> collaboratorsList){
        for(UserEntity useraya: collaboratorsList){
            try {
                child = new HBox();
                img= new Image(new FileInputStream(System.getProperty("user.dir")+"/src/clientview/resources/m.png"));
                imgView=new ImageView(img);
                imgView.setFitHeight(10.0);
                imgView.setFitWidth(10.0);
                userLabel=new Label(useraya.getUsername());
                userLabel.setGraphic(imgView);
                userLabel.paddingProperty();
                userLabel.setPrefSize(100,30);
                child.getChildren().add(userLabel);
                hBoxPane.add(child);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
     public void generateCollaboratorListUI(){
            ObservableList<HBox> items =FXCollections.observableArrayList(hBoxPane);
            collaboratorsList.setItems(items);
  
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
