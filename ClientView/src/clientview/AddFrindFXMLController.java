/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.ItemDBOperations;
import Model.dao.implementation.UserDBOperations;
import Model.entities.ItemEntity;
import Model.entities.UserEntity;
import Utility.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author AhmedIbrahem
 */
public class AddFrindFXMLController implements Initializable {

    @FXML
    private JFXButton addfrindbtn;
    @FXML
    private JFXButton canclefrindbtn;
    @FXML
    private TextField frindtextfield;
    @FXML
    private BorderPane addbroderpane;
    String frindName;
    public static ArrayList<UserEntity> allUsers = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> allusers=new ArrayList<>();
        for(int i=0;i<allUsers.size();i++)
        {
            allusers.add(allUsers.get(i).getUsername());
        }
        TextFields.bindAutoCompletion(frindtextfield,allusers);
    }

    @FXML
    private void addButtonAction() {
        frindName = frindtextfield.getText();
        if (Validation.checkString(frindName) && !frindName.isEmpty()) {
            UserEntity user = new UserEntity();
            user.setId(ClientView.currentUser.getId());
            user.setUsername(frindName);
            UserDBOperations.AddFrind(user);
            ((Stage) addfrindbtn.getScene().getWindow()).close();

        }

    }

    @FXML
    private void cancelButtonAction() {
        Stage stage = (Stage) canclefrindbtn.getScene().getWindow();
        stage.close();
    }

    public static void setAllUSersList(ArrayList<UserEntity> users) {
        System.out.println("size"+users.size());
        allUsers = users;
    }

}
