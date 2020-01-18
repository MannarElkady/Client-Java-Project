/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.CollaboratorHandler;
import Model.CollaboratorsListActionListener;
import Model.ItemAddingCollaboratorActionListener;
import Model.ItemDeletingActionListener;
import Model.ItemUpdatingActionListener;
import Model.TodoSelectedItemHandler;
import Model.dao.implementation.ComponentDBOperations;
import Model.dao.implementation.TodoListDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.ComponentEntity;
import Model.entities.ItemEntity;
import Model.entities.TodoCollaboratorEntity;
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
import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @FXML
    private ImageView leaveTodo;
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
    public static ArrayList<UserEntity> usersList = new ArrayList();

    static ArrayList<UserEntity> todoCollaborators = new ArrayList();

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
    private JFXButton addItemCollaborator;
    @FXML
    private BorderPane borderZft;
    TitledPane itemInList;
    @FXML
    private ImageView editimg;
    private Label todoDetails;
    @FXML
    private VBox todoDetailsLi;
    private VBox vbox;
    private GridPane itemButtonsGrid;
    public static ItemEntity itemSelected = new ItemEntity();
    // public Stage stage= (Stage) rootPane.getScene().getWindow();
    public Stage stage = ClientView.mainStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientView.whichScreen = todo.getId()+"";
        updateUi();

    }

    public static void setItems(ArrayList<Object> list) {
        itemList = list;
    }

    public static void clearItemsList() {
        itemList.clear();
    }

    public static void clearTest() {
        usersList.clear();
        todoCollaborators.clear();
    }

    public static void setToDoData(TodoEntity todoData) {
        todo = todoData;
    }

    public void fillTodoDetails() {
        todoDetails = new Label();
        todoDetails.setText("Assign Date: " + todo.getAssignDate()
                + "\nDeadline Date: " + todo.getDeadlineDate() + "\n Description: " + todo.getDescription());
        todoDetails.setFont(new Font("Open Sans", 16));
        todoDetails.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold; -fx-background-color:#ffffffff");
        todoDetails.setWrapText(true);
        todoDetails.setTextFill(Color.web("#313749"));
        todoDetails.setAlignment(Pos.CENTER);
        todoDetailsLi.setAlignment(Pos.CENTER);
        todoDetailsLi.setPadding(new Insets(10,10,10,10));
        todoDetailsLi.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold; -fx-background-color:#ffffffff");
        todoDetailsLi.getChildren().add(todoDetails);

    }

    @FXML
    private void addColaboratorEvent() throws IOException {
        AddCollaboratorTodoController.isAddCollaborator = true;
        UserDBOperations.getFrinds(ClientView.currentUser);
//        Parent root = FXMLLoader.load(getClass().getResource("AddCollaboratorTodoFXML.fxml"));
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    public void setCollaboratorsDummy() {
        UserEntity user = new UserEntity();
        user.setUsername("colaborayaa");
        usersList.add(user);
        usersList.add(user);
        usersList.add(user);
        usersList.add(user);
        usersList.add(user);
    }

    public void setCollaboratorsPanes() {
        hBoxPane.clear();
        for (UserEntity user : usersList) {
            try {
      System.out.println("g"+usersList.size());
                child = new HBox();
                if(user.getOnlineFlag() == 1)
                    img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/online.png"));
                else
                    img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/offline.png"));
                imgView = new ImageView(img);
                imgView.setFitHeight(10.0);
                imgView.setFitWidth(10.0);
                userLabel = new Label(user.getUsername());
                userLabel.setGraphic(imgView);
              //  userLabel.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                userLabel.setPrefSize(100, 30);
                child.getChildren().add(userLabel);
                if (todo.getCreatorId() == ClientView.currentUser.getId()){
                    Button removeCollaboratrButton = new Button("-");
                    removeCollaboratrButton.setId(""+user.getId());                    
                    removeCollaboratrButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new CollaboratorHandler());
                    child.getChildren().add(removeCollaboratrButton);
                }
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
               // vbox = new VBox();
                itemButtonsGrid = new GridPane();
                descriptionAndDeadline = new Label("Item Description: " + item.getDescription() + "\nDeadline Date:  " + item.getDeadlineDate().toString());
                descriptionAndDeadline.setFont(new Font("Open Sans", 14));
                descriptionAndDeadline.setAlignment(Pos.TOP_LEFT);
                descriptionAndDeadline.setWrapText(true);
                showItem = new JFXButton("Tasks");
                generateItemButtonUi(showItem,this);
                addItemCollaborator = new JFXButton("Collaborator +");
                generateItemButtonUi(addItemCollaborator,new ItemAddingCollaboratorActionListener(stage));
                showItemCollaborators = new JFXButton("Collaborators");
                generateItemButtonUi(showItemCollaborators,new CollaboratorsListActionListener());
                editItemDetails= new JFXButton("Edit");
                generateItemButtonUi(editItemDetails,new ItemUpdatingActionListener(stage,item));
                deleteItem= new JFXButton("Delete");
                generateItemButtonUi(deleteItem,new ItemDeletingActionListener(item));
                itemButtonsGrid.add(descriptionAndDeadline, 0, 0,5,1);
                itemButtonsGrid.add(showItem, 0, 1);
                itemButtonsGrid.add(addItemCollaborator, 1, 1);
                itemButtonsGrid.add(showItemCollaborators, 2, 1);
                if(item.getCreatorID() == ClientView.currentUser.getId()){
                    itemButtonsGrid.add(editItemDetails, 3, 1);
                    itemButtonsGrid.add(deleteItem, 4, 1);
                }
                itemInList = new TitledPane(item.getTitle(), itemButtonsGrid);
                itemInList.setPadding(new Insets(5, 5, 5, 5));
                itemInList.setFont(new Font("Open Sans", 18));
                itemInList.expandedProperty().addListener(new TodoSelectedItemHandler(itemInList));
                accordion.getPanes().add(itemInList);
            }
            vBoxPane.getChildren().add(accordion);
        }
    }
    
    public void generateItemButtonUi(JFXButton button,EventHandler<ActionEvent> event){
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setFont(new Font("Open Sans", 12));
        button.setAlignment(Pos.CENTER);
        button.setOnAction(event);
        button.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;-fx-background-color: #ffffff;");
        button.setWrapText(true);
        
    }
    public static void setCollaboratorList(ArrayList<UserEntity> collaborators) {
        usersList.clear();
        usersList = collaborators;
        System.out.println("testtest"+usersList);

        todoCollaborators.clear();
        todoCollaborators = collaborators;
        System.out.println("testtest"+todoCollaborators);


    }
    public static ArrayList<UserEntity> getCollaboratorList(){
        return todoCollaborators;
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

    
     @FXML
    private void leaveTodoAction() {
         TodoCollaboratorEntity todoCollaborator = new TodoCollaboratorEntity(todo.getId(), ClientView.currentUser.getId());
        TodoListDBOperations.removeCollaborator(todoCollaborator);
    }

    
    private void updateUi() {
        todoNameLabel.setText(todo.getTitle());
        borderZft.setStyle("-fx-background-color:#"+todo.getColor().substring(2));
        borderZft.setPadding(new Insets(5,5,5,5));
        todoNameLabel.setFont(new Font("Open Sans",24));
        todoNameLabel.setStyle("-fx-font-weight: bold;");
        if (todo.getCreatorId() != ClientView.currentUser.getId()) {
            deleteTodo.setVisible(false);
            editTodo.setVisible(false);
        }
        else{
            leaveTodo.setVisible(false);
        }
        //setCollaboratorsDummy();
        setCollaboratorsPanes();
        generateCollaboratorListUI();
        fillTodoDetails();
        loadItems();
        //TodoListDBOperations.getAllItems(todo);  
    }

    @Override
    public void handle(ActionEvent event) {
        ComponentEntity componentEntity = new ComponentEntity(TodoFormXMLController.itemSelected.getItemID(), null, null, 0);
        ComponentDBOperations.retrieveAllComponent(componentEntity);
    }
}
