/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.MainFormHandler;
import Model.NotificationGUIHandler;
import Model.dao.implementation.NotificationDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.NotificationEntity;
import Model.entities.NotificationReceiversEntity;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private JFXListView<String> friendListPane;
    @FXML
    private JFXMasonryPane jMasonaryPane;

    @FXML
    ImageView notificationButton;
    @FXML
    MenuItem notificationItem;
    public static ArrayList<NotificationEntity> notificationsListForOtherClasses = new ArrayList<>();        
    
    private Image img = null;
    private ImageView imgView = null;
    //private Label todoName = null;
    private Label userLabel = null;

    public static ArrayList<Object> data;
    // for Dummy Testing
    ArrayList<TodoEntity> test = new ArrayList();
    public static ArrayList<UserEntity> test2 = new ArrayList();
    ArrayList<HBox> hBoxPane = new ArrayList();
    HBox child = null;
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ScrollPane scrollPaneMasonary;
    @FXML
    private StackPane stackPane;
    @FXML
    private BorderPane stackPaneBorder;

    @FXML
    private JFXListView<BorderPane> listView;

    static ArrayList<NotificationEntity> notificationListData = new ArrayList<>();
    
    
    ArrayList<String> friendsList = new ArrayList();
    private ObjectProperty<ListCell<String>> dragSource = new SimpleObjectProperty<>();


    public static void setnotificationListData(ArrayList<NotificationEntity> notifications){
        notificationListData.clear();
        notificationListData=notifications;
        
    }
    public void setFriendListDummy() {
        UserEntity useraya = new UserEntity();

        useraya.setUsername("Userayaa");
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);
        test2.add(useraya);

    }



    public void setFriendListPanes() {
        for (UserEntity useraya : test2) {
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
                friendsList.add(useraya.getUsername());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setTodoDummy() {
        TodoEntity todo = new TodoEntity();
        todo.setTitle("New Item");
        test.add(todo);
        test.add(todo);
        test.add(todo);
        test.add(todo);
        test.add(todo);

    }

    public void FlowCardComposite() {
        scrollPaneMasonary.setFitToWidth(true);
        // scrollPaneMasonary.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // stackPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        scrollPaneMasonary.setPrefHeight(Double.MAX_VALUE);
        stackPane.setPrefHeight(Double.MAX_VALUE);
        scrollPaneMasonary.setContent(jMasonaryPane);
    }

    /* public void FlowCardComposite() {

	scrollPane = new ScrollPane();
	scrollPane.setFitToHeight(true);
	scrollPane.setFitToWidth(true);
	scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	StackPane stackPane = new StackPane(scrollPane);
	stackPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	masonryPane = new JFXMasonryPane();
	scrollPane.setContent(masonryPane);
	setCenter(stackPane);
	masonryPane.setCache(false);
	setStyle("-fx-background-color : #292929");
}*/

    public static void setTodos(ArrayList<Object> list) {
        data = list;
    }

    /**
     * Initializes the controller class.
     */
    public void generateTodosUI(ArrayList<Object> todoNames) {
        if (todoNames!=null && todoNames.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                Label todoName = null;
                TodoEntity todo = null;
                try {
                    todo = (TodoEntity) data.get(i);
                    todoName = new Label(todo.getTitle());
                    img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/todo.jpg"));
                    imgView = new ImageView(img);
                    imgView.setFitHeight(50.0);
                    imgView.setFitWidth(50.0);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainXMLBase.class.getName()).log(Level.SEVERE, null, ex);
                }
                todoName.setGraphic(imgView);
                todoName.paddingProperty();
                todoName.setPadding(new Insets(15));
                todoName.setPrefSize(100, 100);
                todoName.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                todoName.setWrapText(true);
                todoName.setId(String.valueOf(todo.getId()));
                todoName.addEventFilter(MouseEvent.MOUSE_CLICKED, new MainFormHandler());
                createDragAndDropEvents(todoName);
                jMasonaryPane.getChildren().add(todoName);

            }
        }

    }

    public void generateFriendListUI() {
        ObservableList<HBox> items = FXCollections.observableArrayList(hBoxPane);
        //friendListPane.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
        //friendListPane.setItems(items);

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FlowCardComposite();
        setFriendListPanes();
        generateTodosUI(data);
        //generateFriendListUI();
        generateFriendListUIWithDragAndDropFeature();
        generateFriendListUI();           
        ClientView.whichScreen = "MainController";
        jMasonaryPane.setCache(false);
        //jMasonaryPane.setCache(false);
        stackPaneBorder.setCenter(stackPane);
        int rows = jMasonaryPane.getLimitRow();

        prpareNotificationMenu();        
        //viewNotificationList();
        //loadNotificationMenu(notificationListData);
    }
     
    @FXML
    private void addFriendBtnAction(ActionEvent event) {
        try {
            UserDBOperations.getAllUsers(ClientView.currentUser);
            Parent root = FXMLLoader.load(getClass().getResource("/clientview/AddFrindFXML.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            Scene scene = new Scene(root, 400, 300);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
 
            ex.printStackTrace();
        }
    }

    @FXML
    private void addTodoAction(ActionEvent event) {

        InsertTodoXMLController.isUpdate = false;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertTodoXML.fxml"));
            Parent insertItemWindow = loader.load();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner((Stage) mainBorderPane.getScene().getWindow());
            Scene dialogScene = new Scene(insertItemWindow);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(TodoFormXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    private void getAllTodos() {
        UserEntity user = new UserEntity();
        user.setId(ClientView.currentUser.getId());
        UserDBOperations.getAllTodos(user);        
    }
*/
    public static void setFriendList(ArrayList<UserEntity> a) {
        test2 = a;
    }

    private void prepareNotification(String todoName, String todoID) {

        ArrayList<Object> data = new ArrayList<>();
        NotificationEntity notification = new NotificationEntity();
        notification.setHeader("collaborator invitation");
        notification.setText(ClientView.currentUser.getUsername() + " invited you to be collaborator on todo " + todoName);
        notification.setNotificationType("todoInvitation" + todoID);
        notification.setSenderID(ClientView.currentUser.getId());

        NotificationReceiversEntity notificationReceiver = new NotificationReceiversEntity();
        for (int i = 0; i < test2.size(); i++) {
            if (test2.get(i).getUsername().equals(dragSource.get().getItem())) {
                notificationReceiver.setReceiverID(test2.get(i).getId());
            }
        }
        ArrayList<NotificationReceiversEntity> receiversList = new ArrayList<>();
        receiversList.add(notificationReceiver);
        notification.setNotificationReceivers(receiversList);
        data.add(notification);
        NotificationDBOperations.sendNotification(data);
    }

    private void createDragAndDropEvents(Label todoName) {
        todoName.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });
        todoName.setOnDragDropped(event -> {
            ListCell<String> dragSourceCell = dragSource.get();
            String collaboratorName = dragSourceCell.getItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setContentText("Do you want to assign user " + collaboratorName + " to " + todoName.getText());

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No");
            ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonYes, buttonNo, buttonCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonYes) {
                prepareNotification(todoName.getText(), todoName.getId());

            } 
        });
    }
    
     private void generateFriendListUIWithDragAndDropFeature() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                //ObservableList<HBox> items = FXCollections.observableArrayList(hBoxPane);
               // friendListPane.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                //friendListPane.setItems(items);
                for (int i = 0; i < friendsList.size(); i++) {
                    friendListPane.getItems().add(friendsList.get(i));
                }
                
                friendListPane.setCellFactory(lv -> {
                    ListCell<String> cell = new ListCell<String>() {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            setText(item);
                        }
                    };

                    cell.setOnDragDetected(event -> {
                        if (!cell.isEmpty()) {
                            Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
                            ClipboardContent cc = new ClipboardContent();
                            cc.putString(cell.getItem());
                            db.setContent(cc);
                            dragSource.set(cell);
                            ListCell<String> dragSourceCell = dragSource.get();
                        }
                    });

                    cell.setOnDragOver(event -> {
                        Dragboard db = event.getDragboard();
                        if (db.hasString()) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                    });
                    cell.setOnDragDropped(event -> {
                        Dragboard db = event.getDragboard();
                        if (db.hasString() && dragSource.get() != null) {
                            ListCell<String> dragSourceCell = dragSource.get();
                            // listView.getItems().add(dragSourceCell.getItem());                  
                            //label.setText(dragSourceCell.getItem());
                            event.setDropCompleted(true);
                            dragSource.set(null);
                        } else {
                            event.setDropCompleted(false);
                        }
                    });

                    return cell;
                });

            }
        });

    }
     
     private void prpareNotificationMenu(){
          /*try {
            Image img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/notification_icon.jpg"));
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(50);
            imgView.setFitWidth(50);
            menuButton.setGraphic(imgView);

            Platform.runLater(()
                    -> {
                // hide the arrow of menuButton
                menuButton.lookup(".arrow").setStyle("-fx-background-insets: 0; -fx-padding: 0; -fx-shape: null;");
                // hide the arraw-button pane, to remove unnecessary padding
                menuButton.lookup(".arrow-button").setStyle("-fx-padding: 0");
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
     
     }
     
     public static void setNotificationsList(ArrayList<NotificationEntity> notificationsList){
     
         //loadNotificationMenu(notificationsList);
     }
     
      @FXML
    private void viewNotificationList() {
   
        ImageView notificationIcon =(ImageView) ClientView.mainStage.getScene().lookup("#notificationButton");
        try {
            img = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/clientview/resources/notification.jpg"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if(notificationIcon!=null){
            notificationIcon.setImage(img);
        }
         ArrayList<Integer> users = new ArrayList<>();
        users.add(ClientView.currentUser.getId());
        NotificationDBOperations.receiveNotifications(users);
    }          
    private void loadNotificationMenu(ArrayList<NotificationEntity> notificationsList) {

        notificationsListForOtherClasses.clear();
        notificationsListForOtherClasses=notificationsList;
      
                Label text1;
                
                ArrayList<BorderPane> borderPanes = new ArrayList<BorderPane>();

                for (int i = 0; i < notificationsList.size(); i++) {
                    text1 = new Label(notificationsList.get(i).getHeader());
                    BorderPane border = new BorderPane();
                    VBox box = new VBox();
                    box.getChildren().add(text1);

                    if (!notificationsList.get(i).getNotificationType().toLowerCase().contains("invitation")) {

                        box.setAlignment(Pos.CENTER_LEFT);
                        text1 = new Label(notificationsList.get(i).getText());
                        box.getChildren().add(text1);

                        border.setCenter(box);
                        borderPanes.add(border);

                    } else {
                        if (notificationsList.get(i).getNotificationType().contains("itemInvitation") || notificationsList.get(i).getNotificationType().contains("todoInvitation") || notificationsList.get(i).getNotificationType().contains("friendInvitation") ) {
                            Button b1 = new Button("accept");
                            Button b2 = new Button("reject");
                            b1.setId("accept" +notificationsList.get(i).getNotificationType());
                            b2.setId("reject" +notificationsList.get(i).getNotificationType());
                            b1.addEventFilter(MouseEvent.MOUSE_CLICKED, new NotificationGUIHandler());
                            b2.addEventFilter(MouseEvent.MOUSE_CLICKED, new NotificationGUIHandler());
                            HBox horizontal = new HBox();
                            horizontal.getChildren().add(b1);
                            horizontal.getChildren().add(b2);
                            border.setRight(horizontal);
                            border.setLeft(text1);
                            borderPanes.add(border);
                        }

                    //}

                }
                ObservableList<BorderPane> myObservableList = FXCollections.observableList(borderPanes);

                listView.setItems(myObservableList);                 
                listView.setPrefSize(600, 300);
                //menuButton.getItems().add(new MenuItem("",parentPane));
                //listView.getItems().add(myObservableList);
            } 
        
    }

}


