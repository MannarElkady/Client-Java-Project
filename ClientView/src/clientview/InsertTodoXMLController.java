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
import java.time.LocalDate;
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
    public static boolean isUpdate= false;
    public static TodoEntity todo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(isUpdate){
            isUpdate = false;
            submitNewTodoButton.setText("Update");
            titleTextField.setText(todo.getTitle());
            descriptionTextArea.setText(todo.getDescription());
            assignDate.setValue(LocalDate.of(todo.getAssignDate().getYear()+1900,todo.getAssignDate().getMonth()+1,todo.getAssignDate().getDate()));
            dateDateField.setValue(LocalDate.of(todo.getDeadlineDate().getYear()+1900,todo.getDeadlineDate().getMonth()+1,todo.getDeadlineDate().getDate()));
        }
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
                ((Stage)mainBorderPane.getScene().getWindow()).close(); 
                if(submitNewTodoButton.getText().equals("Update")){
                    newTodo.setId(todo.getId());
                    TodoListDBOperations.updateTodo(newTodo);
                }else{
                    TodoListDBOperations.addTodo(newTodo);
                    UserDBOperations.getAllTodos(ClientView.currentUser);      
                }
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
    public static void setTodoData(TodoEntity todoData) {
        todo = todoData;
    }
}
