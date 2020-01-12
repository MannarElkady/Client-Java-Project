/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.TodoListDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.ItemEntity;
import Model.entities.TodoEntity;
import Model.entities.UserEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
   static ArrayList<UserEntity> test2 = new ArrayList();
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
    
    public static TodoEntity todo= new TodoEntity();
    @FXML
    private JFXButton homeButton;
    
    private BorderPane borderItem;

    static ArrayList<Object> itemList = new ArrayList<>();

    public static void setItems(ArrayList<Object> list) {
        itemList = list;
    }

    public static void clearItemsList() {
        itemList.clear();
    }
    
     public static void clearTest(){
         test2.clear();
     }
    @FXML
    private JFXButton editTodo;
    @FXML
    private ImageView addNewItem1;
    @FXML
    private JFXButton deleteTodo;
    @FXML
    private ImageView addNewItem11;
    @FXML
    private JFXListView<?> todoDetails;
    @FXML
    private JFXButton notificationButton;
    
    private JFXNodesList itemDetails;
    @FXML
    private BorderPane borderZft;
    TitledPane itemInList;
         /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateUi();
    }

    public static void setToDoData(TodoEntity todoData) {
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

    private void addNewGridItem(){
        
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
                userLabel.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
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
            dialog.initOwner((Stage) rootPane.getScene().getWindow());
            Scene dialogScene = new Scene(insertItemWindow);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(TodoFormXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void homeButtonAction(){
        UserDBOperations.getAllTodos(ClientView.currentUser);
    }
    /*
    TitledPane pane1 = new TitledPane("Boats" , new Label("Show all boats available"));
        TitledPane pane2 = new TitledPane("Cars"  , new Label("Show all cars available"));
        TitledPane pane3 = new TitledPane("Planes", new Label("Show all planes available"));

        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        accordion.getPanes().add(pane3);

        VBox vBox = new VBox(accordion);
        Scene scene = new Scene(vBox);
    */
    private void loadItems() {
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                ItemEntity item = (ItemEntity) itemList.get(i);
                final Label itemText = new Label(item.getTitle());
                if(i==0){
                    itemText.setPadding(new Insets(25,10,25,10));
                }
                else{
                    itemText.setPadding(new Insets(10,10,10,10));
                }
                itemText.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                itemText.setFont(new Font("Arial", 24));
                itemText.setPadding(new Insets(10,10,10,10));
                itemText.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        itemText.setPrefWidth(itemText.getText().length() * 16);
                    }
                    
                });
                
                 Accordion accordion = new Accordion();

                
                Label descriptionAndDeadline = new Label(item.getDescription()+"/n Deadline Date: "+item.getDeadlineDate().toString());
                descriptionAndDeadline.setFont(new Font("Arial", 16));
                itemInList = new TitledPane(item.getTitle(), descriptionAndDeadline);
                itemInList.setPadding(new Insets(10,10,10,10));
                vBoxPane.getChildren().add(itemInList);
            }

        }
    }
    /*
    private void loadItems() {
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                ItemEntity item = (ItemEntity) itemList.get(i);
                final Label itemText = new Label(item.getTitle());
                if(i==0){
                    itemText.setPadding(new Insets(25,10,25,10));
                }
                else{
                    itemText.setPadding(new Insets(10,10,10,10));
                }
                itemText.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                itemText.setFont(new Font("Arial", 24));
                itemText.setPadding(new Insets(10,10,10,10));
                itemText.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        itemText.setPrefWidth(itemText.getText().length() * 16);
                    }
                    
                });
                JFXButton itemDetailsButton = new JFXButton("More Item Details?");
                itemDetailsButton.setButtonType(JFXButton.ButtonType.RAISED);
                itemDetailsButton.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                Label description = new Label(item.getDescription());
                description.setFont(new Font("Arial", 16));
                Label deadline= new Label("Deadline Date:   "+item.getDeadlineDate().toString());
                deadline.setFont(new Font("Arial", 16));
                itemDetails =new JFXNodesList();
                int descriptionLength = item.getDescription().split("\r\n|\r|\n").length;
                itemDetails.addAnimatedNode(itemDetailsButton);
                itemDetails.addAnimatedNode(description);
                itemDetails.addAnimatedNode(deadline);
                borderItem = new BorderPane();
                borderItem.setCenter(itemText);
                borderItem.setLayoutY(descriptionLength);
                borderItem.setRight(itemDetails);
                borderItem.setPadding(new Insets(10,10,10,10));
                vBoxPane.getChildren().add(borderItem);
            }

        }
    }*/
     public static void  setCollaboratorList(ArrayList<UserEntity> collaborators){ 
            test2.clear();
           test2=collaborators;   
    }

    @FXML
    private void editTodoAction(ActionEvent event) {
         try {
            InsertTodoXMLController.setTodoData(todo);
            InsertTodoXMLController.isUpdate = true;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertTodoXML.fxml"));
            Parent insertItemWindow = loader.load();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner((Stage) rootPane.getScene().getWindow());
            Scene dialogScene = new Scene(insertItemWindow);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(TodoFormXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteTodoAction() {
        TodoListDBOperations.deleteTodo(todo);   
    }
    @FXML
    private void notificationButtonAction(){
    }

    private void updateUi() {
        todoNameLabel.setText(todo.getTitle());
        //setCollaboratorsDummy();
        setCollaboratorsPanes(test2);
        generateCollaboratorListUI();
        loadItems();
        //TodoListDBOperations.getAllItems(todo);  
    }
}
