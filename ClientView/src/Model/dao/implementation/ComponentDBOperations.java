/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.dao.implementation;

import Model.GsonParser;
import Model.Handler;
import Model.RequestEntity;
import Model.entities.ComponentEntity;
import Model.entities.TodoEntity;
import clientview.ClientView;
import clientview.ItemTasksFXMLController;
import clientview.MainXMLController;
import clientview.TodoStatisticsController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author AhmedIbrahem
 */
public class ComponentDBOperations {

    public static void addComponent(ArrayList<Object> componentsList) {
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "addComponent", componentsList);
        System.out.println(GsonParser.parseToJson(addRequest));
        Handler.sendRequestToServer(addRequest);
    }

    public void addComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            System.out.println("Componetet Added  successfully");
        } else {
            System.out.println("Compenedntd  not added successfully" + arrayObjects.size());
        }
    }

    public static void updateComponent(ArrayList<ComponentEntity> componentList) {
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "updateComponent", componentList);
        GsonParser.parseToJson(addRequest);
        Handler.sendRequestToServer(addRequest);
    }

    public void updateComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null && arrayObjects.size() != 0) {
            System.out.println("Component Updated successfully");
        } else {
            System.out.println("Component not updated");
        }
    }

    public static void deleteComponent(ComponentEntity component) {
        ArrayList<ComponentEntity> list = new ArrayList<>();
        list.add(component);
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "deleteComponent", list);
        GsonParser.parseToJson(addRequest);
        Handler.sendRequestToServer(addRequest);
    }

    public void deleteComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null) {
            System.out.println("Item Deleted   successfully");
        } else {
            System.out.println("Item not added successfully");
        }
    }

    public static void retrieveAllComponent(ComponentEntity itemId) {
        ArrayList<ComponentEntity> list = new ArrayList<>();
        list.add(itemId);
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "getAllComponent", list);
        GsonParser.parseToJson(addRequest);
        Handler.sendRequestToServer(addRequest);
    }

    public void getAllComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null && arrayObjects.size() != 0) {
            System.out.println("You have the component successfully");
            ItemTasksFXMLController.setComponentList(arrayObjects);
        } else {
            System.out.println("Zero components");
        }
        {
            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientview/ItemTasksFXML.fxml"));
                    Parent itemTasks = loader.load();
                    final Stage dialog = new Stage();
                    dialog.initStyle(StageStyle.UNDECORATED);
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner((Stage) ClientView.mainStage.getScene().getWindow());
                    Scene dialogScene = new Scene(itemTasks);
                    dialog.setScene(dialogScene);
                    dialog.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    public static void getAllCheckBoxComponent(TodoEntity todoid) {
        ArrayList<TodoEntity> list = new ArrayList<>();
        list.add(todoid);
        RequestEntity<ComponentEntity> addRequest = new RequestEntity("ComponentDBOperations", "getAllCheckBoxComponent", list);
        GsonParser.parseToJson(addRequest);
        Handler.sendRequestToServer(addRequest);
    }

    public void getAllCheckBoxComponentResponse(ArrayList<Object> arrayObjects) {
        if (arrayObjects != null && arrayObjects.size() != 0) {
            System.out.println("You have the component successfully");
            TodoStatisticsController.setComponentList(arrayObjects);
              Platform.runLater(() -> {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("/clientview/TodoStatistics.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    Scene scene = new Scene(root, 800, 500);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(MainXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } else {
            System.out.println("Zero components");
        }
       
    }

}
