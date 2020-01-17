/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.ItemUpdatingActionListener;
import Utility.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Model.dao.implementation.ItemDBOperations;
import Model.dao.implementation.TodoListDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.ItemEntity;
import java.time.ZoneId;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InsertItemXMLController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private JFXButton submitNewItemButton;
    @FXML
    private JFXButton resetItemFormButton;
    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private JFXDatePicker dateDateField;

    private ItemEntity newItemEntity = null;
    @FXML
    private JFXButton backButton;
    private static ItemEntity itemToUpdate;
    private Stage stage;
    public static boolean isUpdate=false;
    /**
     * Initializes the controller class.
     */
    public static void setItemToUpdate(ItemEntity item){
        itemToUpdate=item;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(isUpdate){
            loadItemToUpdate();
        }
    }
    public void loadItemToUpdate(){
        titleTextField.setText(itemToUpdate.getTitle());
        descriptionTextArea.setText(itemToUpdate.getDescription());
        dateDateField.setValue(itemToUpdate.getDeadlineDate().toLocalDate());
        submitNewItemButton.setText("Update");
        resetItemFormButton.setDisable(true);
        isUpdate=false;
    }

    @FXML
    private void addPaneActionESC(KeyEvent event) {
        KeyCode key = event.getCode();
        if (key == KeyCode.ESCAPE) {
            ((Stage) mainBorderPane.getScene().getWindow()).close();
        }
    }

    @FXML
    private void submitNewItemButtonAction(ActionEvent event) {
        String checkType= submitNewItemButton.getText();
        if(checkType.equals("Add")){
            addItemEntity();
        }
        else if(checkType.equals("Update")){
            UpdateItemEntity();
        }
        refreshPage();
    }
    private void UpdateItemEntity(){
        if (Validation.checkString(titleTextField.getText()) && (Validation.checkString(descriptionTextArea.getText()))) {
            if (dateDateField.getValue() != null) {
                if(Validation.checkDeadlineItem(java.sql.Date.valueOf(dateDateField.getValue()), TodoFormXMLController.todo.getAssignDate(), TodoFormXMLController.todo.getDeadlineDate())){
                itemToUpdate.setDescription(descriptionTextArea.getText());
                itemToUpdate.setTitle(titleTextField.getText());
                itemToUpdate.setDeadlineDate(java.sql.Date.valueOf(dateDateField.getValue()));
                ItemDBOperations.updateItem(itemToUpdate);
                }
            }
        }
    }
    private void addItemEntity() {
        if (Validation.checkString(titleTextField.getText()) && (Validation.checkString(descriptionTextArea.getText()))) {
            if (dateDateField.getValue() != null) {
                if(Validation.checkDeadlineItem(java.sql.Date.valueOf(dateDateField.getValue()), TodoFormXMLController.todo.getAssignDate(), TodoFormXMLController.todo.getDeadlineDate())){
                newItemEntity = new ItemEntity();
                newItemEntity.setTodoID(TodoFormXMLController.todo.getId());
                newItemEntity.setDescription(descriptionTextArea.getText());
                newItemEntity.setTitle(titleTextField.getText());
                newItemEntity.setCreatorID(ClientView.currentUser.getId());
                newItemEntity.setDeadlineDate(java.sql.Date.valueOf(dateDateField.getValue()));
                ItemDBOperations.addItem(newItemEntity);
                }
            }
        }
    }

    private ItemEntity getNewItem() {
        return newItemEntity;
    }

    private void refreshPage() {
        ((Stage) mainBorderPane.getScene().getWindow()).close();
        TodoListDBOperations.getAllItems(TodoFormXMLController.todo);
    }

    /*
    private void returnToTodolistForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TodoFormXML.fxml"));
            Parent mainCallWindowFXML = loader.load();
            Stage stage = (Stage) mainBorderPane.getScene().getWindow();
            Scene mainCallWindow = new Scene(mainCallWindowFXML);
            stage.setScene(mainCallWindow);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InsertItemXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
    @FXML
    private void resetItemFormButtonAction(ActionEvent event) {
        titleTextField.clear();
        descriptionTextArea.clear();
        dateDateField.setValue(null);
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        itemToUpdate=null;
        ((Stage) mainBorderPane.getScene().getWindow()).close();
    }

    

}
