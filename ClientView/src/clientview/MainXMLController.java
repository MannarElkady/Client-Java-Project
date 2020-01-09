/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;


import Model.MainFormHandler;
import Model.dao.implementation.UserDBOperations;
import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MainXMLController implements Initializable {

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

    public static ArrayList<Object> data;
    // for Dummy Testing
    ArrayList <TodoEntity>test=new ArrayList();
   public static ArrayList <UserEntity>test2=new ArrayList();
    ArrayList <HBox> hBoxPane =new ArrayList();
    HBox child=null;
    @FXML
    private BorderPane mainBorderPane;
    public void setFriendListDummy(){
        UserEntity useraya= new UserEntity();
        useraya.setUsername("Userayaa");
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
              
    }
    public void setFriendListPanes(){
        for(UserEntity useraya: test2){
            try {
                child = new HBox();
                img= new Image(new FileInputStream(System.getProperty("user.dir")+"/src/clientview/resources/m.png"));
                imgView=new ImageView(img);
                imgView.setFitHeight(10.0);
                imgView.setFitWidth(10.0);
                userLabel=new Label(useraya.getUsername());
                userLabel.setGraphic(imgView);
                userLabel.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                userLabel.paddingProperty();
                userLabel.setPrefSize(100,30);
                child.getChildren().add(userLabel);
                child.setStyle("-fx-background-color:POWDERBLUE;-fx-background-radius:30;-fx-border-radius:30;");
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
    public static void setTodos(ArrayList<Object> list){
        data = list;
    }
    /**
     * Initializes the controller class.
     */
    public void generateTodosUI(ArrayList <Object> todoNames){
        System.out.println("TEST 2");
        for(int i = 0 ;i< data.size();i++){
            TodoEntity todo=null;
        try {
             todo = (TodoEntity)data.get(i);
            todoName = new Label(todo.getTitle());
            System.out.println("Working Directory = " +System.getProperty("user.dir"));  
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
                todoName.setPrefSize(200,100);
                todoName.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                todoName.setId(String.valueOf(todo.getId()));
                todoName.addEventFilter(MouseEvent.MOUSE_CLICKED, new MainFormHandler());
                todoName.setWrapText(true);
                jMasonaryPane.getChildren().add(todoName);
        }
    }
    
    
    public void generateFriendListUI(){
            ObservableList<HBox> items =FXCollections.observableArrayList(hBoxPane);
            friendListPane.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
            friendListPane.setItems(items);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFriendListPanes();
        generateTodosUI(new ArrayList<Object>());
        generateFriendListUI();
      

    }

  
    @FXML
    private void addFriendBtnAction(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/clientview/AddFrindFXML.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void addTodoAction(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertTodoXML.fxml"));
            Parent insertItemWindow = loader.load();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner((Stage)mainBorderPane.getScene().getWindow());
            Scene dialogScene = new Scene(insertItemWindow);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(TodoFormXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAllTodos() {
        UserEntity user = new UserEntity();
        user.setId(ClientView.currentUser.getId());
        UserDBOperations.getAllTodos(user);
    }
    
    
    
    public static void  setFriendList(ArrayList<UserEntity> a){         
           test2=a;   
    }

}    
