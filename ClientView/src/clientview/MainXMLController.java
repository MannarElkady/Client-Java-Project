/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MainXMLController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton addFriendBtn;
    @FXML
    private ImageView appLogo;
    @FXML
    private JFXButton addTodo;
    @FXML
    private ImageView userImg;
    @FXML
    private JFXListView<HBox> friendListPane;
    @FXML
    private JFXMasonryPane jMasonaryPane;
    
    private Image img=null;
    private ImageView imgView=null;
    private Label todoName=null;
    private Label userLabel=null;

    
    // for Dummy Testing
    ArrayList <TodoEntity>test=new ArrayList();
    ArrayList <UserEntity>test2=new ArrayList();
    ArrayList <HBox> hBoxPane =new ArrayList();
    HBox child=null;
    public void setFriendListDummy(){
        UserEntity useraya= new UserEntity();
        useraya.setUsername("Userayaa");
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        
    }
    public void setFriendListPanes(ArrayList <UserEntity> friendList){
        for(UserEntity useraya: friendList){
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
    public void setTodoDummy(){
        TodoEntity todo=new TodoEntity();
        todo.setTitle("New Item");
        test.add(todo);
        test.add(todo);
        test.add(todo);
        test.add(todo);
        test.add(todo);
    }
    /**
     * Initializes the controller class.
     */
    public void generateTodosUI(ArrayList <TodoEntity> todoNames){
        for(TodoEntity todo: todoNames){
        try {
            todoName = new Label(todo.getTitle());
            System.out.println("Working Directory = " +
              System.getProperty("user.dir"));  
            img= new Image(new FileInputStream(System.getProperty("user.dir")+"/src/clientview/resources/todo.jpg"));
            imgView=new ImageView(img);
            imgView.setFitHeight(50.0);
            imgView.setFitWidth(50.0);
            
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                todoName.setGraphic(imgView);
                todoName.paddingProperty();
                todoName.setPadding(new Insets(15));
                todoName.setPrefSize(100,100);
                todoName.setStyle("-fx-background-color:POWDERBLUE");
                jMasonaryPane.getChildren().add(todoName);
        }
    }
    
    
    public void generateFriendListUI(){
            ObservableList<HBox> items =FXCollections.observableArrayList(hBoxPane);
            friendListPane.setItems(items);
  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTodoDummy();
        setFriendListDummy();
        setFriendListPanes(test2);
        generateTodosUI(test);
        generateFriendListUI();
    }

    @FXML
    private void homeBtnAction(ActionEvent event) {
    }

    @FXML
    private void addFriendBtnAction(ActionEvent event) {
    }

    @FXML
    private void addTodoAction(ActionEvent event) {
    }
}    