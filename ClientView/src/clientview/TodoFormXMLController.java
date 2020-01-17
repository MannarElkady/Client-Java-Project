/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.CollaboratorsListActionListener;
import Model.ItemDeletingActionListener;
import Model.ItemUpdatingActionListener;
import Model.TodoSelectedItemHandler;
import Model.dao.implementation.ComponentDBOperations;
import Model.dao.implementation.TodoListDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.ComponentEntity;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TodoFormXMLController implements Initializable, EventHandler<ActionEvent> {
    Button tasks;
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
    static ArrayList<HBox> hBoxPane = new ArrayList();
    HBox child = null;
    private Accordion accordion;
    private Label descriptionAndDeadline;
    @FXML
    private JFXListView<HBox> collaboratorsList;
    @FXML
    private ImageView addNewItem;
    @FXML
    private ImageView addNewFriend;
    @FXML
    private BorderPane rootPane;

    public static TodoEntity todo = new TodoEntity();
    @FXML
    private JFXButton homeButton;

    private BorderPane borderItem;

    static ArrayList<Object> itemList = new ArrayList<>();
    @FXML
    private JFXButton editTodo;
    @FXML
    private JFXButton deleteTodo;
    @FXML
    private ImageView addNewItem11;
    @FXML
    private JFXButton notificationButton;
    private JFXButton showItem;
    private JFXButton showItemCollaborators;
    private JFXButton editItemDetails;
    private JFXButton deleteItem;
    @FXML
    private BorderPane borderZft;
    TitledPane itemInList;
    @FXML
    private ImageView editimg;
    private Label todoDetails;
    @FXML
    private VBox todoDetailsLi;
    private VBox vbox;
    public static ItemEntity itemSelected = new ItemEntity();
   // public Stage stage= (Stage) rootPane.getScene().getWindow();
    public Stage stage= ClientView.mainStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateUi();
    }

    public static void setItems(ArrayList<Object> list) {
        itemList = list;
    }

    public static void clearItemsList() {
        itemList.clear();
    }

    public static void clearTest() {
        test2.clear();
    }

    public static void setToDoData(TodoEntity todoData) {
        todo = todoData;
    }

    public void fillTodoDetails() {
        todoDetails = new Label();
        todoDetails.setText("Assign Date: " + todo.getAssignDate()
                + "\nDeadline Date: " + todo.getDeadlineDate() + "\n Description: " + todo.getDescription());
        todoDetails.setFont(new Font("Arial", 16));
        todoDetails.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;");
        todoDetails.setWrapText(true);
        todoDetails.setTextFill(Color.web("#313749"));
        todoDetails.setAlignment(Pos.CENTER);
        todoDetailsLi.setAlignment(Pos.CENTER);
        todoDetailsLi.getChildren().add(todoDetails);

    }

    @FXML
    private void addColaboratorEvent() throws IOException {
       AddCollaboratorTodoController.isAddCollaborator=true;
        UserDBOperations.getFrinds(ClientView.currentUser);
//        Parent root = FXMLLoader.load(getClass().getResource("AddCollaboratorTodoFXML.fxml"));
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
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
                userLabel.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                userLabel.setPrefSize(100, 30);
                child.getChildren().add(userLabel);
                hBoxPane.add(child);
            } catch (FileNotFoundException ex) {
             ex.printStackTrace();
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
    private void homeButtonAction() {
        UserDBOperations.getAllTodos(ClientView.currentUser);
    }

    private void loadItems() {
        if (itemList != null) {
            accordion = new Accordion();
            for (int i = 0; i < itemList.size(); i++) {
                ItemEntity item = (ItemEntity) itemList.get(i);
                vbox = new VBox();
                descriptionAndDeadline = new Label("Item Description: " + item.getDescription() + "\nDeadline Date:  " + item.getDeadlineDate().toString());
                descriptionAndDeadline.setFont(new Font("Arial", 22));
                descriptionAndDeadline.setAlignment(Pos.CENTER);
                descriptionAndDeadline.setWrapText(true);
                showItem = new JFXButton("Show Tasks");
                showItem.setButtonType(JFXButton.ButtonType.RAISED);
                showItem.setFont(new Font("Arial", 18));
                showItem.setAlignment(Pos.CENTER);
                showItem.setOnAction(this);
                showItem.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;-fx-background-color: #ffffff;");
                showItemCollaborators = new JFXButton("Show Collaborators");
                showItemCollaborators.setAlignment(Pos.CENTER);
                showItemCollaborators.setFont(new Font("Arial", 18));
                showItemCollaborators.setButtonType(JFXButton.ButtonType.RAISED);
                showItemCollaborators.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;-fx-background-color: #ffffff;");
                showItemCollaborators.setOnAction(new CollaboratorsListActionListener());
                editItemDetails = new JFXButton("Edit");
                editItemDetails.setAlignment(Pos.CENTER);
                editItemDetails.setFont(new Font("Arial", 18));
                editItemDetails.setButtonType(JFXButton.ButtonType.RAISED);
                editItemDetails.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;-fx-background-color: #ffffff;");
                editItemDetails.setOnAction(new ItemUpdatingActionListener(stage,item));
                deleteItem = new JFXButton("Delete");
                deleteItem.setAlignment(Pos.CENTER);
                deleteItem.setFont(new Font("Arial", 18));
                deleteItem.setButtonType(JFXButton.ButtonType.RAISED);
                deleteItem.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;-fx-background-color: #ffffff;");
                deleteItem.setOnAction(new ItemDeletingActionListener(item.getItemID()));
                vbox.getChildren().add(descriptionAndDeadline);
                vbox.getChildren().add(showItem);
                vbox.getChildren().add(showItemCollaborators);
                vbox.getChildren().add(editItemDetails);
                vbox.getChildren().add(deleteItem);
                vbox.setSpacing(10);
                vbox.setAlignment(Pos.CENTER);
                itemInList = new TitledPane(item.getTitle(), vbox);
                itemInList.setPadding(new Insets(0, 5, 5, 5));
                itemInList.setFont(new Font("Arial", 22));
                itemInList.setId(String.valueOf(item.getItemID()));
                itemInList.expandedProperty().addListener(new TodoSelectedItemHandler(itemInList));
                accordion.getPanes().add(itemInList);
            }
            vBoxPane.getChildren().add(accordion);
        }
    }

    public static void setCollaboratorList(ArrayList<UserEntity> collaborators) {
        test2 = collaborators;
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
    private void notificationButtonAction() {
    }

    private void updateUi() {
        todoNameLabel.setText(todo.getTitle());
        if (todo.getCreatorId() != ClientView.currentUser.getId()) {
            deleteTodo.setVisible(false);
            editTodo.setVisible(false);
        }
        //setCollaboratorsDummy();
        setCollaboratorsPanes(test2);
        generateCollaboratorListUI();
        fillTodoDetails();
        loadItems();
        //TodoListDBOperations.getAllItems(todo);  
    }

    @Override
    public void handle(ActionEvent event) {
        ComponentEntity componentEntity= new ComponentEntity(TodoFormXMLController.itemSelected.getItemID(),null,null,0);
        ComponentDBOperations.retrieveAllComponent(componentEntity);
    }
}
