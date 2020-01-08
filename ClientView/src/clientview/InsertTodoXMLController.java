/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.TodoListDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.TodoEntity;
import Utility.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InsertTodoXMLController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXButton submitNewTodoButton;
    @FXML
    private JFXButton resetTodoFormButton;
    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXDatePicker dateDateField;
    
    private static TodoEntity newTodo; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backButtonAction(ActionEvent event) {
         ((Stage)mainBorderPane.getScene().getWindow()).close();
    }

    @FXML
    private void submitNewTodoButtonAction(ActionEvent event) {
        if (Validation.checkString(titleTextField.getText())) {
            if (dateDateField.getValue() != null) {
                newTodo = new TodoEntity();
                newTodo.setTitle(titleTextField.getText());
                newTodo.setCreatorId(ClientView.currentUser.getId());
                newTodo.setDeadlineDate(java.sql.Date.valueOf(dateDateField.getValue()));
                TodoListDBOperations.addTodo(newTodo);
                ((Stage)mainBorderPane.getScene().getWindow()).close(); 
                UserDBOperations.getAllTodos(ClientView.currentUser);      
            }
        }
    }

    @FXML
    private void resetTodoFormButtonAction(ActionEvent event) {
        titleTextField.clear();
        dateDateField.setValue(null);
    }

    private static TodoEntity getNewTodo(){
        return newTodo;
    }
    @FXML
    private void addPaneActionESC(KeyEvent event) {
    }
    
}
