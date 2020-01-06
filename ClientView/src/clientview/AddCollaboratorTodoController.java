package clientview;

import Model.dao.implementation.TodoListDBOperations;
import Model.entities.AssignFriendTodoEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Ibrahim
 */
public class AddCollaboratorTodoController implements Initializable{
    
    @FXML private JFXComboBox collaborators;
    @FXML private JFXComboBox todoLists;
    @FXML private JFXButton addButton;
    @FXML private JFXButton cancelButton;
    
    ArrayList<AssignFriendTodoEntity> list;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collaborators.getItems().add("Java 1.8");
        collaborators.getItems().add("Java 1.7");
        collaborators.getItems().add("Java 1.6");
        collaborators.getItems().add("Java 1.5");
        list = new ArrayList<>();

    }
    
    @FXML private void showCollaborators(){
        System.out.println(collaborators.getSelectionModel().getSelectedItem());
    }
    
    @FXML private void showTodoLists(){
    
    }
    
    @FXML private void addButtonAction(){
        String data = (String) collaborators.getSelectionModel().getSelectedItem();
        if(data!=null){
            list.add(new AssignFriendTodoEntity("ibrahim", 1, 2));
            TodoListDBOperations.assignTodoRequest(list); 
        }
    }
    
    @FXML private void cancelButtonAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
