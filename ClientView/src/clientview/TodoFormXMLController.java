/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.UserDBOperations;
import Model.entities.ItemEntity;
import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TodoFormXMLController implements Initializable {

    @FXML
    private Label todoNameLabel;
    @FXML
    private JFXButton addFriend;
    @FXML
    private VBox vBoxPane;
    @FXML
    private JFXButton addItem;

    JFXButton submit;
    BorderPane newBorder;
    boolean flagPressed = false;
    JFXTextField childText;

    private Image img = null;
    private ImageView imgView = null;
    private Label todoName = null;
    private Label userLabel = null;

    // for Dummy Testing
    ArrayList<TodoEntity> test = new ArrayList();
    ArrayList<UserEntity> test2 = new ArrayList();
    ArrayList<HBox> hBoxPane = new ArrayList();
    HBox child = null;

    @FXML
    private JFXListView<HBox> collaboratorsList;
    @FXML
    private ImageView addNewItem;
    @FXML
    private ImageView addNewFriend;
    @FXML
    private BorderPane rootPane;
    
    static TodoEntity todo= new TodoEntity();

    @FXML
    private JFXButton homeButton;
    
    static ArrayList<Object>itemList = new ArrayList<>();
    
    public static void setItems(ArrayList<Object> list){
        itemList = list;
    }
    public static void clearItemsList(){
        itemList.clear();
    }
    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {        
        todoNameLabel.setText(todo.getTitle());
        setCollaboratorsDummy();
        setCollaboratorsPanes(test2);
        generateCollaboratorListUI();
        loadItems();
        //TodoListDBOperations.getAllItems(todo);

    }
    public static void setToDoData(TodoEntity todoData){
        todo = todoData;
    }

    @FXML
    private void addColaboratorEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddCollaboratorTodoFXML.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setCollaboratorsDummy() {
        UserEntity useraya = new UserEntity();
        useraya.setUsername("colaborayaa");
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);

    }
    
    public void setCollaboratorsPanes(ArrayList<UserEntity> collaboratorsList) {
        for (UserEntity useraya : collaboratorsList) {
            try {
                child = new HBox();
                img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/m.png"));
                imgView = new ImageView(img);
                imgView.setFitHeight(10.0);
                imgView.setFitWidth(10.0);
                userLabel = new Label(useraya.getUsername());
                userLabel.setGraphic(imgView);
                userLabel.paddingProperty();
                userLabel.setPrefSize(100, 30);
                child.getChildren().add(userLabel);
                hBoxPane.add(child);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generateCollaboratorListUI() {
        ObservableList<HBox> items = FXCollections.observableArrayList(hBoxPane);
        collaboratorsList.setItems(items);
    }

    @FXML
    private void addItemAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertItemXML.fxml"));
            Parent insertItemWindow = loader.load();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner((Stage)rootPane.getScene().getWindow());
            Scene dialogScene = new Scene(insertItemWindow);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(TodoFormXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    private void addNewItemLabel(){
       /* ItemEntity newItemEntity = 
        Label newItemTitle = new Label(newItemEntity.getTitle());
        newItemTitle.setPadding(new Insets(10, 10, 10, 10));
        newItemTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        Label newItemDescr = new Label(newItemEntity.getTitle());
        newItemDescr.setPadding(new Insets(10, 10, 10, 10));
        newItemDescr.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        newBorder.setLeft(newItemTitle);
        newBorder.setCenter(newItemDescr);
*/
    }
    
    @FXML
    private void homeButtonAction() throws IOException{
        UserDBOperations.getAllTodos(ClientView.currentUser);      
    }
    
    private void loadItems(){
        
        Label itemText = null;
        for(int i=0;i<itemList.size();i++){
        
            itemText = new Label(((ItemEntity)itemList.get(i)).getTitle()); 
            vBoxPane.getChildren().add(itemText);
            //wait).
            
        }

    }
}
