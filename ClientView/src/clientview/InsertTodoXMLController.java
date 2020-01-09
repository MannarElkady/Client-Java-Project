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
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private JFXDatePicker assignDate;
    @FXML
    private JFXColorPicker colorPicker;

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
            if (dateDateField.getValue() != null && assignDate.getValue() != null) {
                newTodo = new TodoEntity();
                newTodo.setAssignDate(java.sql.Date.valueOf(assignDate.getValue()));
                newTodo.setTitle(titleTextField.getText());
                newTodo.setCreatorId(ClientView.currentUser.getId());
                System.out.println(colorPicker.getValue().toString().substring(4));
                newTodo.setColor(colorPicker.getValue().toString());
                newTodo.setDescription(descriptionTextArea.getText());
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

    @FXML
    private void assignDateAction(ActionEvent event) {
    }
    
}
