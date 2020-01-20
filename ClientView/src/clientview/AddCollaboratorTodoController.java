package clientview;

import Model.dao.implementation.NotificationDBOperations;
import Model.entities.NotificationEntity;
import Model.entities.NotificationReceiversEntity;
import Model.entities.UserEntity;
import static clientview.AddFrindFXMLController.allUsers;
import static clientview.MainXMLController.test2;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * @author Ibrahim
 */
public class AddCollaboratorTodoController implements Initializable {

    @FXML
    private JFXComboBox collaborators;
    @FXML
    private JFXComboBox todoLists;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton cancelButton;
    private static ArrayList<UserEntity> userlist = new ArrayList<>();
    public static boolean isAddCollaborator;

    static ArrayList<String> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < userlist.size(); i++) {
            collaborators.getItems().add(userlist.get(i).getUsername());
        }
    }

    public static void setTodos(ArrayList<String> todo) {
        list = todo;
    }

    @FXML
    private void showCollaborators() {
        System.out.println(collaborators.getSelectionModel().getSelectedItem());
    }

    private void showTodoLists() {
        System.out.println("TESTTT");
        for (String data : list) {
            todoLists.getItems().add(data);
        }
    }

    @FXML
    private void addButtonAction() {
        String data = (String) collaborators.getSelectionModel().getSelectedItem();
        if (data != null) {

            prepareNotification();
            ((Stage) addButton.getScene().getWindow()).close();

        }
    }

    @FXML
    private void cancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        
    }

    public static void setTodoFriendList(ArrayList<UserEntity> a) {
        /*userlist.clear();
        userlist = a;
        for (int i = 0; i < userlist.size(); i++) {
            for (int j = 0; j < TodoFormXMLController.todoCollaborators.size(); j++) {
                if (userlist.get(i).getId() == TodoFormXMLController.todoCollaborators.get(j).getId()) {
                    userlist.remove(i);
                }
            }
        }*/
    }
    private void prepareNotification() {

        ArrayList<Object> data = new ArrayList<>();
        NotificationEntity notification = new NotificationEntity();
        notification.setHeader("Add Colaborator Invetation");
        notification.setText(ClientView.currentUser.getUsername() + " invited you to be collaborator in TODO");
        notification.setNotificationType("todoInvitation"+TodoFormXMLController.todo.getId());
        notification.setSenderID(ClientView.currentUser.getId());

        NotificationReceiversEntity notificationReceiver = new NotificationReceiversEntity();
        for (int i = 0; i < userlist.size(); i++) {
            if (userlist.get(i).getUsername().equals((String) collaborators.getSelectionModel().getSelectedItem())) {
                notificationReceiver.setReceiverID(userlist.get(i).getId());
            }
        }
        ArrayList<NotificationReceiversEntity> receiversList = new ArrayList<>();
        receiversList.add(notificationReceiver);
        notification.setNotificationReceivers(receiversList);
        data.add(notification);
        NotificationDBOperations.sendNotification(data);
    }

}
