/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.entities.UserEntity;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ItemCollaboratorsXMLController implements Initializable {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Label collaboratorLabel;
    @FXML
    private JFXListView<HBox> collaboratorListView;
    private static ArrayList<UserEntity> Itemcollaborators = new ArrayList();
    private HBox hchild;
    private Label collaboratorNameLabel;
    private ArrayList<HBox> hBoxPane = new ArrayList();

    /**
     * Initializes the controller class.
     */
    public static void setItemAssignedCollaboratorsList(ArrayList<UserEntity> arrayList) {
        Itemcollaborators.clear();
        Itemcollaborators = arrayList;
    }
    public static ArrayList<UserEntity> getItemAssignedCollaborators(){
        return Itemcollaborators;
    }
    
    public void setCollaboratorsListView(ArrayList<UserEntity> collaboratorsList) {
        if(collaboratorsList!=null && collaboratorsList.size() >0){
            for (UserEntity useraya : collaboratorsList) {
                hchild = new HBox();
                collaboratorNameLabel = new Label(useraya.getUsername());
                collaboratorNameLabel.setStyle("-fx-background-radius:30;-fx-border-radius:30;");
                collaboratorNameLabel.setFont(new Font("Arial", 18));
                collaboratorNameLabel.setStyle("-fx-background-radius:30;-fx-border-radius:30;-fx-font-weight: bold;");
                collaboratorNameLabel.setWrapText(true);
                hchild.getChildren().add(collaboratorNameLabel);
                hBoxPane.add(hchild);
            }
        }
    }
    public void generateCollaboratorListUI() {
        ObservableList<HBox> items = FXCollections.observableArrayList(hBoxPane);
        collaboratorListView.setItems(items);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCollaboratorsListView(Itemcollaborators);
        generateCollaboratorListUI();
    }

}
