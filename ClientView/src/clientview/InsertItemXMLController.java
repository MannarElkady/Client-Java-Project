/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

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
<<<<<<< HEAD
import Model.entities.ItemEntity;
=======
>>>>>>> 598f3994e3b85ce262e24b6bf5317b98cfaefdfd

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

    private ItemEntity newItemEntity=null;
    @FXML
    private JFXButton backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addPaneActionESC(KeyEvent event) {
         KeyCode key = event.getCode();
            if (key == KeyCode.ESCAPE){
               ((Stage)mainBorderPane.getScene().getWindow()).close();
            }
    }

    @FXML
    private void submitNewItemButtonAction(ActionEvent event) {
        if (Validation.checkString(titleTextField.getText()) && (Validation.checkString(descriptionTextArea.getText()))) {
            if (dateDateField.getValue() != null) {
                newItemEntity = new ItemEntity();
                newItemEntity.setDescription(descriptionTextArea.getText());
                newItemEntity.setTitle(titleTextField.getText());
                newItemEntity.setDeadlineDate(java.sql.Date.valueOf(dateDateField.getValue()));
                ItemDBOperations.addItem(newItemEntity);
                ((Stage)mainBorderPane.getScene().getWindow()).close();                
            }
        }
    }
    private ItemEntity getNewItem(){
        return newItemEntity;
    }

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

    @FXML
    private void resetItemFormButtonAction(ActionEvent event) {
        titleTextField.clear();
        descriptionTextArea.clear();
        dateDateField.setValue(null);
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        ((Stage)mainBorderPane.getScene().getWindow()).close();
    }

}
